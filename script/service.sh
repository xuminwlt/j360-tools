#!/bin/bash
# ------------------------------------------------------------------------
# IMPROVED DEBUGGING (execute with bash -x)
# export PS4=' ${BASH_SOURCE}:${LINENO}(${FUNCNAME[0]}) '
#
# Backup invocation parameters
COMMANDLINE_ARGS="$@"
EXEC_OPTION=""
SERVER_NAME="boot"
# ------------------------------------------------------------------------
# HELPERS

# a simple helper to get the current user
setCurrentUser(){
   CUSER="`whoami 2>/dev/null`"
   # Solaris hack
   if [ ! $? -eq 0 ]; then
      CUSER="`/usr/ucb/whoami 2>/dev/null`"
   fi
}

# get a canonical path, macosx and slowlaris does not support radlink -f :-)
pathCanonical() {
    local dst="${1}"
    while [ -h "${dst}" ] ; do
        ls=`ls -ld "${dst}"`
        link=`expr "$ls" : '.*-> \(.*\)$'`
        if expr "$link" : '/.*' > /dev/null; then
            dst="$link"
        else
            dst="`dirname "${dst}"`/$link"
        fi
    done
    local bas="`basename "${dst}"`"
    local dir="`dirname "${dst}"`"
    if [ "$bas" != "$dir" ]; then
      dst="`pathCanonical "$dir"`/$bas"
    fi
    echo "${dst}" | sed -e 's#//#/#g' -e 's#/\./#/#g' -e 's#/[^/]*/\.\./#/#g'
}

# a simple helper to get the server installation dir
getServerHome(){
  # get the real path to the binary
  local REAL_BIN="`pathCanonical $0`"
  local REAL_DIR="`dirname $REAL_BIN`/../"
  REAL_DIR="`cd $REAL_DIR && pwd -P`"
  if [ -z "$REAL_DIR" ];then
      echo 'ERROR: unable to find real installtion path fo server, you have to define SERVER_HOME manually in the config' >&2
      exit 1
  fi
  echo "$REAL_DIR"

}

# ------
# start setting variables
# ------
# Server installation dir
if [ -z "$SERVER_HOME" ] ; then
  SERVER_HOME="`getServerHome`"
fi

# Server base dir
if [ -z "$SERVER_BASE" ] ; then
  SERVER_BASE="$SERVER_HOME"
fi

# Configure user specified classpath here or externally using this variable
if [ -z "$SERVER_USER_CLASSPATH" ] ; then
    SERVER_USER_CLASSPATH=""
fi

# Server Classpath configuration
SERVER_CLASSPATH="$SERVER_BASE/../lib/:$SERVER_USER_CLASSPATH"

# Server configuration directory
if [ -z "$SERVER_CONF" ] ; then

    # For backwards compat with old variables we let SERVER_CONFIG_DIR set SERVER_CONF
    if [ -z "$SERVER_CONFIG_DIR" ] ; then
        SERVER_CONF="$SERVER_BASE/conf"
    else
        SERVER_CONF="$SERVER_CONFIG_DIR"
    fi
fi

# Configure a user with non root privileges, if no user is specified do not change user
if [ -z "$SERVER_USER" ] ; then
    SERVER_USER=""
fi

# Server data directory
if [ -z "$SERVER_DATA" ] ; then

    # For backwards compat with old variables we let SERVER_DATA_DIR set SERVER_DATA
    if [ -z "$SERVER_DATA_DIR" ] ; then
        SERVER_DATA="$SERVER_BASE/data"
    else
        SERVER_DATA="$SERVER_DATA_DIR"
    fi
fi

if [ -z "$SERVER_TMP" ] ; then
  SERVER_TMP="$SERVER_BASE/tmp"
fi

if [ ! -d "$SERVER_DATA" ]; then
   setCurrentUser
   if ( [ -z "$SERVER_USER" ] || [ "$SERVER_USER" = "$CUSER" ] );then
        mkdir $SERVER_DATA
   elif [ "`id -u`" = "0" ];then
      su -c "mkdir $SERVER_DATA" - $SERVER_USER;
   fi
fi

if [ ! -d "$SERVER_TMP" ]; then
   if ( [ -z "$SERVER_USER" ] || [ "$SERVER_USER" = "$CUSER" ] );then
        mkdir $SERVER_TMP
   elif [ "`id -u`" = "0" ];then
      su -c "mkdir $SERVER_TMP" - $SERVER_USER;
   fi
fi

# Location of the pidfile
if [ -z "$SERVER_PIDFILE" ]; then
  SERVER_PIDFILE="$SERVER_DATA/server.pid"
fi

# ------------------------------------------------------------------------
# LOAD CONFIGURATION

# CONFIGURATION
# For using instances
SERVER_CONFIGS="$SERVER_HOME/bin/env"

# load server configuration
CONFIG_LOAD="no"
for SERVER_CONFIG in $SERVER_CONFIGS;do
   if [ -f "$SERVER_CONFIG" ] ; then
     ( . $SERVER_CONFIG >/dev/null 2>&1 )
     if [ "$?" != "0" ];then
      echo "ERROR: There are syntax errors in '$SERVER_CONFIG'"
      exit 1
     else
       echo "INFO: Loading '$SERVER_CONFIG'"
       . $SERVER_CONFIG
      CONFIG_LOAD="yes"
      break
     fi
   fi
done

# inform user that default configuration is loaded, no suitable configfile found
if [ "$CONFIG_LOAD" != "yes" ];then
      echo "INFO: Using default configuration";
      echo "      Configurations are loaded in the following order: $SERVER_CONFIGS"
      echo
fi

if [ -z "$SERVER_OPTS" ] ; then
    SERVER_OPTS="$SERVER_OPTS_MEMORY"
fi

# ------------------------------------------------------------------------
# OS SPECIFIC SUPPORT

OSTYPE="unknown"

case "`uname`" in
  CYGWIN*) OSTYPE="cygwin" ;;
  Darwin*)
           OSTYPE="darwin"
           if [ -z "$JAVA_HOME" ] && [ "$JAVACMD" = "auto" ];then
             JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Home
           fi
           ;;
esac

# For Cygwin, ensure paths are in UNIX format before anything is touched
if [ "$OSTYPE" = "cygwin" ]; then
  [ -n "$SERVER_HOME" ] &&
    SERVER_HOME="`cygpath --unix "$SERVER_HOME"`"
  [ -n "$JAVA_HOME" ] &&
    JAVA_HOME="`cygpath --unix "$JAVA_HOME"`"
  [ -n "$SERVER_CLASSPATH" ] &&
    SERVER_CLASSPATH="`cygpath --path --unix "$SERVER_CLASSPATH"`"
fi

# Detect the location of the java binary
if [ -z "$JAVACMD" ] || [ "$JAVACMD" = "auto" ] ; then
  if [ -n "$JAVA_HOME"  ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  fi
fi

# Hm, we still do not know the location of the java binary
if [ ! -x "$JAVACMD" ] ; then
    JAVACMD=`which java 2> /dev/null `
    if [ -z "$JAVACMD" ] ; then
        JAVACMD=java
    fi
fi
# Stop here if no java installation is defined/found
if [ ! -x "$JAVACMD" ] ; then
  echo "ERROR: Configuration variable JAVA_HOME or JAVACMD is not defined correctly."
  echo "       (JAVA_HOME='$JAVA_HOME', JAVACMD='$JAVACMD')"
  exit 1
fi

echo "INFO: Using java '$JAVACMD'"

if [ -z "$SERVER_BASE" ] ; then
  SERVER_BASE="$SERVER_HOME"
fi

# For Cygwin, switch paths to Windows format before running java if [ "$OSTYPE" = "cygwin" ]; then
if [ "$OSTYPE" = "cygwin" ];then
  SERVER_HOME="`cygpath --windows "$SERVER_HOME"`"
  SERVER_BASE="`cygpath --windows "$SERVER_BASE"`"
  SERVER_CONF="`cygpath --windows "$SERVER_CONF"`"
  SERVER_DATA="`cygpath --windows "$SERVER_DATA"`"
  SERVER_CLASSPATH=`cygpath --path --windows "$SERVER_CLASSPATH"`
  [ -n "$JAVA_HOME" ] &&
    JAVA_HOME="`cygpath --windows "$JAVA_HOME"`"
  CYGHOME="`cygpath --windows "$HOME"`"
  SERVER_TMP="`cygpath --windows "$SERVER_TMP"`"
  if [ -n "$CYGHOME" ]; then
      SERVER_CYGWIN="-Dcygwin.user.home=\"$CYGHOME\""
  fi
fi

# Set default classpath
# Add instance conf dir before AMQ install conf dir to pick up instance-specific classpath entries first
SERVER_CLASSPATH="${SERVER_CONF}:${SERVER_CLASSPATH}"

if [ "$OSTYPE" = "cygwin" ];then
  # remove training backslashes to prevent quoting problems
  SERVER_CLASSPATH="`echo ${SERVER_CLASSPATH}|sed '~s,[\\]*$,,g'`"
  SERVER_HOME="`echo ${SERVER_HOME}|sed '~s,[\\]*$,,g'`"
  SERVER_BASE="`echo ${SERVER_BASE}|sed '~s,[\\]*$,,g'`"
  SERVER_CONF="`echo ${SERVER_CONF}|sed '~s,[\\]*$,,g'`"
  SERVER_DATA="`echo ${SERVER_DATA}|sed '~s,[\\]*$,,g'`"
fi

# Start the Server JAR
#
#
# @ARG1 : the name of the PID-file
#         If specified, this function starts the java process in background as a daemon
#         and stores the pid of the created process in the file.
#         Output on stdout/stderr will be supressed if this parameter is specified
# @RET  : If unless 0 something went wrong
#
# Note: This function uses a lot of globally defined variables
# - if $SERVER_USER is set, the function tries starts the java process whith the specified
#   user
invokeJar(){
   PIDFILE="$1"
   TASK_TODO="$2"
   RET="1"

   if [ ! -f "${SERVER_HOME}/lib/${SERVER_NAME}.jar" ];then
    echo "ERROR: '${SERVER_HOME}/lib/${SERVER_NAME}.jar' does not exist, define SERVER_HOME in the config"
    exit 1
   fi
   setCurrentUser

   if ( [ -z "$SERVER_USER" ] || [ "$SERVER_USER" = "$CUSER" ] );then
      DOIT_PREFIX="sh -c "
      DOIT_POSTFIX=";"
   elif [ "`id -u`" = "0" ];then
      DOIT_PREFIX="su -s /bin/sh -c "
      DOIT_POSTFIX=" - $SERVER_USER"
      echo "INFO: changing to user '$SERVER_USER' to invoke java"
   fi
   # Execute java binary
   if [ -n "$TASK_TODO" ] && [ "$TASK_TODO" != "stop" ];then
      $EXEC_OPTION $DOIT_PREFIX "\"$JAVACMD\" $SERVER_OPTS $SERVER_DEBUG_OPTS \
              -Dserver.classpath=\"${SERVER_CLASSPATH}\" \
              -Dserver.home=\"${SERVER_HOME}\" \
              -Dserver.base=\"${SERVER_BASE}\" \
              -Dserver.conf=\"${SERVER_CONF}\" \
              -Dserver.data=\"${SERVER_DATA}\" \
              $SERVER_CYGWIN \
              -jar \"${SERVER_HOME}/lib/${SERVER_NAME}.jar\" $COMMANDLINE_ARGS >/dev/null 2>&1 &
              RET=\"\$?\"; APID=\"\$!\";
              echo \$APID > "${PIDFILE}";
              echo \"INFO: pidfile created : '${PIDFILE}' (pid '\$APID')\";exit \$RET" $DOIT_POSTFIX
      RET="$?"
   elif [ -n "$TASK_TODO" ] && [ "$TASK_TODO" = "stop" ];then
          SPID="`cat "${PIDFILE}"`"
          $EXEC_OPTION $DOIT_PREFIX "\"$JAVACMD\" $SERVER_OPTS $SERVER_DEBUG_OPTS \
              -Dserver.classpath=\"${SERVER_CLASSPATH}\" \
              -Dserver.home=\"${SERVER_HOME}\" \
              -Dserver.base=\"${SERVER_BASE}\" \
              -Dserver.conf=\"${SERVER_CONF}\" \
              -Dserver.data=\"${SERVER_DATA}\" \
              $SERVER_CYGWIN \
              -jar \"${SERVER_HOME}/lib/${SERVER_NAME}.jar\" $COMMANDLINE_ARGS --pid $SPID &
              RET=\"\$?\"; APID=\"\$!\";
              echo \$APID > "${PIDFILE}.stop"; exit \$RET" $DOIT_POSTFIX
      RET="$?"
   else
      $EXEC_OPTION $DOIT_PREFIX "\"$JAVACMD\" $SERVER_OPTS $SERVER_DEBUG_OPTS \
              -Dserver.classpath=\"${SERVER_CLASSPATH}\" \
              -Dserver.home=\"${SERVER_HOME}\" \
              -Dserver.base=\"${SERVER_BASE}\" \
              -Dserver.conf=\"${SERVER_CONF}\" \
              -Dserver.data=\"${SERVER_DATA}\" \
              $SERVER_CYGWIN \
              -jar \"${SERVER_HOME}/lib/${SERVER_NAME}.jar\" $COMMANDLINE_ARGS" $DOIT_POSTFIX
      RET="$?"
   fi
   return $RET
}

# Check if Server is running
#
# @RET  : 0 => the server process is running
#         1 => process id in $SERVER_PIDFILE does not exist anymore
#         2 => something is wrong with the pid file
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE : the name of the pid file


checkRunning(){
    if [ -f "$SERVER_PIDFILE" ]; then
       if  [ -z "`cat $SERVER_PIDFILE`" ];then
        echo "ERROR: Pidfile '$SERVER_PIDFILE' exists but contains no pid"
        return 2
       fi
       SERVER_PID="`cat ${SERVER_PIDFILE}`"
       RET="`ps -p "${SERVER_PID}"|grep java`"
       if [ -n "$RET" ];then
         return 0;
       else
         return 1;
       fi
    else
         return 1;
    fi
}

checkStopRunning(){
    PID_STOP="${SERVER_PIDFILE}.stop"
    if [ -f "$PID_STOP" ]; then
       if  [ -z "`cat $PID_STOP`" ];then
        echo "ERROR: Pidfile os stop process '$PID_STOP' exists but contains no pid"
        return 2
       fi
       THEPID=`cat ${PID_STOP}`
       RET=`ps -p $THEPID|grep java`
       if [ -n "$RET" ];then
         return 0;
       else
         return 1;
       fi
    else
         return 1;
    fi
}

# Check if Server is running
#
# @RET  : 0 => the server process is running
#         1 => the server process is not running
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE : the name of the pid file


invoke_status(){
    if ( checkRunning );then
         PID="`cat $SERVER_PIDFILE`"
         echo "Server is running (pid '$PID')"
         exit 0
    fi
    echo "Server not running"
    exit 1
}

# Start Server if not already running
#
# @RET  : 0 => is now started, is already started
#         !0 => something went wrong
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE      : the name of the pid file
# - $SERVER_OPTS         : Additional options
# - $SERVER_SUNJMX_START : options for JMX settings
# - $SERVER_SSL_OPTS     : options for SSL encryption

invoke_start(){
    if ( checkRunning );then
      PID="`cat $SERVER_PIDFILE`"
      echo "INFO: Process with pid '$PID' is already running"
      exit 0
    fi

    SERVER_OPTS="$SERVER_OPTS $SERVER_SUNJMX_START $SERVER_SSL_OPTS -Djava.awt.headless=true -Djava.io.tmpdir=\"${SERVER_TMP}\""

    echo "INFO: Starting - inspect logfiles specified in logging.properties and log4j.properties to get details"
    invokeJar "$SERVER_PIDFILE" start
    exit "$?"
}

# Start Server in foreground (for debugging)
#
# @RET  : 0 => was started
#         1 => was already started
#         !0 => somthing went wrong
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE      : the name of the pid file
# - $SERVER_OPTS         : Additional options
# - $SERVER_SUNJMX_START : options for JMX settings
# - $SERVER_SSL_OPTS     : options for SSL encryption

invoke_console(){
    if ( checkRunning );then
      echo "ERROR: Server is already running"
      exit 1
    fi

    SERVER_OPTS="$SERVER_OPTS $SERVER_SUNJMX_START $SERVER_SSL_OPTS -Djava.awt.headless=true -Djava.io.tmpdir=\"${SERVER_TMP}\""

    COMMANDLINE_ARGS="start `echo $COMMANDLINE_ARGS|sed 's,^console,,'`"
    EXEC_OPTION="exec"
    echo "INFO: Starting in foreground, this is just for debugging purposes (stop process by pressing CTRL+C)"
    echo "INFO: Creating pidfile $SERVER_PIDFILE"
    echo "$$" > "$SERVER_PIDFILE"
    invokeJar "$SERVER_PIDFILE"
    RET="$?"
    echo "INFO: removing pidfile $SERVER_PIDFILE"
    exit "$RET"
}


# Kill Server
# @RET    : 0 => stop was successful
#          !0 => something went wrong
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE         : the name of the pid file


invoke_kill(){
    if ( checkRunning ); then
       SERVER_PID="`cat ${SERVER_PIDFILE}`"
       echo "INFO: sending SIGKILL to pid '$SERVER_PID'"
       kill -KILL $SERVER_PID
       RET="$?"
       rm -f "${SERVER_PIDFILE}"
       return $RET
    fi
    echo "INFO: not running, nothing to do"
    return 0
}


# Stop Server
# @RET    : 0 => stop was successful
#          !0 => something went wrong
#
# Note: This function uses globally defined variables
# - $SERVER_PIDFILE         : the name of the pid file
# - $SERVER_KILL_MAXSECONDS : the number of seconds to wait for termination of broker after sending
#                              shutdown signal by jmx interface

invoke_stop(){
    RET="1"
    if ( checkRunning );then
       SERVER_PID="`cat $SERVER_PIDFILE`"
       echo
       echo "INFO: sending SIGKILL to process"
       invoke_kill
       RET="$?"

    elif [ -f "$SERVER_PIDFILE" ];then
       echo "ERROR: No or outdated process id in '$SERVER_PIDFILE'"
       echo
       echo "INFO: Removing $SERVER_PIDFILE"
    else
       echo "Server not running"
       exit 0
    fi
    rm -f "$SERVER_PIDFILE" >/dev/null 2>&1
    rm -f "$SERVER_DATA/server.pid.stop" >/dev/null 2>&1
    exit $RET
}

# Invoke a task on a running Server instance
#
# $1    : "checkforrunning", do not invoke the task if server is not
#         active
#         <other>, invoke task always
# @RET  : 0 => successful
#         !0 => something went wrong
#
# Note: This function uses globally defined variables
# - $SERVER_QUEUEMANAGERURL : The url of the queuemanager
# - $SERVER_OPTS            : Additional options
# - $SERVER_SUNJMX_START    : options for JMX settings
# - $SERVER_SSL_OPTS        : options for SSL encryption
invoke_task(){

    local CHECKRUNNING="$1"
    SERVER_OPTS="$SERVER_OPTS $SERVER_SSL_OPTS"

    if [ "$CHECKRUNNING" = "checkforrunning" ];then
       if ( ! checkRunning );then
         echo "Server is not running."
         exit 1
       fi
    elif [ "$CHECKRUNNING" = "checkfornotrunning" ];then
      if ( checkRunning );then
         echo "Server is running."
         exit 1
       fi
    fi

    # call task in java binary
    if [ "$1" = "browse" ] && [ -n "$SERVER_QUEUEMANAGERURL" ];then
       COMMANDLINE_ARGS="$SERVER_QUEUEMANAGERURL `echo $COMMANDLINE_ARGS|sed 's,^browse,,'`"
    elif [ "$1" = "query" ]  && [ -n "$SERVER_QUEUEMANAGERURL" ];then
       COMMANDLINE_ARGS="$SERVER_SUNJMX_CONTROL `echo $COMMANDLINE_ARGS|sed 's,^query,,'`"
    else
       COMMANDLINE_ARGS="$COMMANDLINE_ARGS $SERVER_SUNJMX_CONTROL"
    fi
    invokeJar "$SERVER_PIDFILE"
    RET="$?"
    exit $RET
}

show_help() {
  invokeJar "$SERVER_PIDFILE"|sed "s,Usage: Main,Usage: $0,"
  cat << EOF
Tasks provided by the sysv init script:
    restart         - stop running instance (if there is one), start new instance
    status          - check if server process is running

Configuration of this script:
    The configuration of this script is read from the following files:
    $SERVER_CONFIGS
    This script searches for the files in the listed order and reads the first available file.
    Modify $SERVER_BASE/bin/env or create a copy of that file on a suitable location.
    To use additional configurations for running multiple instances on the same operating system
    rename or symlink script to a name matching to server-instance-<INSTANCENAME>.
    This changes the configuration location to /etc/default/server-instance-<INSTANCENAME> and
    \$HOME/.serverrc-instance-<INSTANCENAME>.
EOF
  exit 1
}

# ------------------------------------------------------------------------
# MAIN

# show help
if [ -z "$1" ];then
 show_help
fi

case "$1" in
  status)
    invoke_status
    ;;
  restart)
    if ( checkRunning );then
      $0 stop
      echo
    fi
    $0 start
    $0 status
    ;;
  start)
    invoke_start
    ;;
  stop)
    invoke_stop
    exit $?
    ;;
  *)
    invoke_task
    exit $?
esac
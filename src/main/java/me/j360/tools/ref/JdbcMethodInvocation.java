
package me.j360.tools.ref;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

/**
 * Invocation that reflected call for JDBC method.
 * 
 * @author gaohongtao
 */
@RequiredArgsConstructor
public class JdbcMethodInvocation {

    @Getter
    private final Method method;
    
    @Getter
    private final Object[] arguments;
    
    /**
     * Invoke JDBC method.
     * 
     * @param target target object
     */
    @SneakyThrows
    public void invoke(final Object target) {
        method.setAccessible(true);
        method.invoke(target, arguments);
    }
}

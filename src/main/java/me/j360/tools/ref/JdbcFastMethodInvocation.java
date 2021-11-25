
package me.j360.tools.ref;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Invocation that reflected call for JDBC method.
 * 
 * @author gaohongtao
 */
@RequiredArgsConstructor
public class JdbcFastMethodInvocation {

    private static ConcurrentMap<Class<?>, FastClass> fastClassMap = new ConcurrentHashMap<>();

    public static FastMethod build(final Class<?> clz, Method method) {
        FastClass fastClz = fastClassMap.get(clz);
        if (Objects.isNull(fastClz)) {
            fastClz = FastClass.create(clz);
            fastClassMap.putIfAbsent(clz, fastClz);
        }
        return fastClz.getMethod(method);
    }

    private static FastClass fastClz = FastClass.create(PreparedStatement.class);
    public static FastMethod get(Method method) {
        return fastClz.getMethod(method);
    }

    @Getter
    private final FastMethod method;
    
    @Getter
    private final Object[] arguments;
    
    /**
     * Invoke JDBC method.
     * 
     * @param target target object
     */
    @SneakyThrows
    public void invoke(final Object target) {
        method.invoke(target, arguments);
    }
}

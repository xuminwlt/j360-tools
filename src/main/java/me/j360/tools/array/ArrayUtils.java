package me.j360.tools.array;

import java.lang.reflect.Array;

/**
 * @author: min_xu
 * 说明：反射中Array的native操作性能分析
 */
public class ArrayUtils {

    public static Object get(Object[] array, int index) {
        return array[index];
    }

    public static Object getByReflect(Object array, int index) {
        return Array.get(array, index);
    }

    public static Object newArray(Class clazz, Object[] objects) {
        Object array = Array.newInstance(clazz, objects.length);
        for (int i = 0; i < objects.length; i++) {
            Array.set(array, i, objects[i]);
        }
        return array;
    }

    public static Object get(Class clazz, Object[] objects, int index) throws ArrayIndexOutOfBoundsException{
        Object array = Array.newInstance(clazz, objects.length);
        int length = Array.getLength(array);
        if (length <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < objects.length; i++) {
            Array.set(array, i, objects[i]);
        }
        return Array.get(array, index);
    }


    public static Integer getInt(Class clazz, Object[] objects, int index) throws ArrayIndexOutOfBoundsException{
        Object array = Array.newInstance(clazz, objects.length);
        int length = Array.getLength(array);
        if (length <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < objects.length; i++) {
            Array.set(array, i, objects[i]);
        }
        return Array.getInt(array, index);
    }


}

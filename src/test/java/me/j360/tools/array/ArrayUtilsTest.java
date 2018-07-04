package me.j360.tools.array;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: min_xu
 * 说明：
 */
public class ArrayUtilsTest {

    @Test
    public void get() {
        String[] array = new String[3];
        array[0] = "0";
        array[1] = "1";
        array[2] = "2";

        Object newArray = ArrayUtils.newArray(String.class, array);
        Object newResult = ArrayUtils.getByReflect(newArray, 2);
        Object result = ArrayUtils.get(array, 2);
        assertThat(newResult).isEqualTo(result);
    }


    @Test
    public void getInt() {
        Integer two = 2;
        Integer[] array = new Integer[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;

        int result = ArrayUtils.getInt(int.class, array, 2);
        assertThat(result).isEqualTo(two);
    }
}

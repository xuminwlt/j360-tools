package me.j360.tools.text;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: min_xu
 * @date: 2018/7/3 下午5:22
 * 说明：
 */

public class StringUtilsTest {

    @Test
    public void indexOf() {
        int result = StringUtils.indexOf("StringUtilsTest", "sTe");
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void indexOf2() {
        int result = StringUtils.indexOf("StringUtilsTest", "abaabaca");
        assertThat(result).isEqualTo(0);
    }
}

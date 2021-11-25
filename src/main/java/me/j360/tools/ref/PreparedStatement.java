package me.j360.tools.ref;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: min_xu
 * @date: 2019/1/28 10:31 PM
 * 说明：
 */
public class PreparedStatement {

    public Map<Integer, Object> map = new TreeMap<>();

    public void setObject(int i, Object o) {
        map.put(i, o);
    }
}

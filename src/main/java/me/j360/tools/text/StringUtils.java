package me.j360.tools.text;

/**
 * @author: min_xu
 * 说明：
 */

public class StringUtils {

    public static int indexOf(String str, String searchStr) {
        int[] next = getNext(searchStr);
        int i = 0;
        int j = 0;

        while (i < str.length()) {
            if (j == -1 || str.charAt(i) == searchStr.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }

            if (j == searchStr.length()) {
                return i - j;
            }
        }
        return -1;
    }

    private static int[] getNext(String searchStr) {
        int[] next = new int[searchStr.length()];
        next[0] = -1;
        int j = 0;
        int k = -1;

        while (j < searchStr.length() - 1) {
            if (k == -1 || searchStr.charAt(j) == searchStr.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

}

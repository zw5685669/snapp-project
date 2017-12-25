package com.snapp.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-13  13:08
 */
public class Common {

    /**
     * map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isCollectionEmpty(Map map) {
        boolean isEmpty = true;
        if (map == null) {
            return isEmpty;
        }
        isEmpty = map.isEmpty();
        return isEmpty;
    }

    /**
     * Collection是否为空
     *
     * @param c
     * @return
     */
    public static boolean isCollectionEmpty(Collection c) {
        boolean isEmpty = true;
        if (c == null) {
            return isEmpty;
        }
        isEmpty = c.isEmpty();
        return isEmpty;
    }
}

package com.snapp.common.study;

/**
 * <p>
 *     公用
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-20  11:28
 */
public class TestClass {

//    public static void main(String[] args) {
//        //要做的事  1.先输出1-100中的奇数偶数 2.再输出100 -1 中的偶数奇数
//        for (int i = 1; i <= 100; i++) {
//            if (i % 2 == 0) {
//                System.out.println(i + "偶数");
//            } else {
//                System.out.println(i + "奇数");
//            }
//        }
//        for (int i = 100; i >= 1; i--) {
//            if (i % 2 == 0) {
//                System.out.println(i + "偶数");
//            } else {
//                System.out.println(i + "奇数");
//            }
//        }
//    }

    public static void main(String[] args) {
        /*

         */
        for (int i = 1; i <= 100; i++) {
            String result = judge(i);
            System.out.println(result);
        }
        for (int i = 100; i >= 1; i--) {
            String result = judge(i);
            System.out.println(result);
        }
    }

    /**
     * 判断奇数还是偶数
     * @param i 输出参数 整数
     * @return 返回 字符串 说明输入参数为奇数还是偶数
     */
    public static String judge(int i) {
        if (i % 2 == 0) {
            return i + "偶数";
        } else {
            return i + "奇数";
        }
    }
}

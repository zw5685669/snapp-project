package com.snapp.common.study;

/**
 * <p>
 *     音乐测试类
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-22  11:06
 */
public class TestMusic {

    public static void main(String[] args) {
        Music music = new Music();
        //为新建的对象初始化属性。
        music.setMusicName("我想");
        music.setSinger("董昱昆");
        music.setAlbums("我不知道啊");

        music.play();
    }
}

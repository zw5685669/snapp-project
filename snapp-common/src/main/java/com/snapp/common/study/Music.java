package com.snapp.common.study;

/**
 * <p>
 *     音乐
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-22  10:28
 */
public class Music {

    /**
     * 音乐名称
     */
    private String musicName;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 专辑
     */
    private String albums;

    /**
     * 播放音乐
     */
    public void play() {
        System.out.println("音乐:" + musicName + ",播放啦啦啦啦啦啦啦啦啦。作者是：" + singer + "专辑我们这里就不加了，因为我懒");
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }
}

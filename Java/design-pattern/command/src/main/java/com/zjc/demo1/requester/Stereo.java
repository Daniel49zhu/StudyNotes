package com.zjc.demo1.requester;

public class Stereo {

    public void on() {
        System.out.println("打开音响");
    }

    public void setCD() {
        System.out.println("放入CD");
    }

    public void setVolume(int i) {
        System.out.println("设置播放音量为：" + i);
    }

    public void off(){
        System.out.println("关闭音响");
    }
}
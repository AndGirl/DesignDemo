package com.ybj.builderdemo;

/**
 * Created by 杨阳洋 on 2018/1/15.
 * 抽象构建类
 */

public abstract class Builder {

    //设置主机
    public abstract void buildBoard(String board);
    //设置显示器
    public abstract void buildDiaplay(String board);
    //设置操作系统
    public abstract void buildOS();
    //创建操作系统
    public abstract Computer create();

}

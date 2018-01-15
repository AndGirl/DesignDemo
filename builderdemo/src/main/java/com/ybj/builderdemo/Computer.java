package com.ybj.builderdemo;

/**
 * Created by 杨阳洋 on 2018/1/15.
 * 计算机抽象类，即UXML图中的Product角色
 */

public abstract class Computer {

    protected String mBoard;
    protected String mDisplay;
    protected String mOS;

    public Computer() {
    }

    public void setBoard(String board) {
        mBoard = board;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }

    public abstract void setOS();
    @Override
    public String toString() {
        return "Computer [mBoard=" + mBoard + ", mDisplay=" +mDisplay
                +", mOS=" + mOS+"]";
    }
}

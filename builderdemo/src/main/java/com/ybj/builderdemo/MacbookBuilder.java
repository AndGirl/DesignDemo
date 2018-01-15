package com.ybj.builderdemo;

/**
 * Created by 杨阳洋 on 2018/1/15.
 */

public class MacbookBuilder extends Builder {

    Computer mComputer = new MacBook();

    @Override
    public void buildBoard(String board) {
        mComputer.setBoard(board);
    }

    @Override
    public void buildDiaplay(String board) {
        mComputer.setDisplay(board);
    }

    @Override
    public void buildOS() {
        mComputer.setOS();
    }

    @Override
    public Computer create() {
        return mComputer;
    }
}

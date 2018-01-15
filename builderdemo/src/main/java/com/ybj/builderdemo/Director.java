package com.ybj.builderdemo;

/**
 * Created by 杨阳洋 on 2018/1/15.
 * 统一组装过程
 */

public class Director {

    Builder mBuilder = null;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    /**
     * 构建对象
     * @param board
     * @param display
     */
    public void construct(String board,String display){
        mBuilder.buildBoard(board);
        mBuilder.buildDiaplay(display);
        mBuilder.buildOS();
    }

}

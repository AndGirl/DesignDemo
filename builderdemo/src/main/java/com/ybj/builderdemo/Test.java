package com.ybj.builderdemo;

/**
 * Created by 杨阳洋 on 2018/1/15.
 */

public class Test {

    public static void main(String [] args){

        //构建器
        Builder mBuilder = new MacbookBuilder();
        //Director
        Director director = new Director(mBuilder);
        //封装构建过程
        director.construct("2G","Inter");
        System.out.println("Computer Info : " + mBuilder.create().toString());

    }

}

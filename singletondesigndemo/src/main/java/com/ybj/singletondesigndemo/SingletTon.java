package com.ybj.singletondesigndemo;

import java.io.Serializable;

/**
 * Created by 杨阳洋 on 2018/1/15.
 * 推荐使用此方法创建单列模式
 * 第一次调用getInstance（）方法会导致虚拟机记载SingletTonHolder类
 * 这种方法不仅能够确保线程安全，也能保证单例对象的唯一性，同时延迟了单例的实例化
 */

public class SingletTon implements Serializable{

    private SingletTon(){}

    public static SingletTon getInstance(){
        return SingletTonHolder.SINGLET_TON;
    }

    private static class SingletTonHolder{
        private static final SingletTon SINGLET_TON = new SingletTon();
    }


}

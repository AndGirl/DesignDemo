package com.ybj.singletondesigndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 单列设计模式-关键点
 * 1.构造函数不对外开放，一般为private
 * 2.通过一个静态方法或枚举返回单列类的对象
 * 3.确保单例类的对象有且只有一个，尤其在多线程环境下
 * 4.确保单列类对象在反序列化的时候不会重新创建对象
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

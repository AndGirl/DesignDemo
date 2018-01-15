package com.ybj.builderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Builder（构建者模式）
 * 定义:
 * 将一个复杂对象的构建与它的实现分离开来。使得同样的构建过程可以创建不同的表示
 *
 * 使用场景：
 * 1.相同方法不同顺序产生不同的事件结果
 * 2.产品类非常复杂
 * 3.初始化一个对象特别复杂，如参数多，且很多参数都具有默认值时。
 *
 * http://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/builder.html
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

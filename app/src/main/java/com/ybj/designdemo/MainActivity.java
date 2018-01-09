package com.ybj.designdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 图片加载器作为练习项目
 * 面向对象的六大原则
 * -单一职责原则
 * 一个类中应该是一组相关性很高的函数，数据的封装
 * -开闭原则
 * 对于修改是封闭的，对于扩展是开放的
 * -里氏替换原则
 * 所有引用基类的地方必须透明地使用其子类（实质：抽象）
 * -依赖倒置原则
 * 模块间的依赖通过抽象发生，实现类之间不发生直接的依赖关系，其依赖关系是通过接口或抽象类产生的
 * -接口隔离原则
 *
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

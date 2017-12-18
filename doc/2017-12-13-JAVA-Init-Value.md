---
title: JAVA 初始值问题
date: 2017-12-13 14:31:52
tags: JAVA
categories: JAVA
---
从C/C++转过来做JAVA的程序员一般会不自觉的关心变量的初始值问题，其实JAVA由于编译器的保护，初始值问题并没有C/C++那么突出。这里对初始值问题稍作分析。
JAVA的对象实质上是一块内存和操作这块一些函数。内存来维护对象的状态，这些状态在对象的生命周期中可能会改变。对象变量的初始值决定了对象的初始状态。
<!-- more -->

#### 函数内的局部变量
函数内的局部变量，如果没有初始化就使用，编译器直接报错，使用这类问题不会引起什么后果。

	Error:(xx, xx) java: variable xxxx might not have been initialized

#### 对象的成员变量

> 默认初始值

如果对象的成员变量没有通过任何方法初始化，在new出对象的时候，这些变量根据不同类型将会得到不同初始值.
boolean 会得到 false, byte, short, int, char, float, doubld 会得到各自类型的0， object reference 会得到 null。

> 构造函数

每一个类至少要有一个构造函数，如果没有声明任何构造函数，编译器则会自动创建一个默认的构造函数，默认构造函数时一个没有参数且函数体为空。
构造函数可以通过参数不同来实现重载。

> 初始化器

可以在声明变量的时候，给变量一个初始值。使用这种初始化方式时，可以引用已经声明的变量来初始化当前变量，但是不能前向引用

	//OK
    private int a = 10;
    private int b = a;
	//Error
    private int c = d;
    private int d = 10;

初始化器的另外一种形式时初始化块，可以在类里面定义任意块初始化块，初始化块会按照从前到后的顺序执行。初始化块可以处理异常，在有多个重载的构造函数时，可以用它来初始化各构造函数无差异的部分。


#### 初始化顺序

默认初始值 -> 声明时的初始化 -> 初始化块（从前到后） ->  构造函数


	
#### 参考
https://www.javaworld.com/article/2076614/core-java/object-initialization-in-java.html

#### 示例
https://github.com/HuanjunWang/JavaLearning/blob/master/src/Initialization.java
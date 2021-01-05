package com.example.bingfa;

import java.util.concurrent.FutureTask;

//继承方式的好处：方便传参，可以在子类中添加成员变量，通过set方法设置参数或者通过构造函数进行传递，
//使用runnable方式则只能使用主线程里面被声明为final的变量。不好：java不支持多线程，继承了thread类，那么子类就不能再继承其他类。
//前两种方式都没办法拿到任务返回的结果，futuretask可以
public class CallerTaskTest {

    public static void main(String[] args) {

        //创建异步任务
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        //启动线程
        new Thread(futureTask).start();
        try {
            //等待任务执行完毕，并返回结果
            String result = futureTask.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

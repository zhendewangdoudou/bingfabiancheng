package com.example.bingfa;

//两个视频共用一个task代码逻辑，如果需要，可以给RunnableTask添加参数进行任务区分，
//RunnableTask可以继承其他类，
//缺点：任务没有返回值
public class RunnableTaskTest {

    public static void main(String[] args) {
        RunnableTask runnableTask = new RunnableTask();
        new Thread(runnableTask).start();
        new Thread(runnableTask).start();
    }
}

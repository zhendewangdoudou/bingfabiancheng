package com.example.bingfa;

//当创建完thread对象后该线程并没有启动，直到调用了start方法后才真正的启动了线程
//调用start方法后，线程处于就绪状态，指该线程已经获得除CPU外的其他资源，等待获取cpu资源后才会真正处于运行状态，
//一旦run方法执行完毕，线程进入终止状态，

//好处：在run方法内获取当前线程直接使用this就可以了，无需使用Thread.currentThread（）方法
//不好：java不支持多线程，如果继承了thread类
public class ThreadTest {

    //继承thread类并重写run
    public static class MyThread extends Thread{

        @Override
        public void run(){
            System.out.println("---------");
        }
    }

    public static void main(String[] args) {

        //创建线程
        MyThread thread = new MyThread();
        //启动线程
        thread.start();
    }
}

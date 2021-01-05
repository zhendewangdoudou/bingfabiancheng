package com.example.bingfa;


/**
 * main函数里面启动了线程A和线程B，为了让线程A 先获取到锁，这里让B 先休眠了1s,
 * 线程A先后获取到共享变量resourceA和共享变量resourceB 上的锁
 * 然后调用resourceA 的wait()方法阻塞自己，阻塞自己后线程A 释放掉获取的resourceA上的锁
 *
 * 线程B休眠结束后，会首先尝试获取resourceA上的锁，如果当时线程A还没有调用wait()方法释放该锁，那么线程B会被阻塞
 * 当线程A释放了resourceA上的锁后，线程B 就会获取到resourceA上的锁，然后尝试获取resourceB 上的锁。
 * 由于线程A 调用的resourceA 上的wait（）方法，所以线程A挂起自己后并没有释放获取到的resourceB上的锁，
 * 所以线程B 尝试获取resourceB 上的锁时会被阻塞
 *
 * 证明了：当线程调用共享对象的wait（）方法时，当前线程只会释放当前共享对象的锁，当前线程持有的其他共享对象的监视器锁并不会被释放。
 */
public class SynchronizedTest {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadA get resourceA lock");

                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadA get resourceB lock");

                            //线程A阻塞，并释放获取到的resourceA锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //休眠1s
                    Thread.sleep(1000);

                    //获取resourceA 共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB try get resourceB lock...");

                        //获取resourceB 共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadB get resourceB lock");

                            //线程B 阻塞 并释放获取到的resourceA 的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();

        //等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");




    }



}

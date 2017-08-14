package com.rdx.newsSOA.Server;

/**
 * Created by youxiaoshuang on 2017/8/3.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
class RunableDemo implements Runnable {
    private Thread t;
    private String threadName;

    public RunableDemo(String name) {
        this.threadName = name;
        System.out.println( "创建线程：" + name );
    }

    private void start() {
        System.out.println( "开始线程: " + this.threadName );
        if (t == null) {
            t = new Thread( this, threadName );
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println( "执行线程：" + threadName );

        for (int i = 4; i > 0; i--) {
            System.out.println( "Thread:" + threadName + i );
            //让线程睡眠
            try {
                Thread.sleep( 50 );
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println( "线程 " + threadName + " 中断." );
            }
            System.out.println( "线程 " + threadName + " 退出." );
        }
    }


    public static class TestThread1 {
        public static void main(String[] args) {
            RunableDemo r1 = new RunableDemo( "Thread-1" );
            r1.run();
            RunableDemo r2 = new RunableDemo( "Thread-2" );
            r2.run();
        }

    }

}

package com.rdx.newsSOA.Server;

import java.util.HashMap;

/**
 * Created by youxiaoshuang on 2017/8/1.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class Main {
    public Main() throws Exception {
        QueueConsumer consumer = new QueueConsumer( "queue" );
        Thread consumerThread = new Thread( consumer );
        consumerThread.start();

        Producer producer = new Producer( "queue" );

        for (int i = 0; i < 100000; i++) {
            HashMap message = new HashMap();
            message.put( "message number", i );
            producer.sendMessage( message );
            System.out.println( "Message Number " + i + " sent." );
        }
    }

    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

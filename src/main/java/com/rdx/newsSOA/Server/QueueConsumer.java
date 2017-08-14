package com.rdx.newsSOA.Server;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by youxiaoshuang on 2017/8/1.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class QueueConsumer extends EndPoint implements Runnable, Consumer {
    public QueueConsumer(String endpointName) throws IOException {
        super( endpointName );
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println( "Consumer " + consumerTag + " registered" );
    }

    @Override
    public void handleCancelOk(String s) {

    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize( body );
        System.out.println( "Message Number " + map.get( "message number" ) + " received." );
    }

    @Override
    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume( endPointName, true, this );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

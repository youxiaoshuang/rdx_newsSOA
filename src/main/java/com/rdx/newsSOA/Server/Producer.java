package com.rdx.newsSOA.Server;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/8/1.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class Producer extends EndPoint {

    public Producer(String endpointName) throws IOException {
        super( endpointName );
    }


    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish( "", endPointName, null, SerializationUtils.serialize( object ) );
    }
}

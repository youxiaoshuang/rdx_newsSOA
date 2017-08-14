package com.rdx.newsSOA.Server;

import com.rdx.newsSOA.util.JsonTool;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by youxiaoshuang on 2017/8/2.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class Redis {
    private Jedis jedis;

    public Redis() {
        Jedis jedis = new Jedis( "localhost" );
        jedis.auth( "yxsredis" );
        this.jedis = jedis;
    }

    public Long hashSet(String key, String filed, String value) {
        Long hset = jedis.hset( key, filed, value );
        return hset;
    }

    public String hashGet(String key, String filed) {
        String hget = jedis.hget( key, filed );
        return hget;
    }

    public Map<String, String> hashGetAll(String key) {
        Map<String, String> stringStringMap = jedis.hgetAll( key );
        return stringStringMap;
    }


    /**
     * lpush 自己理解 left push 在左边 即头部加入
     *
     * @param key
     * @param value
     */
    public void listLeftPush(String key, String value) {
        Long lpush = jedis.lpush( key, value );
    }

    public void listRightPush(String key, String value) {
        jedis.rpush( key, value );
    }

    public List<String> getListPush(String key, long start, long end) {
        List<String> lrange = jedis.lrange( key, start, end );
        return lrange;
    }


    public String set(String key, String value) {
        String set = jedis.set( key, value );
        return set;
    }

    public Set<String> getSet(String key) {
        Set<String> smembers = jedis.smembers( key );
        return smembers;
    }

    public static void main(String[] args) {
        Redis redis = new Redis();
        System.out.println( "****************** hashSet Start ********************" );
        Long aLong = redis.hashSet( "me", "name", "尤晓霜" );
        Long aLong1 = redis.hashSet( "me", "age", "25" );

        String s = redis.hashGet( "me", "name" );
        System.out.println( "hashGet取值：" + s );

        Map<String, String> me = redis.hashGetAll( "me" );
        System.out.println( "读取hashGetAll的值" + me );
        Set<Map.Entry<String, String>> entries = me.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            System.out.println( "hashGetAll取所有的key：" + key );
        }
        String mame = me.get( "name" );
        System.out.println( "读取hashGetAll返回的map中name对应的值" + mame );
        System.out.println( "****************** push Start **********************" );
        int i = 0;
        while (i < 10) {
            redis.listLeftPush( "number", "" + i );
            redis.listRightPush( "number", "" + i );
            i++;
        }
        List<String> listPush = redis.getListPush( "number", 0, 100 );
        System.out.println( "getListPush获取列表中的值" + JsonTool.writeValueAsString( listPush ) );
    }
}

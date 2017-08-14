package com.rdx.newsSOA.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxiaoshuang on 2017/4/13.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class ListUtil {
    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%n;  //(先计算出余数)
        int number=source.size()/n;  //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }


    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<Integer> integers=new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        List<List<Integer>> lists=averageAssign(integers, 2);
        for (final List<Integer> list : lists) {
            System.out.println( list );
        }
    }
}

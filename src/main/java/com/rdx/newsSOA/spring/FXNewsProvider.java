package com.rdx.newsSOA.spring;

import org.apache.commons.lang.ArrayUtils;

/**
 * Created by youxiaoshuang on 2017/5/23.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class FXNewsProvider {
    private IFXNewsListener newsListener;
    private IFXNewsPersister newsPersister;

    public void getAndPersistNews(){
        String[] newsIds = newsListener.getAvailableNewsIds();
        if(ArrayUtils.isEmpty( newsIds )){
            return;
        }
        for (String newsId : newsIds) {

        }
    }

}

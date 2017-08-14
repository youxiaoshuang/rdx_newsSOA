package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.common.constants.SessionConstants.Global.RedisKeyEnum;
import com.rdx.newsSOA.dao.NDoucumentMapper;
import com.rdx.newsSOA.face.StatisticsService;
import com.rdx.newsSOA.face.serviceModel.DocBrowseLogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 网站访问统计
 * Created by youxiaoshuang on 2017/6/28.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Service(value = "statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
//    @Autowired
//    private RedisClientAdapterImpl redisClientAdapter;
    @Autowired
    private NDoucumentMapper nDoucumentMapper;

    private String web_keyName = RedisKeyEnum.WEB_BROWSER.getKeyName();
    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    @Override
    public long addWebBrowser() {
        logger.info( "新增一条网站访问量" );
//        long incr = redisClientAdapter.incr( web_keyName );
        return 1;
    }

    @Override
    public long adddocrowser(Integer docId, Integer userId) {
        logger.info( "1.新增一条网站访问量" );
        addWebBrowser();
        logger.info( "2.新增一条文章访问量" );
        String doc_keyName = RedisKeyEnum.getDocKeyName( docId );
//        long incr = redisClientAdapter.incr( doc_keyName );
        logger.info( "3.新增一条文章访问记录" );
        DocBrowseLogModel docBrowseLogModel = new DocBrowseLogModel();
        docBrowseLogModel.setDocId( docId );
        docBrowseLogModel.setUserId( (userId==null)?userId:0 );
        docBrowseLogModel.setCreateTime( new Date(  ) );
        docBrowseLogModel.setUpdateTime( new Date(  ) );
        nDoucumentMapper.insertDocBrowser( docBrowseLogModel );
        return 1;
    }
}

package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.dao.OrderEntityMapper;
import com.rdx.newsSOA.dto.*;
import com.rdx.newsSOA.face.EstateService;
import com.rdx.newsSOA.spider.EstateBuildProcessor;
import com.rdx.newsSOA.spider.EstateDanYuanProcessor;
import com.rdx.newsSOA.spider.EstateListProcessor;
import com.rdx.newsSOA.spider.EstateRoomProcessor;
import com.rdx.newsSOA.util.JsonTool;
import com.rdx.newsSOA.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by youxiaoshuang on 2017/4/11.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Service
@Scope("prototype")
public class EstateServiceImpl implements EstateService {
    @Autowired
    private OrderEntityMapper orderEntityMapper;
    @Autowired
    private EstateListProcessor estateListProcessor;
    @Autowired
    private EstateBuildProcessor estateBuildProcessor;
    @Autowired
    private EstateDanYuanProcessor estateDanYuanProcessor;
    @Autowired
    private EstateRoomProcessor estateRoomProcessor;
    @PostConstruct
    public void init() {
        estateListProcessor.setEstateService( this );
        estateBuildProcessor.setEstateService( this );
        estateDanYuanProcessor.setEstateService( this );
        estateRoomProcessor.setEstateService( this );
    }
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void spiderGggEstateInfo() {
        List<EstateInfo> fggEstateInfos = orderEntityMapper.selectFgg();
        logger.info( "查询estate_info表" + JsonTool.writeValueAsString( fggEstateInfos ) );
        List<List<EstateInfo>> lists = ListUtil.averageAssign( fggEstateInfos, 2);
        for (final List<EstateInfo> list : lists) {
            logger.info( "异步爬取小区："+JsonTool.writeValueAsString( list ) );
            threadPoolTaskExecutor.execute( new Runnable() {
                @Override
                public void run() {
                    job( list );
                }
            } );
        }

    }

    public void job(List<EstateInfo> estateInfos){
        if (estateInfos != null) {
            for (EstateInfo fggEstateInfo : estateInfos) {
                EstateSearchRequest estateSearchRequest = new EstateSearchRequest();
                estateSearchRequest.setAreaName( fggEstateInfo.getEstateName() );
                estateSearchRequest.setCityName( fggEstateInfo.getCityName() );
                estateSearchRequest.setEstateName( fggEstateInfo.getEstateName() );
                estateSearchRequest.setEstateId( fggEstateInfo.getId() );
                logger.info( "爬取小区 ：" + fggEstateInfo.getEstateName() + "的信息 start" );
                logger.info( "异步爬取小区" );
//                threadPoolTaskExecutor.execute( new Runnable() {
//                    @Override
//                    public void run() {
                estateListProcessor.getEstateList( estateSearchRequest, estateListProcessor );
//                    }
//                } );
                logger.info( "更新小区状态" );
                orderEntityMapper.updateStatusIsSpider( fggEstateInfo.getId() );
                logger.info( "爬取小区 ：" + fggEstateInfo.getEstateName() + "的信息 end" );


            }
        }
    }

    @Override
    public EstateSearchResponse insertEstate(EstateSearchResponse estateSearchResponse) {
        EstateSearchResponse estateSearchResponse1 = orderEntityMapper.selectByEstateId( estateSearchResponse.getEstateId() );
        if (estateSearchResponse1 != null) {
            estateSearchResponse.setId( estateSearchResponse1.getId() );
            return estateSearchResponse;
        }
        logger.info( "插入数据" + estateSearchResponse );
        orderEntityMapper.insertEstateSearchResponse( estateSearchResponse );
        return estateSearchResponse;
    }

    @Override
    public void spiderGggBuildInfo(final BuildingSeachRequest buildingSeachRequest) {
        logger.info( "异步爬取楼栋"+buildingSeachRequest );
        threadPoolTaskExecutor.execute( new Runnable() {
            @Override
            public void run() {
                estateBuildProcessor.getBuildList( buildingSeachRequest, estateBuildProcessor );
            }
        } );
    }

    @Override
    public void spiderGggRoomInfo(final RoomSeachRequest roomSeachRequest) {
        logger.info( "异步爬取室号"+roomSeachRequest );
        threadPoolTaskExecutor.execute( new Runnable() {
            @Override
            public void run() {
                estateRoomProcessor.getRoomList( roomSeachRequest, estateRoomProcessor );
            }
        } );
    }

    @Override
    public void spiderGggDanYuanInfo(final DanYuanSeachRequest danYuanSeachRequest) {
        logger.info( "异步爬取单元"+danYuanSeachRequest );
        threadPoolTaskExecutor.execute( new Runnable() {
            @Override
            public void run() {
                estateDanYuanProcessor.getDanYuanList( danYuanSeachRequest, estateDanYuanProcessor );
            }
        } );
    }

    @Override
    public BuildingSeachResponse insertBuild(BuildingSeachResponse buildingSeachResponse) {
        orderEntityMapper.insertEstateBuild( buildingSeachResponse );
        return buildingSeachResponse;
    }

    @Override
    public DanYuanSeachResponse insertDanYuan(DanYuanSeachResponse danYuanSeachResponse) {
        orderEntityMapper.insertEstateDanYuan( danYuanSeachResponse );
        return danYuanSeachResponse;
    }

    @Override
    public RoomSeachResponse insertRoom(RoomSeachResponse roomSeachResponse) {
        orderEntityMapper.insertEstateRoom( roomSeachResponse );
        return roomSeachResponse;
    }



}

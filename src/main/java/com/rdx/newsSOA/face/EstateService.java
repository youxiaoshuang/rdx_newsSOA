package com.rdx.newsSOA.face;

import com.rdx.newsSOA.dto.*;

/**
 * Created by youxiaoshuang on 2017/4/11.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public interface EstateService {

    /**
     * 爬取房估估 小区信息
     */
    public void spiderGggEstateInfo();

    public EstateSearchResponse insertEstate(EstateSearchResponse estateSearchResponse);

    public void spiderGggBuildInfo(BuildingSeachRequest buildingSeachRequest);

    public void spiderGggRoomInfo(RoomSeachRequest roomSeachRequest);

    public void spiderGggDanYuanInfo(DanYuanSeachRequest danYuanSeachRequest);

    public BuildingSeachResponse insertBuild(BuildingSeachResponse buildingSeachResponse);

    public DanYuanSeachResponse insertDanYuan(DanYuanSeachResponse danYuanSeachResponse);

    public RoomSeachResponse insertRoom(RoomSeachResponse roomSeachResponse);
}

package com.rdx.newsSOA.dao;

import com.rdx.newsSOA.dto.*;
import com.rdx.newsSOA.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderEntity record);

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderEntity record);

    int updateByPrimaryKey(OrderEntity record);

    List<EstateInfo> selectFgg();

    int updateStatusIsSpider(@Param( "estateId" ) Integer estateId);

    int insertEstateSearchResponse(EstateSearchResponse estateSearchResponse);

    int insertEstateBuild(BuildingSeachResponse buildingSeachResponse);

    int insertEstateRoom(RoomSeachResponse roomSeachResponse);

    int insertEstateDanYuan(DanYuanSeachResponse danYuanSeachResponse);

    EstateSearchResponse selectByEstateId(@Param( "estateId" ) Integer estateId);

}
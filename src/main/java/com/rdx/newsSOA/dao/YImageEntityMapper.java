package com.rdx.newsSOA.dao;

import com.rdx.newsSOA.entity.YImageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YImageEntityMapper {
    int deleteByPrimaryKey(Integer imageid);

    int insert(YImageEntity record);

    int insertSelective(YImageEntity record);

    YImageEntity selectByPrimaryKey(Integer imageid);

    int updateByPrimaryKeySelective(YImageEntity record);

    int updateByPrimaryKey(YImageEntity record);

	List<YImageEntity> selectImagesPage(YImageEntity image);
	
	YImageEntity selectFtpUtlByName(@Param(value = "Name") String imageName);
}
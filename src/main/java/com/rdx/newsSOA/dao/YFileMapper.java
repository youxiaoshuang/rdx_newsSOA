package com.rdx.newsSOA.dao;

import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.entity.YFileExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YFileMapper {
    int countByExample(YFileExample example);

    int deleteByExample(YFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YFile record);

    int insertSelective(YFile record);

    List<YFile> selectByExample(YFileExample example);

    YFile selectByPrimaryKey(Integer id);

    List<YFile> selectByDocId(@Param("docId") Integer docId);

    YFile selectByKey(@Param("key") String key);

    int updateByExampleSelective(@Param("record") YFile record, @Param("example") YFileExample example);

    int updateByExample(@Param("record") YFile record, @Param("example") YFileExample example);

    int updateByPrimaryKeySelective(YFile record);

    int updateByPrimaryKey(YFile record);
}
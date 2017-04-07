package com.rdx.newsSOA.dao;

import com.rdx.newsSOA.entity.YCodeExample;
import com.rdx.newsSOA.entity.YCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YCodeMapper {
    int countByExample(YCodeExample example);

    int deleteByExample(YCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(YCode record);

    int insertSelective(YCode record);

    List<YCode> selectByExample(YCodeExample example);

    YCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") YCode record, @Param("example") YCodeExample example);

    int updateByExample(@Param("record") YCode record, @Param("example") YCodeExample example);

    int updateByPrimaryKeySelective(YCode record);

    int updateByPrimaryKey(YCode record);
}
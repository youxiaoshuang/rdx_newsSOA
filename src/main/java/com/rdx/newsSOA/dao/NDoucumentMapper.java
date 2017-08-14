package com.rdx.newsSOA.dao;

import com.rdx.newsSOA.dto.ImageModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.entity.NDoucumentExample;
import com.rdx.newsSOA.dto.DocumentModel;
import com.rdx.newsSOA.dto.Page;
import com.rdx.newsSOA.face.serviceModel.DocBrowseLogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NDoucumentMapper {
    int countByExample(NDoucumentExample example);

    int deleteByExample(NDoucumentExample example);

    int deleteByPrimaryKey(Integer id);

    int insertDocBrowser(DocBrowseLogModel docBrowseLogModel);

    int insert(NDoucument record);

    int insertSelective(NDoucument record);

    List<NDoucument> selectByExample(NDoucumentExample example);

    List<NDoucument> selectByAll();

    List<NDoucument> selectBanner();

    List<DocumentModel> selectByRefresh(String uuid);

    List<NDoucument> selectByPull(Page page);

    NDoucument selectByPrimaryKey(Integer id);

    NDoucument selectByCode(@Param("code") String code);

    int updateByExampleSelective(@Param("record") NDoucument record, @Param("example") NDoucumentExample example);

    int updateByExample(@Param("record") NDoucument record, @Param("example") NDoucumentExample example);

    int updateByPrimaryKeySelective(NDoucument record);

    int updateByPrimaryKey(NDoucument record);

    int updateByUuid(NDoucument record);

    /**
     * @param title
     * @return
     */
    NDoucument selectByMd5(@Param("md5") String title);

    List<NDoucument> selectHotNews(Page page);

    List<NDoucument> selectHotNewsByRefresh(Page page);

    List<NDoucument> selectHotNewsByPull(Page page);

    List<NDoucument> selectQuTu(Page page);

    List<DocumentModel> selectQuTuByRefresh(String uuid);

    List<NDoucument> selectQuTuByPull(Page page);

    List<NDoucument> selectDuanZi(Page page);

    List<DocumentModel> selectDuanZiByRefresh(String uuid);

    List<NDoucument> selectDuanZiByPull(Page page);

    List<DocumentModel> selectNews2();

    List<DocumentModel> selectQuTu2();

    List<DocumentModel> selectDZ2();

    List<ImageModel> selectImgByNews(@Param("docId") Integer docId);

    Integer selectMaxId();

    List<DocumentModel> selectImageModelById(@Param("id") Integer id, @Param("number") Integer number);

    List<DocumentModel> selectImageModelNewsById(@Param("id") Integer id, @Param("number") Integer number);
    List<DocumentModel> selectImageModelQtById(@Param("id") Integer id, @Param("number") Integer number);
    List<DocumentModel> selectImageModelDzById(@Param("id") Integer id, @Param("number") Integer number);




}

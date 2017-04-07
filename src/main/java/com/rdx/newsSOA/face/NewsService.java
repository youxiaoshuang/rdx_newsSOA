package com.rdx.newsSOA.face;


import com.rdx.newsSOA.dao.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.face.serviceModel.Response;

import java.util.List;

/**
 * Created by youxiaoshuang on 16/7/28.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface NewsService {
    public NDoucument addNews(NDoucument nDoucument);

    /**
     * 新增头条新闻
     * @param touTiaoNewModel
     * @return
     */
    NDoucument addTTNews(TouTiaoNewModel touTiaoNewModel);


    List<NDoucument> findNews();

    Response refreshNews(String uuid);

    List<NDoucument> findPullNews(String uuid);

    List<NDoucument> findHotNews();

    List<NDoucument> findefreshHotNews(String uuid);

    List<NDoucument> findePullHotNews(String uuid);

    List<NDoucument> findDuanZi();

    Response findefreshDuanZi(String uuid);

    List<NDoucument> findefPullDuanZi(String uuid);

    List<NDoucument> findQuTu();

    Response findefreshQuTu(String uuid);

    List<NDoucument> findePullQuTu(String uuid);

    NDoucument addDefaultNews();

    NDoucument getNewsDetailByUuid(String code);


    Response getHomeData();

    Response getLikeData();

    Response getRandomNewsData();

    Response getRandomQtData();

    Response getRandomDzData();


}

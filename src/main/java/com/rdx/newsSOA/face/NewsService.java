package com.rdx.newsSOA.face;


import com.rdx.newsSOA.dto.TouTiaoNewModel;
import com.rdx.newsSOA.dto.ImageModel;
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

    List<NDoucument> findBanner();

    List<ImageModel> selectImgByNews(Integer docId);

    Response refreshNews(String uuid);

    /**
     * 获取首页Banner 五个图
     * @return
     */
    List<NDoucument> getBanner();

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


    Response<String> pushNewsToBBS(NDoucument doucument);

}

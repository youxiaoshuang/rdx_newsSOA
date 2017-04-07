package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.dao.NDoucumentMapper;
import com.rdx.newsSOA.dao.TouTiaoNewModel;
import com.rdx.newsSOA.dao.YFileMapper;
import com.rdx.newsSOA.dto.DocumentModel;
import com.rdx.newsSOA.dto.ImageModel;
import com.rdx.newsSOA.dto.Page;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.face.NewsService;
import com.rdx.newsSOA.face.serviceModel.Response;
import com.rdx.newsSOA.util.DateUtils;
import com.rdx.newsSOA.util.PageParameter;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by youxiaoshuang on 16/7/28.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NDoucumentMapper nDoucumentMapper;
    @Autowired
    private YFileMapper yFileMapper;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    public NDoucument addNews(NDoucument nDoucument) {
        String uuId = UUID.randomUUID().toString().replace( "-", "" );
        nDoucument.setStatus( 1 );
        nDoucument.setCreatetime( new Date() );
        nDoucumentMapper.updateByUuid( nDoucument );
        return nDoucument;
    }

    @Override
    public NDoucument addTTNews(TouTiaoNewModel touTiaoNewModel) {
        //先判断新闻是否添加过 根据title判断
        NDoucument nDoucument = new NDoucument();
        NDoucument doucument = nDoucumentMapper.selectByTitle( touTiaoNewModel.getTitle() );
        if (doucument == null) {
            String uuId = UUID.randomUUID().toString().replace( "-", "" );
            nDoucument.setUuid( uuId );
            nDoucument.setStatus( 1 );
            nDoucument.setCreatetime( new Date() );
            nDoucument.setSourceUrl( touTiaoNewModel.getUrl() );
            nDoucument.setSourceType( touTiaoNewModel.getSourceTye() );
            nDoucument.setTitle( touTiaoNewModel.getTitle() );
            nDoucument.setContent( touTiaoNewModel.getContent() );
            nDoucument.setStatus( 1 );
            nDoucument.setDesc( touTiaoNewModel.getDesc() );
            nDoucumentMapper.insertSelective( nDoucument );
            //插入
            List<YFile> yFiles = touTiaoNewModel.getYFiles();
            if (yFiles != null && yFiles.size() > 0) {
                for (YFile yFile : yFiles) {
                    yFile.setDocid( nDoucument.getId() );
                    int i = yFileMapper.insertSelective( yFile );
                }
            }
        }

        return nDoucument;
    }

    public List<NDoucument> findNews() {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectByAll( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public Response refreshNews(String uuid) {
        Response response = new Response();
        List<DocumentModel> documentModels = nDoucumentMapper.selectByRefresh( uuid );
        putImages( documentModels );
        response.setStatus( 1 );
        response.setData( documentModels );
        response.setMsg( "success" );
        return response;
    }

    @Override
    public List<NDoucument> findPullNews(String uuid) {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        page.setUuid( uuid );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectByPull( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public List<NDoucument> findHotNews() {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectHotNews( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public List<NDoucument> findefreshHotNews(String uuid) {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        page.setUuid( uuid );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectHotNewsByRefresh( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public List<NDoucument> findePullHotNews(String uuid) {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        page.setUuid( uuid );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectHotNewsByPull( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public List<NDoucument> findDuanZi() {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectDuanZi( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public Response findefreshDuanZi(String uuid) {
        Response response = new Response();
        List<DocumentModel> documentModels = nDoucumentMapper.selectDuanZiByRefresh( uuid );
        putImages( documentModels );
        response.setStatus( 1 );
        response.setData( documentModels );
        response.setMsg( "success" );
        return response;
    }

    @Override
    public List<NDoucument> findefPullDuanZi(String uuid) {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        page.setUuid( uuid );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectDuanZiByPull( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public List<NDoucument> findQuTu() {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectQuTu( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }

    @Override
    public Response findefreshQuTu(String uuid) {
        Response response = new Response();
        List<DocumentModel> documentModels = nDoucumentMapper.selectQuTuByRefresh( uuid );
        putImages( documentModels );
        response.setStatus( 1 );
        response.setData( documentModels );
        response.setMsg( "success" );
        return response;
    }

    @Override
    public List<NDoucument> findePullQuTu(String uuid) {
        PageParameter pageParameter = new PageParameter( 1, 5 );
        Page page = new Page();
        page.setParameter( pageParameter );
        page.setUuid( uuid );
        List<NDoucument> nDoucuments = nDoucumentMapper.selectQuTuByPull( page );
        for (NDoucument nDoucument : nDoucuments) {
            List<YFile> yFiles = yFileMapper.selectByDocId( nDoucument.getId() );
            nDoucument.setImages( yFiles );
            String s = DateUtils.formatDateStrForPage( nDoucument.getCreatetime() );
            nDoucument.setPublishTime( s );
        }
        return nDoucuments;
    }


    public NDoucument addDefaultNews() {
        String uuId = UUID.randomUUID().toString().replace( "-", "" );
        NDoucument nDoucument = new NDoucument();
        nDoucument.setStatus( 0 );
        nDoucument.setUuid( uuId );
        nDoucumentMapper.insertSelective( nDoucument );
        return nDoucument;
    }

    public NDoucument getNewsDetailByUuid(String code) {
        NDoucument nDoucument = nDoucumentMapper.selectByCode( code );
        return nDoucument;
    }

    /**
     * 获取首页列表显示
     *
     * @return
     */
    @Override
    public Response getHomeData() {
        Response response = new Response();
        Map<String, Object> map = new HashedMap();
        //获取新鲜事 新闻 两条
        List<DocumentModel> xxsLists = nDoucumentMapper.selectNews2();
        putImages( xxsLists );

        map.put( "xxs", xxsLists );
        //获取趣图两条
        List<DocumentModel> qutuList = nDoucumentMapper.selectQuTu2();
        putImages( qutuList );
        map.put( "qutu", qutuList );
        //获取段子两条
        List<DocumentModel> dzlist = nDoucumentMapper.selectDZ2();
        map.put( "dz", dzlist );

        response.setData( map );
        response.setMsg( "success" );
        response.setStatus( 1 );
        return response;
    }

    //获取猜你喜欢列表
    @Override
    public Response getLikeData() {
        Response<List<DocumentModel>> response = new Response<List<DocumentModel>>();
        List<DocumentModel> ranDownDoc = getRanDownDoc();
        putImages( ranDownDoc );
        response.setStatus( 1 );
        response.setMsg( "success" );
        response.setData( ranDownDoc );
        return response;
    }

    @Override
    public Response getRandomNewsData() {
        Response<List<DocumentModel>> response = new Response<List<DocumentModel>>();
        List<DocumentModel> ranDownNews = getRanDownNews();
        putImages( ranDownNews );
        response.setStatus( 1 );
        response.setMsg( "success" );
        response.setData( ranDownNews );
        return response;
    }

    @Override
    public Response getRandomQtData() {
        Response<List<DocumentModel>> response = new Response<List<DocumentModel>>();
        List<DocumentModel> ranDownQt = getRanDownQt();
        putImages( ranDownQt );
        response.setStatus( 1 );
        response.setMsg( "success" );
        response.setData( ranDownQt );
        return response;
    }

    @Override
    public Response getRandomDzData() {
        Response<List<DocumentModel>> response = new Response<List<DocumentModel>>();
        List<DocumentModel> ranDownDz = getRanDownDz();
        putImages( ranDownDz );
        response.setStatus( 1 );
        response.setMsg( "success" );
        response.setData( ranDownDz );
        return response;
    }

    /**
     * 随机获取多条内容
     *
     * @return
     */
    public List<DocumentModel> getRanDownDoc() {
        int i = 10;//随机获取十条
        //获取新闻最大的id
        Integer maxId = nDoucumentMapper.selectMaxId();
        double d = Math.random();
        int maxid = (int) (d * maxId);
        List<DocumentModel> documentModels = nDoucumentMapper.selectImageModelById( maxid, i );
        if (documentModels == null) {
            return getRanDownDoc();
        }
        return documentModels;
    }

    /**
     * 随机获取多条新闻
     *
     * @return
     */
    public List<DocumentModel> getRanDownNews() {
        int i = 10;//随机获取十条
        //获取新闻最大的id
        Integer maxId = nDoucumentMapper.selectMaxId();
        double d = Math.random();
        int maxid = (int) (d * maxId);
        List<DocumentModel> documentModels = nDoucumentMapper.selectImageModelNewsById( maxid, i );
        if (documentModels == null) {
            return getRanDownDoc();
        }
        return documentModels;
    }

    /**
     * 随机获取一条趣图
     *
     * @return
     */
    public List<DocumentModel> getRanDownQt() {
        int i = 10;//随机获取十条
        //获取新闻最大的id
        Integer maxId = nDoucumentMapper.selectMaxId();
        double d = Math.random();
        int maxid = (int) (d * maxId);
        List<DocumentModel> documentModels = nDoucumentMapper.selectImageModelQtById( maxid, i );
        if (documentModels == null) {
            return getRanDownDoc();
        }
        return documentModels;
    }

    /**
     * 随机获取一条段子
     *
     * @return
     */
    public List<DocumentModel> getRanDownDz() {
        int i = 10;//随机获取十条
        //获取新闻最大的id
        Integer maxId = nDoucumentMapper.selectMaxId();
        double d = Math.random();
        int maxid = (int) (d * maxId);
        List<DocumentModel> documentModels = nDoucumentMapper.selectImageModelDzById( maxid, i );
        if (documentModels == null) {
            return getRanDownDoc();
        }
        return documentModels;
    }

    public void putImages(List<DocumentModel> documentModels){
        for (DocumentModel ranDownNew : documentModels) {
            if (ranDownNew != null) {
                List<ImageModel> imageModels = nDoucumentMapper.selectImgByNews( ranDownNew.getId() );
                if (imageModels != null && imageModels.size() > 0) {
                    ranDownNew.setImageSize( imageModels.size() );
                    ranDownNew.setImages( imageModels );
                }
            }
        }
    }


}

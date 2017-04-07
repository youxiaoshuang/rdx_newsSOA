package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.dao.NDoucumentMapper;
import com.rdx.newsSOA.dao.YFileMapper;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.face.FileService;
import com.rdx.newsSOA.face.serviceModel.FileModel;
import com.rdx.newsSOA.util.FileUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by youxiaoshuang on 2016/9/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private YFileMapper yFileMapper;
    @Autowired
    private NDoucumentMapper nDoucumentMapper;
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Value( "#{sys['httpPath']}" )
    private String httpPath;


    public FileModel uploadLocalFile(FileModel fileModel) {
        try {
            FileUitl.toFile(fileModel.getBaseStr(),fileModel.getServerPath());
            fileModel.setUrl( httpPath+"fileCenter/getFile/"+fileModel.getKey() );
        } catch (Exception e) {
            e.printStackTrace();
            logger.info( "保存图片失败:"+e );
        }
        return fileModel;
    }

    public void saveFile(FileModel fileModel) {
        //根据docCode查询文章详情
        NDoucument nDoucument = nDoucumentMapper.selectByCode( fileModel.getDocCode());
        YFile yFile = new YFile();
        yFile.setUrl( fileModel.getUrl() );
        yFile.setKey( fileModel.getKey() );
        yFile.setDocid( nDoucument.getId() );
        yFile.setFiletype( fileModel.getFileType() );
        yFile.setNewname( fileModel.getNewName() );
        yFile.setOriginalname( fileModel.getOriginalName() );
        yFile.setServerpath( fileModel.getServerPath() );
        yFile.setSize( fileModel.getSize() );
        int i = yFileMapper.insertSelective( yFile );
    }

    public YFile getFileByKey(String key) {
        YFile yFile = yFileMapper.selectByKey( key );
        return yFile;
    }

//    public File uploadLocalFile(MultipartFile multipartFile, String savePath, String newName) {
//        try {
//            multipartFile.transferTo( new java.io.File( savePath ) );
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        YFile yFile = new YFile();
//        File file = new File();
//        yFile.setOriginalname( multipartFile.getOriginalFilename() );
//        yFile.setFiletype( multipartFile.getContentType() );
//        yFile.setNewname( newName );
//        yFile.setSize( multipartFile.getSize() );
//        yFileMapper.insertSelective( yFile );
//        BeanUtils.copyProperties( yFile, file);
//
//        return file;
//    }
}

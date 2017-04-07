package com.rdx.newsSOA.face;

import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.face.serviceModel.FileModel;

/**
 * 文件上传服务接口
 * Created by youxiaoshuang on 2016/9/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface FileService {
    /**
     * 第一个版本只上传到项目本地 以后扩展为对象存储引擎
     * @return
     */
//    File uploadLocalFile(MultipartFile multipartFile,String savePath,String newname);

    FileModel uploadLocalFile(FileModel fileModel);

    void saveFile(FileModel fileModel);

    YFile getFileByKey(String key);
}

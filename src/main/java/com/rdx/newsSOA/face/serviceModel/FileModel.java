package com.rdx.newsSOA.face.serviceModel;

import lombok.Data;

/**
 * Created by youxiaoshuang on 2016/9/18.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class FileModel {
    private String key;
    private String baseStr;//base64 文件
    private String originalName;//原始文件名
    private String newName;//保存到服务器的文件名
    private String fileType;//文件类型
    private long size;//文件大小
    private String url;//web访问地址
    private String serverPath;//保存到服务器的地址


    private String docCode;//所属文章


}

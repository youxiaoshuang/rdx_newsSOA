package com.rdx.newsSOA.dto;

import com.rdx.newsSOA.entity.YFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by youxiaoshuang on 2016/11/26.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class TouTiaoNewModel implements Serializable {
    private static final long serialVersionUID = -4756665388521305937L;
    private String title;
    private String source;
    //    private List<YImageEntity> yImageEntities;
    private String media_name;
    private String dateTime;
    private String desc;
    private String url;
    private String content;
    private List<YFile> yFiles;
    private Integer sourceTye;
    private List<YFile> mediaFiles;
    private Boolean hasVideo;
}
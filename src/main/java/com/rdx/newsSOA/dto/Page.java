package com.rdx.newsSOA.dto;


import com.rdx.newsSOA.util.PageParameter;
import lombok.Data;

/**
 * Created by youxiaoshuang on 16/8/23.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class Page {
    private PageParameter parameter;
    private String uuid;
}

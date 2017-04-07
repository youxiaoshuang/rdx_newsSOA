package com.rdx.newsSOA.face;


import com.rdx.newsSOA.entity.Tag;

import java.util.List;

/**
 * Created by youxiaoshuang on 2016/11/3.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface TagService {
    Tag addTag(Tag tag);
    List<Tag> getTags();
}

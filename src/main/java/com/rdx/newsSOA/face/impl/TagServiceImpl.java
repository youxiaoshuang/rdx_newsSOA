package com.rdx.newsSOA.face.impl;


import com.rdx.newsSOA.dao.TagMapper;
import com.rdx.newsSOA.entity.Tag;
import com.rdx.newsSOA.face.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by youxiaoshuang on 2016/11/3.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "tagService")
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    public Tag addTag(Tag tag) {
        int i = tagMapper.insertSelective( tag );
        tag.setId( i );
        return tag;
    }

    public List<Tag> getTags() {
        return tagMapper.selectAll();
    }
}

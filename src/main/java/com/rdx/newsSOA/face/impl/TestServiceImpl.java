package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.face.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by youxiaoshuang on 2017/4/6.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Service(value = "testService")
public class TestServiceImpl implements TestService {
    @Override
    public Integer getNum() {
        return 1;
    }
}

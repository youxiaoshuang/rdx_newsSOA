package com.rdx.newsSOA.common.constants.SessionConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by youxiaoshuang on 2016/11/24.
 * Projiect fightting
 * Author youxiaoshuang
 */
public enum  SessionConstants {
    SESSION_USER("session_user");
    @Setter
    @Getter
    private String sessionName;
    SessionConstants(String sessionName){
        this.sessionName = sessionName;
    }

}

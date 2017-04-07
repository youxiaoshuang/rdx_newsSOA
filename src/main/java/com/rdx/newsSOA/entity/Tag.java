package com.rdx.newsSOA.entity;

import java.io.Serializable;

public class Tag implements Serializable{
    private static final long serialVersionUID = -3424366604197442838L;
    private Integer id;

    private String tagname;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
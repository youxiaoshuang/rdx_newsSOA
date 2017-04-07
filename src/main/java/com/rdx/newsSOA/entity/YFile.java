package com.rdx.newsSOA.entity;

import java.io.Serializable;

public class YFile implements Serializable {
    private static final long serialVersionUID = 2206419713465767169L;
    private Integer id;

    private String key;

    private String originalname;

    private String newname;

    private String filetype;

    private Long size;

    private String url;

    private String serverpath;

    private Integer docid;

    private Integer isLocalFile;

    public Integer getIsLocalFile() {
        return isLocalFile;
    }

    public void setIsLocalFile(Integer isLocalFile) {
        this.isLocalFile = isLocalFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getNewname() {
        return newname;
    }

    public void setNewname(String newname) {
        this.newname = newname;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServerpath() {
        return serverpath;
    }

    public void setServerpath(String serverpath) {
        this.serverpath = serverpath;
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }
}
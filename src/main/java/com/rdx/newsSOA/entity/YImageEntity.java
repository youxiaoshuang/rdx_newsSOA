package com.rdx.newsSOA.entity;

import com.rdx.newsSOA.util.PageParameter;

import java.io.Serializable;

public class YImageEntity implements Serializable {
    private static final long serialVersionUID = -688489632148960317L;
    private Integer imageid;

    private String type;

    private String name;

    private Long size;

    private String url;
    
    private String ftpUrl;
    
    private PageParameter  parameter;

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.url = url == null ? null : url.trim();
    }

	public PageParameter getParameter() {
		return parameter;
	}

	public void setParameter(PageParameter parameter) {
		this.parameter = parameter;
	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}
    
    
}
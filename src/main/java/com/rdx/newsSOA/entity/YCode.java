package com.rdx.newsSOA.entity;

import java.io.Serializable;
import java.util.Date;

public class YCode implements Serializable{
    private static final long serialVersionUID = 5269538406579231808L;
    private Integer id;

    private Integer code;

    private Integer mainid;

    private Integer assistid;

    private Byte status;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMainid() {
        return mainid;
    }

    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    public Integer getAssistid() {
        return assistid;
    }

    public void setAssistid(Integer assistid) {
        this.assistid = assistid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
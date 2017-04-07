package com.rdx.newsSOA.entity;

import java.io.Serializable;

public class OrderEntity implements Serializable {
    private static final long serialVersionUID = -6953681050733399664L;
    private Integer id;

    private String orderno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }
}
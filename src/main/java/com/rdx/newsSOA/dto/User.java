package com.rdx.newsSOA.dto;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String name;
	private String password;
	private String uuid;
}

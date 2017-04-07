package com.rdx.newsSOA.face.impl;


import com.rdx.newsSOA.dao.UserMapper;
import com.rdx.newsSOA.dto.User;
import com.rdx.newsSOA.face.UserService;
import com.rdx.newsSOA.face.serviceModel.Response;
import com.rdx.newsSOA.util.EncryptUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper udao;

	public List<User> getLists() {
		// TODO Auto-generated method stub
		return null;
	}

	public Response saveUser(User user) {
		// TODO Auto-generated method stub
		Response response = new Response();
		com.rdx.newsSOA.entity.User userEn = new com.rdx.newsSOA.entity.User();
		user.setPassword( EncryptUtil.encrypt( user.getPassword() ) );
		BeanUtils.copyProperties(user,userEn );
		//验证用户是否存在
		Response byName = findByName( user );
		if(byName.getStatus()==1){
			response.setStatus( 0 );
			response.setMsg( "账号已存在" );
			return response;
		}
		//注册
		int insert = udao.insertSelective( userEn );
		if(insert>0){
			response.setStatus( 1 );
			response.setMsg( "注册成功" );
			response.setData( userEn );
		}else{
			response.setStatus( 0 );
			response.setMsg( "注册失败" );
		}
		return response;
	}


	public Response checkUser(User user) {
		Response response = new Response();
		com.rdx.newsSOA.entity.User usern = new com.rdx.newsSOA.entity.User();
		usern.setName( user.getName() );
		usern.setPassword( EncryptUtil.encrypt(user.getPassword()) );
		//效验用户是否存在
		Response byName = findByName( user );
		if(byName.getStatus()==0){//账号不存在
			return byName;
		}
		//效验用户和用户名
		com.rdx.newsSOA.entity.User byNameAndPass = udao.selectByNameAndPass( usern );
		if(byNameAndPass !=null){
			response.setMsg( "登录成功" );
			response.setStatus( 1 );
			response.setData( byNameAndPass );
		}else{
			response.setMsg( "密码错误" );
			response.setStatus( 0 );
		}
		return response;
	}

	public Response findByName(User user) {
		Response response = new Response();
		com.rdx.newsSOA.entity.User usern = new com.rdx.newsSOA.entity.User();
		usern.setName( user.getName() );
		int i = udao.selectByname( usern );
		if(i>0){
			response.setStatus( 1 );
			response.setMsg( "账号已存在" );
		}else{
			response.setStatus( 0 );
			response.setMsg( "账号不存在" );
		}
		return response;
	}

}

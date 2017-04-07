package com.rdx.newsSOA.face;


import com.rdx.newsSOA.dto.User;
import com.rdx.newsSOA.face.serviceModel.Response;

import java.util.List;

public interface UserService {
	List<User> getLists();

    /**
     * 注册账号
     * @param user
     * @return
     */
    Response saveUser(User user);

    /**
     * 登录效验
     * @param user
     * @return
     */
    Response checkUser(User user);

    /**
     * 根据用户名查询账号是否存在
     * @param user
     * @return
     */
    Response findByName(User user);

}

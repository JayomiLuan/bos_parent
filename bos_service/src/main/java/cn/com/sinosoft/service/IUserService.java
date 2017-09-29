package cn.com.sinosoft.service;

import cn.com.sinosoft.domain.User;

public interface IUserService {
    User findUsernameAndPassword(String username, String s);

    void editPassword(User model);
}

package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IUserDao;
import cn.com.sinosoft.domain.User;
import cn.com.sinosoft.service.IUserService;
import cn.com.sinosoft.utils.CommonUtils;
import cn.com.sinosoft.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User findUsernameAndPassword(String username, String md5) {
        return userDao.findUsernameAndPassword(username,md5);
    }

    @Override
    public void editPassword(User model) {
        // String hql = update user set password = ? where id = ?;
        model.setPassword(MD5Utils.md5(model.getPassword()));
        userDao.excuteUpdate("user.editPassword",model.getPassword(),CommonUtils.getLoginUser().getId());
    }
}

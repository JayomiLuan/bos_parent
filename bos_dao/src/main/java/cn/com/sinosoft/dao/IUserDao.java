package cn.com.sinosoft.dao;

import cn.com.sinosoft.dao.base.IBaseDao;
import cn.com.sinosoft.domain.User;

public interface IUserDao extends IBaseDao<User> {

    User findUsernameAndPassword(String username, String md5);
}

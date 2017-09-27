package cn.com.sinosoft.dao.impl;

import cn.com.sinosoft.dao.IUserDao;
import cn.com.sinosoft.dao.base.impl.BaseDaoImpl;
import cn.com.sinosoft.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


    @Override
    public User findUsernameAndPassword(String username, String md5) {
        String hql = "from User where username = ? and password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, md5);
        if(null != list && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}

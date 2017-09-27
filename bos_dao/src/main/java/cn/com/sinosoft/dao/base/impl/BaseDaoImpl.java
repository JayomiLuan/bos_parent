package cn.com.sinosoft.dao.base.impl;

import cn.com.sinosoft.dao.base.IBaseDao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> entityClass;//User.class

    public BaseDaoImpl() {
        //UserDaoImpl extends BaseDaoImpl<User>
        //UserDaoImpl userDao = new UserDaoImpl();//spring工厂<bean id="userDao" class="...UserDaoImpl">
        //this == userDao;
        //this.getClass() == UserDaoImpl.class
        //this.getClass().getGenericSuperclass() == BaseDaoImpl<User>.class
        ParameterizedType parameterizedType = (ParameterizedType)
                this.getClass().getGenericSuperclass();
        //parameterizedType.getActualTypeArguments() == [User.class]
        Type[] types = parameterizedType.getActualTypeArguments();
        entityClass = (Class<T>) types[0];
    }

    //1.加在属性上：给对应的属性赋值
    //2.加在方法上：给方法的参数进行赋值
    @Resource//resource可以根据bean类型或者是id属性值进行注入
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String hql = "from " + entityClass.getName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

}

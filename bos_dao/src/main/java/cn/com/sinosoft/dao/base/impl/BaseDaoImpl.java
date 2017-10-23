package cn.com.sinosoft.dao.base.impl;

import cn.com.sinosoft.dao.base.IBaseDao;
import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.utils.pageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
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

    /**
     * 实现通用的更新方法
     * @param s
     * @param objects
     */
    @Override
    public void excuteUpdate(String s, Object...objects) {
        Query namedQuery = this.getSessionFactory().getCurrentSession().getNamedQuery(s);
        int index = 0;
        for (Object object : objects) {
            namedQuery.setParameter(index,object);
            index++;
        }
        namedQuery.executeUpdate();
    }

    //通用分页查询
    @Override
    public void pageQuery(pageBean pageBean) {
        DetachedCriteria dc = pageBean.getDc();
        //查询总记录数
        dc.setProjection(Projections.rowCount());
        List<Long> total = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
        pageBean.setTotal(total.get(0));
        //查询当前页的数据
        dc.setProjection(null);
        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        int startRows = (currentPage-1)*pageSize;
        List<?> rows = this.getHibernateTemplate().findByCriteria(dc, startRows, pageSize);
        pageBean.setRows(rows);
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 通用条件查询
     * @param s
     * @param objects
     * @return
     */
    @Override
    public List<Staff> findByCondition(String s,Object ...objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(s);
        int parm = 0;
        for (Object object : objects) {
            query.setParameter(parm++,object);
        }
        return query.list();
    }
}

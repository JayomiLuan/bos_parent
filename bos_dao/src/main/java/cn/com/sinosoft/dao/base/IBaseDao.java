package cn.com.sinosoft.dao.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    void save(T enntity);
    void delete(T enntity);
    void update(T enntity);
    //单个
    T findById(Serializable id);
    //多个
    List<T> findAll();
}

package cn.com.sinosoft.dao;

import cn.com.sinosoft.dao.base.IBaseDao;
import cn.com.sinosoft.domain.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {
    List<Region> findByQ(String  q);
}

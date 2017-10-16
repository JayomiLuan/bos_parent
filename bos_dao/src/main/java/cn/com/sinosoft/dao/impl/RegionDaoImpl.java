package cn.com.sinosoft.dao.impl;

import cn.com.sinosoft.dao.IRegionDao;
import cn.com.sinosoft.dao.base.impl.BaseDaoImpl;
import cn.com.sinosoft.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    @Override
    public List<Region> findByQ(String q) {

        String hql = "from Region where shortcode like ? or citycode like ? ";
        List<Region> regionList = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%");
        return regionList;
    }
}

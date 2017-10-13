package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IRegionDao;
import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.service.IRegionService;
import cn.com.sinosoft.utils.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao regionDao;

    @Override
    public void pageQuery(pageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public void batchSave(List<Region> list) {
        for (Region region : list) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void add(Region model) {
        regionDao.save(model);
    }

    @Override
    public void batchDelete(String ids) {
        String[] arrID = ids.split(",");
        for (String id : arrID) {
            Region region = regionDao.findById(id);
            regionDao.delete(region);
        }


    }
}

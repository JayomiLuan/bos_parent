package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IRegionDao;
import cn.com.sinosoft.service.IRegionService;
import cn.com.sinosoft.utils.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao regionDao;

    @Override
    public void pageQuery(pageBean pageBean) {

        regionDao.pageQuery(pageBean);


    }
}

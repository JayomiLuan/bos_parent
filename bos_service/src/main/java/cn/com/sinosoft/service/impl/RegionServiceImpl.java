package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IRegionDao;
import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.service.IRegionService;
import cn.com.sinosoft.utils.PinYin4jUtils;
import cn.com.sinosoft.utils.pageBean;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public void alter(Region model) {
        Region region = regionDao.findById(model.getId());
        region.setProvince(model.getProvince());
        region.setCity(model.getCity());
        region.setDistrict(model.getDistrict());
        region.setPostcode(model.getPostcode());
        String temp = model.getProvince().substring(0, model.getProvince().length() - 1) + model.getCity().substring(0, model.getCity().length() - 1) + model.getDistrict().substring(0, model.getDistrict().length() - 1);
        String[] headByString = PinYin4jUtils.getHeadByString(temp);
        String shortcode = StringUtils.join(headByString, "");
        region.setShortcode(shortcode);
        region.setCitycode(PinYin4jUtils.hanziToPinyin(model.getCity().substring(0,model.getCity().length()-1)).replace(" ",""));
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public List<Region> findByQ(String q) {

        return regionDao.findByQ(q);
    }
}

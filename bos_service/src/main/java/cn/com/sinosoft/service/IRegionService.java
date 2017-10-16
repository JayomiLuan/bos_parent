package cn.com.sinosoft.service;

import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.utils.pageBean;

import java.util.List;

public interface IRegionService {


    void pageQuery(pageBean pageBean);

    void batchSave(List<Region> list);

    void add(Region model);

    void batchDelete(String ids);

    void alter(Region model);

    List<Region> findAll();

    List<Region> findByQ(String q);
}

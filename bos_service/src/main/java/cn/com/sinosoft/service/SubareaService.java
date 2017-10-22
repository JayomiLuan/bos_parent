package cn.com.sinosoft.service;

import cn.com.sinosoft.domain.Subarea;
import cn.com.sinosoft.utils.pageBean;

public interface SubareaService {


    void add(Subarea model);

    void pageQuery(pageBean pageBean);

    void delete(String ids);
}

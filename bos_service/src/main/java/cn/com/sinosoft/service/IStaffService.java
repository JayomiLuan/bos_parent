package cn.com.sinosoft.service;

import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.utils.pageBean;

import java.util.List;

public interface IStaffService {

    void add(Staff model);

    void pageQuery(pageBean pageBean);

    void batchDelete(String ids);

    void batchrestore(String ids);

    void edit(Staff model);

    List<Staff> findByCondition();
}

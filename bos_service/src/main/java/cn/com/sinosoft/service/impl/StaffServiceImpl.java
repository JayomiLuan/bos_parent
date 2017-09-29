package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IStaffDao;
import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;

    @Override
    public void add(Staff model) {
        staffDao.save(model);
    }
}

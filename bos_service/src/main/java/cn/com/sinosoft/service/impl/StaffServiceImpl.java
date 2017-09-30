package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.IStaffDao;
import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.service.IStaffService;
import cn.com.sinosoft.utils.pageBean;
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

    @Override
    public void pageQuery(pageBean pageBean) {
    staffDao.pageQuery(pageBean);

    }

    @Override
    public void batchDelete(String ids) {
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            staffDao.excuteUpdate("staff.delete",id);
        }

    }

    @Override
    public void batchrestore(String ids) {
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            staffDao.excuteUpdate("staff.restore",id);
        }
    }

    @Override
    public void edit(Staff model) {
        Staff staff = staffDao.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setEmail(model.getEmail());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
    }
}

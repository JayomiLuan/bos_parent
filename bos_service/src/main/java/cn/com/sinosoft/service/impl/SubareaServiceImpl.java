package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.SubareaDao;
import cn.com.sinosoft.domain.Subarea;
import cn.com.sinosoft.service.SubareaService;
import cn.com.sinosoft.utils.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(pageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public void delete(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            Subarea subarea = subareaDao.findById(id);
            subareaDao.delete(subarea);
        }
    }
}

package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.SubareaDao;
import cn.com.sinosoft.service.SubareaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class SubareaServiceImpl implements SubareaService {
    @Resource
    private SubareaDao subareaDao;
}

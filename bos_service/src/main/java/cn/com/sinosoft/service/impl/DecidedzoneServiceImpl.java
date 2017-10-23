package cn.com.sinosoft.service.impl;

import cn.com.sinosoft.dao.DecidedzoneDao;
import cn.com.sinosoft.service.DecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
    @Autowired
    private DecidedzoneDao decidedzoneDao;
}

package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Decidedzone;
import cn.com.sinosoft.service.DecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
    @Autowired
    private DecidedzoneService decidedzoneService;





}

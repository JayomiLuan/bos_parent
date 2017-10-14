package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Subarea;
import cn.com.sinosoft.service.SubareaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
public class SubareaAction  extends BaseAction<Subarea>{
    @Resource
    private SubareaService subareaService;



}

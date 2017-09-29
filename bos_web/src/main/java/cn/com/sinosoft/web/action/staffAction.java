package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class staffAction extends  BaseAction<Staff>{
    @Autowired
    private IStaffService staffService;

    public String add(){
        staffService.add(model);
        return "list";
    }

}

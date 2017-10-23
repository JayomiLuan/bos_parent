package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
public class staffAction extends  BaseAction<Staff>{
    @Autowired
    private IStaffService staffService;

    public String add(){
        staffService.add(model);
        return "list";
    }
    //分页查询
    public String pageQuery(){
        staffService.pageQuery(pageBean);
        //转成json的数据格式
        //JsonConfig:用来排除不需要转换成json的属性
        String[] excludes = {"currentPage","pageSize","dc","decidedzones"};
        this.writeObject2Json(pageBean,excludes);
        return NONE;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String delete(){
        staffService.batchDelete(ids);
        return "list";
    }
    public String restore(){
        staffService.batchrestore(ids);
        return "list";
    }
    public String edit(){
        staffService.edit(model);
        return "list";
    }
    public String listAjax(){
        List<Staff> list =  staffService.findByCondition();
        String[] exclude = {"decidedzones"};
        this.writeList2Json(list,exclude);
        return NONE;
    }
}

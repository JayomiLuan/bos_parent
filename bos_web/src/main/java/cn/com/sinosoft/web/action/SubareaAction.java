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
    public String add(){
        subareaService.add(model);
        return "list";
    }

    public String pageQuery(){
        subareaService.pageQuery(pageBean);
        String[] excludes = {"dc","decidedzone","subareas","currentPage","pageSize"};
        this.writeObject2Json(pageBean,excludes);
        return NONE;
    }
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }

    public String delete(){
        subareaService.delete(ids);
        return "list";
    }

}

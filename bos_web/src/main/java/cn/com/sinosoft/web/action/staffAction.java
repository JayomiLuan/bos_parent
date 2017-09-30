package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Staff;
import cn.com.sinosoft.service.IStaffService;
import cn.com.sinosoft.utils.pageBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class staffAction extends  BaseAction<Staff>{
    @Autowired
    private IStaffService staffService;

    private Integer page;

    private Integer rows;
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public String add(){
        staffService.add(model);
        return "list";
    }
    //分页查询
    public String pageQuery(){
        pageBean pageBean = new pageBean();
        pageBean.setPageSize(rows);//每页条数
        pageBean.setCurrentPage(page);//当前页
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        pageBean.setDc(dc);
        staffService.pageQuery(pageBean);
        //转成json的数据格式
        //JsonConfig:用来排除不需要转换成json的属性
        JsonConfig jsonConfig = new JsonConfig();
        String[] excludes = {"currentPage","pageSize","dc","decidedzones"};
        jsonConfig.setExcludes(excludes);
        JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
        String json = jsonObject.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}

package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.service.IRegionService;
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
public class RegionAction extends BaseAction<Region> {
    @Autowired
    private IRegionService regionService;

    private Integer page;
    private Integer rows;
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String pageQuery(){
        pageBean pageBean = new pageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria dc = DetachedCriteria.forClass(Region.class);
        pageBean.setDc(dc);
        regionService.pageQuery(pageBean);
        JsonConfig jsonConfig = new JsonConfig();
        String[] jsonArr = {"subareas"};
        jsonConfig.setExcludes(jsonArr);
        JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
        String json = jsonObject.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

}

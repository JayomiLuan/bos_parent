package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.domain.Subarea;
import cn.com.sinosoft.service.SubareaService;
import cn.com.sinosoft.utils.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

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
    public String exportXls(){
        List<Subarea> list =  subareaService.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("区域编号");
        row.createCell(2).setCellValue("关键字");
        row.createCell(3).setCellValue("位置");
        if(null!= list&& list.size()>0){
            int index = 1;
            for (Subarea subarea : list) {
                //在sheet中循环创建row
                row = sheet.createRow(index++);
                //在row中创建列，添加数据
                row.createCell(0).setCellValue(subarea.getId());
                Region region = subarea.getRegion();
                if(null != region){
                    row.createCell(1).setCellValue(region.getId());
                } else {
                    row.createCell(1).setCellValue("未定义");
                }
                row.createCell(2).setCellValue(subarea.getAddresskey());
                row.createCell(3).setCellValue(subarea.getPosition());
            }
        }
        //3.将excel通过response返回到前台
        String filename = "分区数据.xls";
        //获取到请求头中的用户的浏览器类型User-Agent
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        try {
            //两个头之一：content-type，告诉浏览器返回数据的格式
            String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
            System.out.println(mimeType);
            filename = FileUtils.encodeDownloadFilename(filename, agent);
            System.out.println(filename);
            //一个流：指的是response的输出流
            ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
            ServletActionContext.getResponse().setContentType(mimeType);
            //两个头之二：content-disposition，告诉浏览器返回的数据打开的方法
            ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
            //4.调用workbook的write方法将excel返回到前台
            hssfWorkbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

}

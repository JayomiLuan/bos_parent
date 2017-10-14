package cn.com.sinosoft.web.action;

import cn.com.sinosoft.domain.Region;
import cn.com.sinosoft.service.IRegionService;
import cn.com.sinosoft.utils.PinYin4jUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    @Resource
    private IRegionService regionService;

    private File regionFile;
    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    public String pageQuery(){
        regionService.pageQuery(pageBean);
        String[] jsonArr = {"subareas"};
        this.writeObject2Json(pageBean,jsonArr);
        return NONE;
    }


    public String importXls(){
        // 使用POI读取execel文档
        String flag = "1";
        try {
            // 1.创建workbook对象，读取整个文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(regionFile));
            // 2.读取某个sheet
            HSSFSheet sheet = wb.getSheetAt(0);
            // 3.循环读取每一行
            List<Region> list = new ArrayList<>();
            for (Row row : sheet) {
                // 跳过第一行标题
                int rowNum = row.getRowNum();// 获取每行的行号
                if (0 == rowNum) {
                    // 第一行，跳过
                    continue;
                }
                // 4.读取一行中的单元格
                String id = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();

                // 5.创建一个Region对象，封装数据
                Region region = new Region(id, province, city, district, postcode, null, null, null);

                // shortcode：简码
                // 河北省石家庄市开发区
                // 河北石家庄开发
                // HBSJZKF
                // 去掉省市区
                province = province.substring(0, province.length() - 1);
                city = city.substring(0, city.length() - 1);
                district = district.substring(0, district.length() - 1);
                // 拼接字符串
                String temp = province + city + district;// 河北石家庄开发
                String[] headByString = PinYin4jUtils.getHeadByString(temp);
                String shortcode = StringUtils.join(headByString, "");
                region.setShortcode(shortcode);

                // citycode：城市码
                // 石家庄市
                // 石家庄
                // shijiazhuang
                String citycode = PinYin4jUtils.hanziToPinyin(city, "");
                region.setCitycode(citycode);
                list.add(region);
            }
            regionService.batchSave(list);
        } catch (IOException e) {
            flag = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public String add(){
        String id = UUID.randomUUID().toString().substring(0, 5);
        model.setId(id);
        String temp = model.getProvince().substring(0, model.getProvince().length() - 1) + model.getCity().substring(0, model.getCity().length() - 1) + model.getDistrict().substring(0, model.getDistrict().length() - 1);
        String[] headByString = PinYin4jUtils.getHeadByString(temp);
        String shortcode = StringUtils.join(headByString, "");
        model.setShortcode(shortcode);
        model.setCitycode(PinYin4jUtils.hanziToPinyin(model.getCity().substring(0,model.getCity().length()-1)).replace(" ",""));
        regionService.add(model);
        return "list";
    }
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    public String delete(){
        regionService.batchDelete(ids);
        return "list";
    }
    public String alter(){
        regionService.alter(model);
        return "list";
    }
}

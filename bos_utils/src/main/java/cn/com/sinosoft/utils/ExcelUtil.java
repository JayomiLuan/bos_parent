package cn.com.sinosoft.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
    public static <T> List<T> importXls(File file, Class clazz) {
        // 使用POI读取execel文档
        String flag = "1";
        try {
            // 1.创建workbook对象，读取整个文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
            // 2.读取某个sheet
            HSSFSheet sheet = wb.getSheetAt(0);
            // 3.循环读取每一行
            List<T> list = new ArrayList<>();
            for (Row row : sheet) {
                // 跳过第一行标题
                int rowNum = row.getRowNum();// 获取每行的行号
                if (0 == rowNum) {
                    // 第一行，跳过
                    continue;
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
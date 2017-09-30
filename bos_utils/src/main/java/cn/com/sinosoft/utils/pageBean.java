package cn.com.sinosoft.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class pageBean {

    private Integer currentPage;//当前页
    private Integer pageSize;//每页条数
    private DetachedCriteria dc;//存放前台查询的条件
    private Long total;//总记录数
    private List<?> rows;//当前页数据

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDc() {
        return dc;
    }

    public void setDc(DetachedCriteria dc) {
        this.dc = dc;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

package com.gomai.utils;

import java.util.List;

public class PageResult  <T> {
    private long total;
    private List<T> rows;
    private Long totalPage;// 总页数

    public PageResult() {
    }

    public PageResult(long total, List<T> rows, Long totalPage) {
        this.total = total;
        this.rows = rows;
        this.totalPage = totalPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                ", totalPage=" + totalPage +
                '}';
    }
}

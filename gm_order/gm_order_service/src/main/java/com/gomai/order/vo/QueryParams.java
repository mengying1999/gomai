package com.gomai.order.vo;

/**
 *
 */
public class QueryParams<T>  {
    /**
     * 查询类
     */
    private T data;  //数据
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 每页显示记录数
     */
    private Integer rows;

    public QueryParams() {
    }

    public QueryParams(T data, Integer page, Integer rows) {
        this.data = data;
        this.page = page;
        this.rows = rows;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "QueryParams{" +
                "data=" + data +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}

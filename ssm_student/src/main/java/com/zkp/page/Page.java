package com.zkp.page;


import org.springframework.stereotype.Component;

@Component
public class Page {

    private int page;//当前页
    private int offset;//偏移量，从哪开始显示
    private int rows;//每页显示多少条记录

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        this.offset = (page - 1) * rows;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (page - 1) * rows;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}

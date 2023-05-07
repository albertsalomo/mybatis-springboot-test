package com.mybatis.mysql.model;

public class Book {
    private Integer id;
    private String title;
    private String subTitle;
    private Integer pages;

    public Book() {

    }

    public Book(Integer id, String title, String subTitle, Integer pages) {
        super();
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.pages = pages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}

package com.zqf.pojo;
/**
 * @author oscarzqf
 * @description
 * @create 2022-03-26-15:49
 */
public class images {
    private int id;
    private String url;
    private String img;

    public images(int id, String url, String img) {
        this.id = id;
        this.url = url;
        this.img = img;
    }

    public images() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

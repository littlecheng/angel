package com.weitao.collecttest;

public class MusicLnfo {
    private Integer id;
    private String  url;
    private String title;
    private String singer;


    public MusicLnfo(Integer id,String url,String title , String singer){
        this.id = id ;
        this.url = url;
        this.title = title;
        this.singer = singer;
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

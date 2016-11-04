package com.ugfind.model;

public class Carousel {
    private Integer id;

    private String bannaPic;

    private Integer schoolId;

    private String contentUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannaPic() {
        return bannaPic;
    }

    public void setBannaPic(String bannaPic) {
        this.bannaPic = bannaPic == null ? null : bannaPic.trim();
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }
}
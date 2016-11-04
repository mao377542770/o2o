package com.ugfind.model;

public class Literature {
    private Integer id;

    private String name;

    private String literatureUrl;

    private Integer literatureType;

    private Integer schoolId;

    private String abst;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLiteratureUrl() {
        return literatureUrl;
    }

    public void setLiteratureUrl(String literatureUrl) {
        this.literatureUrl = literatureUrl == null ? null : literatureUrl.trim();
    }

    public Integer getLiteratureType() {
        return literatureType;
    }

    public void setLiteratureType(Integer literatureType) {
        this.literatureType = literatureType;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst == null ? null : abst.trim();
    }
}
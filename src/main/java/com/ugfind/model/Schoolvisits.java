package com.ugfind.model;

public class Schoolvisits {
    private Integer id;

    private Integer schoolId;
    
    private String schoolName;

    private Integer schoolVisits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getSchoolVisits() {
        return schoolVisits;
    }

    public void setSchoolVisits(Integer schoolVisits) {
        this.schoolVisits = schoolVisits;
    }

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "Schoolvisits [id=" + id + ", schoolId=" + schoolId
				+ ", schoolName=" + schoolName + ", schoolVisits="
				+ schoolVisits + "]";
	}
    
    
}
package com.ugfind.model;

public class ActivityConfig {
	private Integer currentPage;
	private Integer itemsPerPage;
	private Integer totalItems;
	private Integer currentIndex;
	private Integer schoolId;
	private Integer deptId;
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Integer getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}
	public Integer getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
}

package com.ugfind.model;

public class NewsPageConfig {
	private Integer currentPage;
	private Integer itemsPerPage;
	private Integer totalItems;
	private Integer schoolId;
	private Integer currentIndex;
	
	
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
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}
	
}

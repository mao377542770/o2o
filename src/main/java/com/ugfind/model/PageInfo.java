package com.ugfind.model;

//分页信息
public class PageInfo {
	private Integer currentPage;   //当前页码数
	private Integer itemsPerPage; //一页显示几个
	private Integer totalItems;		  //总共多少条数据
	public Integer getCurrentPage() {
		return currentPage*itemsPerPage;
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
	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", itemsPerPage="
				+ itemsPerPage + ", totalItems=" + totalItems + "]";
	}
	
	
}

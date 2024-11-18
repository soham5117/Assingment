package edu.soham.springboot.entity;

import java.util.List;

public class Response<T> {

	
	private Integer totalSoldItems;
	private Double TotalSaleAmount;
	private Integer noNotSoldItems;
	private List<PiChartData> piChartData;
	private List <T> Data;
	private List<BarChartData> barChartData;
	private Integer totalPages;
	private Integer CurrentPage;
	
	
	
	
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getCurrentPage() {
		return CurrentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		CurrentPage = currentPage;
	}
	public List<PiChartData> getPiChartData() {
		return piChartData;
	}
	public void setPiChartData(List<PiChartData> piChartData) {
		this.piChartData = piChartData;
	}
	public List<BarChartData> getBarChartData() {
		return barChartData;
	}
	public void setBarChartData(List<BarChartData> barChartData) {
		this.barChartData = barChartData;
	}
	private String Message;
	
	public Integer getTotalSoldItems() {
		return totalSoldItems;
	}
	public void setTotalSoldItems(Integer totalSoldItems) {
		this.totalSoldItems = totalSoldItems;
	}
	public Double getTotalSaleAmount() {
		return TotalSaleAmount;
	}
	public void setTotalSaleAmount(Double totalSaleAmount) {
		TotalSaleAmount = totalSaleAmount;
	}
	public Integer getNoNotSoldItems() {
		return noNotSoldItems;
	}
	public void setNoNotSoldItems(Integer noNotSoldItems) {
		this.noNotSoldItems = noNotSoldItems;
	}
	public List<T> getData() {
		return Data;
	}
	public void setData(List<T> data) {
		Data = data;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	

}

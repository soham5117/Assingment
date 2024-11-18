package edu.soham.springboot.entity;

public class PiChartData {

	 private String category;
	    private Long itemCount;

	    public PiChartData(String category, Long itemCount) {
	        this.category = category;
	        this.itemCount = itemCount;
	    }

	    // Getters and setters
	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public Long getItemCount() {
	        return itemCount;
	    }

	    public void setItemCount(Long itemCount) {
	        this.itemCount = itemCount;
	    }
}

package edu.soham.springboot.entity;

public class BarChartData {

	 private String priceRange;
	    private Long itemCount;

	    public BarChartData(String priceRange, Long itemCount) {
	        this.priceRange = priceRange;
	        this.itemCount = itemCount;
	    }
	    
	    // Getters and setters
	    public String getPriceRange() {
	        return priceRange;
	    }

	    public void setPriceRange(String priceRange) {
	        this.priceRange = priceRange;
	    }

	    public Long getItemCount() {
	        return itemCount;
	    }

	    public void setItemCount(Long itemCount) {
	        this.itemCount = itemCount;
	    }
}


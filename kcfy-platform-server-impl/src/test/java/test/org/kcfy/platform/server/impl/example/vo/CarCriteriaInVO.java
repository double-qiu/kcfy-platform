package test.org.kcfy.platform.server.impl.example.vo;

import com.kcfy.platform.server.kernal.model.JInputModel;

public class CarCriteriaInVO implements JInputModel {

	private String name;
	
	private String brand;
	
	private String minPrice;

	private String maxPrice;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}


}

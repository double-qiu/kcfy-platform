package test.org.kcfy.platform.server.impl.example.vo;

import com.kcfy.platform.server.kernal.model.JInputModel;

public class CarAddInVO implements JInputModel {

	private String name;
	
	private String brand;
	
	private float price;

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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	

	
}

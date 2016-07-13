package test.org.kcfy.platform.server.impl.example.vo;

import com.kcfy.platform.server.kernal.model.JOutputModel;

public class CarRecordOutVO implements JOutputModel {

	private String id;
	
	private String name;
	
	private String brand;
	
	private float price;


	public String getName() {
		return name;
	}
	
	public CarRecordOutVO() {}
	
	public CarRecordOutVO(String id,String name) {
		this.id=id;
		this.name=name;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

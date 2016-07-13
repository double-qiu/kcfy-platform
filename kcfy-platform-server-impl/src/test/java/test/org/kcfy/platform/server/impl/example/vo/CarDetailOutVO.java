package test.org.kcfy.platform.server.impl.example.vo;

import test.org.kcfy.platform.server.impl.example.model.Car;

import com.kcfy.platform.server.kernal.Copy;
import com.kcfy.platform.server.kernal.model.JOutputModel;

public class CarDetailOutVO implements JOutputModel {

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

	
	public static void main(String[] args) {
		
		Car car=new Car();
		car.setName("宝马");
		car.setBrand("宝马");
		car.setPrice(0.99f);
		car.setId("aaaaaaaaaaaaa");
		
		CarDetailOutVO carDetailOutVO= Copy.simpleCopy(car, CarDetailOutVO.class);
		System.out.println(carDetailOutVO);
	}
}

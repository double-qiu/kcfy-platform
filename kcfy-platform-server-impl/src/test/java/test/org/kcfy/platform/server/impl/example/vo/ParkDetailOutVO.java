package test.org.kcfy.platform.server.impl.example.vo;

import com.kcfy.platform.server.kernal.model.JOutputModel;

public class ParkDetailOutVO implements JOutputModel {



	private String name;
	
	private int capacity;
	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}

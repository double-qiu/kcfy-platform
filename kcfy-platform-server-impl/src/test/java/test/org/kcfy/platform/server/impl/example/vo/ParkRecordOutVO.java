package test.org.kcfy.platform.server.impl.example.vo;

import com.kcfy.platform.server.kernal.model.JOutputModel;

public class ParkRecordOutVO  implements JOutputModel{

	private String name;
	
	private int capacity;
	
	private String address;
	
	public ParkRecordOutVO() {
	}
	public ParkRecordOutVO(String name, int capacity, String address) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.address = address;
	}



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

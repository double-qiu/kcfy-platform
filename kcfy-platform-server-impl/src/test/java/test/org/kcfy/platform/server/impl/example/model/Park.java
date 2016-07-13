package test.org.kcfy.platform.server.impl.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kcfy.platform.server.kernal.model.AbstractEntity;

@Entity
@Table(name = "t_park")
public class Park  extends AbstractEntity{

	@Column(name = "_name")
	private String name;
	
	@Column(name = "_capacity")
	private int capacity;
	
	@Column(name = "_address")
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

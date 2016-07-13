package test.org.kcfy.platform.server.impl.example.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import test.org.kcfy.platform.server.impl.example.vo.CarRecordOutVO;

import com.kcfy.platform.server.kernal.model.AbstractEntity;


@SqlResultSetMappings(
		value={
				@SqlResultSetMapping(name = "CarRecordOutVOMapping",
						classes={@ConstructorResult(
								targetClass=CarRecordOutVO.class,
								columns={
									@ColumnResult(name="_id",type=String.class),
									@ColumnResult(name="_name",type=String.class),
								}
								)}
						)
		}
)
@Entity
@Table(name = "t_car")
public class Car  extends AbstractEntity{


	@Column(name = "_name")
	private String name;
	
	@Column(name = "_brand")
	private String brand;
	
	@Column(name = "_price")
	private float price;

	public String getName() {
		return name;
	}
	
	public Car() {}
	
	public Car(String name) {
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
	
}

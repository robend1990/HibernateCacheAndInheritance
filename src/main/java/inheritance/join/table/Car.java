package inheritance.join.table;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@PrimaryKeyJoinColumn(name = "vehicleid")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Car extends Vehicle{

	protected Integer numberOfPassangers;
	protected Integer numberOfDoors;
	public Integer getNumberOfPassangers() {
		return numberOfPassangers;
	}
	public void setNumberOfPassangers(Integer numberOfPassangers) {
		this.numberOfPassangers = numberOfPassangers;
	}
	public Integer getNumberOfDoors() {
		return numberOfDoors;
	}
	public void setNumberOfDoors(Integer numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	
	
	
}

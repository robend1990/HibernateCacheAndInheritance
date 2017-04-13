package inheritance.join.table;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "vehicleid")
public class Truck extends Vehicle{

	protected Integer numberOfContainers;
	protected Integer loadCapacity;
	
	
	public Integer getNumberOfContainers() {
		return numberOfContainers;
	}
	public void setNumberOfContainers(Integer numberOfContainers) {
		this.numberOfContainers = numberOfContainers;
	}
	public Integer getLoadCapacity() {
		return loadCapacity;
	}
	public void setLoadCapacity(Integer loadCapacity) {
		this.loadCapacity = loadCapacity;
	}
	
	
}

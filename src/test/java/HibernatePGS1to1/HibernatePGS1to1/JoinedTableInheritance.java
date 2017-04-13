package HibernatePGS1to1.HibernatePGS1to1;

import static org.junit.Assert.assertEquals;
import inheritance.join.table.Car;
import inheritance.join.table.Truck;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class JoinedTableInheritance {

	@Before
	public void clearDB(){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from Vehicle").executeUpdate();
        session.flush();
        tx.commit();
        session.close();
	}
	
	@Test
	public void saveCar(){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Car car = new Car();
        car.setManufacturer("Mazda");
        car.setNumberOfDoors(4);
        car.setNumberOfPassangers(4);
        session.save(car);
        tx.commit();
        List<Car> cars = session.createQuery("from Car car").list();
        assertEquals(1, cars.size());
        car = cars.get(0);
        assertEquals("Mazda", car.getManufacturer());
        assertEquals((Integer)4, car.getNumberOfDoors());
        assertEquals((Integer)4, car.getNumberOfPassangers());
        session.close();
	}
	
	@Test
	public void saveTruck(){
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Truck truck = new Truck();
        truck.setManufacturer("Scania");
        truck.setLoadCapacity(200);
        truck.setNumberOfContainers(10);
        session.save(truck);
        tx.commit();
        List<Truck> trucks = session.createQuery("from Truck truck").list();
        assertEquals(1, trucks.size());
        truck = trucks.get(0);
        assertEquals("Scania", truck.getManufacturer());
        assertEquals((Integer)200, truck.getLoadCapacity());
        assertEquals((Integer)10, truck.getNumberOfContainers());
        session.close();
	}
}

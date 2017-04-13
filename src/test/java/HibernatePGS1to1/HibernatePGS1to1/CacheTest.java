package HibernatePGS1to1.HibernatePGS1to1;

import static org.junit.Assert.assertEquals;
import inheritance.join.table.Car;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class CacheTest {

	@Before
	public void clearSecondLevelCache() {
		HibernateUtil.getSessionFactory().getCache().evictAllRegions();
	}

	/**
	 * Entity is present in first level cache so, it is fetched from there. No
	 * need to go to second level cache.
	 */
	@Test
	public void test1() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Agent agent = (Agent) session.load(Agent.class, new Long(18));
		System.out.println(agent.getName());
		session.load(Agent.class, new Long(18));
		tx.commit();
		session.close();
		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getEntityFetchCount());
		assertEquals(0, HibernateUtil.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();

	}

	/**
	 * Entity is fetched from db, added to second level cache and then removed
	 * from first level cache by evict. Second agent is loaded from second level
	 * cache
	 */
	@Test
	public void test2() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Agent agent = session.load(Agent.class, new Long(18));
		System.out.println(agent.getName());
		session.evict(agent);
		agent = session.load(Agent.class, new Long(18));
		System.out.println(agent.getName());
		tx.commit();
		session.close();
		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getEntityFetchCount());
		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();

	}

	/**
	 * When another session created from same session factory try to get entity,
	 * it is successfully looked up in second level cache and no database call
	 * is made.
	 */
	@Test
	public void test3() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Agent agent = session.load(Agent.class, new Long(18));
		System.out.println(agent.getName());
		session.evict(agent);
		agent = session.load(Agent.class, new Long(18));
		System.out.println(agent.getName());

		Session anotherSession = HibernateUtil.getSessionFactory()
				.openSession();
		anotherSession.beginTransaction();
		agent = anotherSession.load(Agent.class, new Long(18));
		System.out.println(agent.getName());
		anotherSession.getTransaction().commit();

		tx.commit();
		session.close();

		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getEntityFetchCount());
		assertEquals(2, HibernateUtil.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();
	}

	/**
	 * Second session was created before the object was updated. That is why
	 * object is loaded from db during second call, instead second level cache
	 */
	@Test
	public void test4() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Session session2 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();
		Car car = session1.load(Car.class, new Long(35));
		System.out.println(car.getManufacturer());
		car.setManufacturer(car.getManufacturer() + System.currentTimeMillis());
		tx1.commit();

		Transaction tx2 = session2.beginTransaction();
		car = session2.load(Car.class, new Long(35));
		System.out.println(car.getManufacturer());
		tx2.commit();

		session1.close();
		session2.close();

		assertEquals(2, HibernateUtil.getSessionFactory().getStatistics()
				.getEntityFetchCount());
		assertEquals(0, HibernateUtil.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();
	}

	/**
	 * 
	 */
	@Test
	public void testQueryCache() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Agent a");
		query.setCacheable(true);
		Iterator it = query.list().iterator();
		while (it.hasNext()) {
			Agent agent = (Agent) it.next();
			System.out.println(agent.getName());
		}
		query = session.createQuery("from Agent a ");
		query.setCacheable(true);
		it = query.list().iterator();
		while (it.hasNext()) {
			Agent agent = (Agent) it.next();
			System.out.println(agent.getName());
		}
		tx.commit();
		session.close();

		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics().getQueryCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();

	}

	/**
	 * This should be fixed test4. Why it is not working ?
	 */
	@Test
	public void test5() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Car car = session1.load(Car.class, 35L);
		System.out.println(car.getManufacturer());
		car.setManufacturer(car.getManufacturer() + System.currentTimeMillis());
		tx1.commit();

		Session session2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx2 = session2.beginTransaction();
		Car car2 = session2.load(Car.class, 35L);
		System.out.println(car2.getManufacturer());
		tx2.commit();

		session1.close();
		session2.close();

		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getEntityFetchCount());
		assertEquals(1, HibernateUtil.getSessionFactory().getStatistics()
				.getSecondLevelCacheHitCount());
		HibernateUtil.getSessionFactory().getStatistics().clear();

	}
}

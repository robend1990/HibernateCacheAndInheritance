package HibernatePGS1to1.HibernatePGS1to1;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Agent agent = new Agent();
        agent.setName("Robert2");
        session.save(agent);
        tx.commit();
        System.out.println(session.createQuery("from Agent a").list());
        session.close();
        
        
    }
}

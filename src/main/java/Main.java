import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import owner.Owner;

public class Main {
    SessionFactory sessionFactory;
    public static void main(String[] args) {
        Main main = new Main();
        main.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Owner.class)
                .buildSessionFactory();

        Session session = main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Owner owner = new Owner();
        owner.setName("Some new owner from today");
        owner.setAge(34);

        session.persist(owner);

        transaction.commit();
    }
}

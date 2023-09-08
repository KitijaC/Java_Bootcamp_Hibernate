import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import owner.Owner;
import pets.Gender;
import pets.Pet;
import pets.PetTypes;

public class Main {
    SessionFactory sessionFactory;
    public static void main(String[] args) {
        Main main = new Main();
        main.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                // add all classes which is a type of entity
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(PetTypes.class)
                .addAnnotatedClass(Pet.class)
                .buildSessionFactory();

        Session session = main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Owner owner = new Owner();
        owner.setName("Some new owner from today");
        owner.setAge(34);
        session.persist(owner);

        PetTypes petTypes = new PetTypes();
        petTypes.setType("Something new");

        session.persist(petTypes);

        Pet pet = new Pet();
        pet.setPetName("Tony");
        pet.setAge(2);
        pet.setWeight(12.4f);
        pet.setGender(Gender.OTHER);
        pet.setOwner(owner);
        pet.setPetType(petTypes);

        session.persist(pet);

        transaction.commit();

        System.out.println(pet);
        System.out.println(petTypes);

        session.close();
    }
}

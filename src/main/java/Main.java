import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import owner.Owner;
import pets.Gender;
import pets.Pet;
import pets.PetTypes;

import java.util.List;

public class Main {
    SessionFactory sessionFactory;
    public static void main(String[] args) {
        Main main = new Main();
        main.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                // add all class which is a type of entity
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(PetTypes.class)
                .addAnnotatedClass(Pet.class)
                .buildSessionFactory();

        Session session = main.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Owner owner = new Owner();
        owner.setName("Some New owner from today");
        owner.setAge(34);
        session.persist(owner);

        PetTypes petTypes = new PetTypes();
        petTypes.setType("Something New");

        session.persist(petTypes);

        Pet pet = new Pet();
        pet.setPetName("Tony");
        pet.setAge(2);
        pet.setWeight(12.4f);
        pet.setGender(Gender.OTHER);
        pet.setOwner(owner);
        pet.setPetType(petTypes);

        session.persist(pet);

        // find by id
        Long ownerId = owner.getId();
        Owner foundOwner = session.find(Owner.class, ownerId);
        Pet foundPet = session.find(Pet.class, pet.getId());

        if (foundOwner != null){
            // owner was found
            System.out.println("Found Owner: " + foundOwner);

            // update
            foundOwner.setName("Some other name");
            foundOwner.setAge(98);
            session.merge(foundOwner); // update / save the new details;

            foundPet.setPetName("A random pet name");
            session.merge(foundPet);

            System.out.println("After update pet: " + foundPet);
            System.out.println("After update owner: " + foundOwner);

        } else {
            System.out.println("Owner was not found!");
        }

        // deleting items from db
        Pet randomPetToBeDeleted = new Pet();
        randomPetToBeDeleted.setPetName("Zino random pet");

        session.persist(randomPetToBeDeleted);

        session.remove(randomPetToBeDeleted); // deletes pet from table

        Owner randomOwner = new Owner();
        randomOwner.setName("I will be deleted");
        session.persist(randomOwner);

        session.remove(randomOwner); // deletes owner from table;

        // select all pet older than 4
        List<Pet> petsOlderThanFour = session.createQuery("FROM pets WHERE age < 4", Pet.class).getResultList();
        List<Pet> petsBelongToSpecificOwner = session.createQuery("FROM pets WHERE owner.id = 1", Pet.class).getResultList();
        List<Owner> ownersWithNameLikeZino = session.createQuery("FROM owners WHERE name LIKE '%Some%'", Owner.class).getResultList();

        System.out.println("petsOlderThanFour: " + petsOlderThanFour );
        System.out.println("petsBelongToSpecificOwner: " + petsBelongToSpecificOwner );
        System.out.println("ownersWithNameLikeZino: " + ownersWithNameLikeZino );


        transaction.commit();  // save all to database
        //transaction.rollback();  // cancel all actions that happened till now and roll back

        System.out.println(pet);
        System.out.println(petTypes);

        session.close(); // close the connection to the database
    }
}

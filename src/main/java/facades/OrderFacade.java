package facades;

import entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderFacade {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    private OrderFacade(){}

    public static OrderFacade getInstance(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    public Customer createCustomer(String name, String email) {
        EntityManager em = emf.createEntityManager();
        try{
            Customer customer = new Customer(name, email);
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }

    public Customer findCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            //check for null and throw exception..
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

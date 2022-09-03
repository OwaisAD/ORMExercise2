package facades;

import entities.Customer;
import entities.Order;
import entities.Product;

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

    public Product createProduct(String name, String description, int price) {
        EntityManager em = emf.createEntityManager();
        try {
            Product product = new Product(name, description, price);
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return product;
        } finally {
            em.close();
        }
    }


    public Product findProduct(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Product product = em.find(Product.class, id);
            // also needs exception handling
            return product;
        } finally {
            em.close();
        }
    }


    public Order addOrderToCustomer(int customerId) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, customerId); // bedre at bruge em.find da objektet vil være managed
            Order order = new Order();

            em.getTransaction().begin();
                em.persist(order); // manageren kender ikke order, derfor persistere vi den - den kender dog customer grundet e.find
                customer.addOrder(order);
            em.getTransaction().commit();

            return order;
        }finally {
            em.close();
        }
    }
}

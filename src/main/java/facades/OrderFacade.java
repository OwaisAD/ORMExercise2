package facades;

import entities.Customer;
import entities.Order;
import entities.Orderline;
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

    public Orderline createOrderline(int orderId, int productId, int quantity) {
        EntityManager em = emf.createEntityManager();
        try{
            Order order = em.find(Order.class, orderId);
            Product product = em.find(Product.class, productId);
            Orderline orderline = new Orderline(quantity);

            if(order != null && product != null) {
                em.getTransaction().begin();
                    em.persist(orderline);
                    orderline.setOrder(order);
                    orderline.setProduct(product);
                em.getTransaction().commit();
            }
            return orderline;
        } finally {
            em.close();
        }
    }

    public List<Order> listCustomerOrders(int customerId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o JOIN o.customer c WHERE c.id = :c_id", Order.class);
            query.setParameter("c_id", customerId);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    public long getTotalPriceOfOrder(int orderId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT SUM(ol.quantity*ol.product.price) FROM Orderline ol JOIN ol.order o WHERE o.id = :o_id ", Long.class);
            query.setParameter("o_id",orderId);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
}

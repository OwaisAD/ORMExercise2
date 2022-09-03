import entities.Customer;
import entities.Order;
import entities.Orderline;
import entities.Product;
import facades.OrderFacade;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesterClass {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        OrderFacade orderFacade = OrderFacade.getInstance(emf);

        // Create a customer
        //Customer c1 = orderFacade.createCustomer("Owais", "owais@live.dk");
        //System.out.println("Creating a customer: \n" + c1);

        // Find a customer
        int id = 1;
        Customer c2 = orderFacade.findCustomer(id);
        System.out.println("Finding a customer with id " + id + ": \n" + c2);

        // Get all customers
        List<Customer> allCustomers = orderFacade.getAllCustomers();
        System.out.println("List of all customers:");
        for (Customer c : allCustomers) {
            System.out.println(c);
        }

        // Create a product
        //Product p1 = orderFacade.createProduct("iPhone 14 Max", "Apple telefon", 6999);
        //System.out.println("Creating a product: \n" + p1);

        // Find a product
        Product p2 = orderFacade.findProduct(id);
        System.out.println("Finding a product with id " + id + ": \n" + p2);

        // Create an order and add it to a customer
        /*int customerId = 1;
        Order order = orderFacade.addOrderToCustomer(customerId);
        System.out.println("Creating and adding an order to a customer: \n" + order);*/


        // Create an orderline for a specific product and add it to an order
        Orderline orderline = orderFacade.createOrderline(1,1,2);
        System.out.println("Orderline: " + orderline);

        // Find all orders for a specific customer


        // Find the total price of an order
    }
}

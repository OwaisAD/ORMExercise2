import entities.Customer;
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


        // Find a product


        // Create an order and add it to a customer


        // Create an orderline for a specific product and add it to an order


        // Find all orders for a specific customer


        // Find the total price of an order
    }
}

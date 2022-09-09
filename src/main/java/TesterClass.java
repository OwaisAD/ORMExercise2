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

        int customerId = 1;

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
        //Product p2 = orderFacade.createProduct("iPhone 12 mini", "Apple telefon", 5499);

        // Find a product
        Product p3 = orderFacade.findProduct(id);
        System.out.println("Finding a product with id " + id + ": \n" + p3);

        // Create an order and add it to a customer
        //Order order = orderFacade.addOrderToCustomer(customerId);
        //System.out.println("Creating and adding an order to a customer: \n" + order);


        // Create an orderline for a specific product and add it to an order
        Orderline orderline = orderFacade.createOrderline(3,2,6);
        System.out.println("Orderline: " + orderline);

        // Find all orders for a specific customer
        List<Order> listOfCustomerOrders = orderFacade.listCustomerOrders(customerId);

        // Imperativ programmeing (en stil eller paradigme)
        for (Order o : listOfCustomerOrders) {
            System.out.println(o);
        }
        // BEGGE LØKKER GØR DET SAMME, MEN DEN ENE FOREGÅR I EN FUNKTION
        // Deklarativt programmering eller "functional"
        listOfCustomerOrders.forEach(o -> System.out.println(o));

        // Find the total price of an order
        int orderId = 3;
        long totalPriceOfOrder = orderFacade.getTotalPriceOfOrder(orderId);
        System.out.println("Total price of order with id " + orderId + ": " + totalPriceOfOrder);
    }
}

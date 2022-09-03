package facades;

import javax.persistence.EntityManagerFactory;

public class OrderFacade {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    private OrderFacade(){}

    public OrderFacade getInstance(EntityManagerFactory _emf) {
        if(instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }
}

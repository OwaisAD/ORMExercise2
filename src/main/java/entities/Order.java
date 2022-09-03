package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //fremednøgle siden som ejer relationen mellem Customer og Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date created;

    @OneToMany(mappedBy = "order")
    private Set<Orderline> orderlines = new LinkedHashSet<>();

    public Order() {
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Orderline> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(Set<Orderline> orderlines) {
        this.orderlines = orderlines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", created=" + created +
                '}';
    }
}
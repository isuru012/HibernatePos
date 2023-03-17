package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`Order`") // SELECT * FROM Customer ORDER BY
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderId_generator")
    @SequenceGenerator(name="orderId_generator", sequenceName = "orderId_seq", allocationSize=1)
    @Column(name = "orderId", updatable = false, nullable = false)
    private int oid;
//    @Column(name = "order_date")
    private Date date;
    private int customerId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order() {
    }

    public Order(int oid, Date date, int customerId) {
        this.setOid(oid);
        this.setDate(date);
        this.setCustomerId(customerId);
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

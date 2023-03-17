package lk.ijse.hibernate.entity;

import lk.ijse.hibernate.util.StringPrefixSequenceIdGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_seq", allocationSize=1)
    @Column(name = "id", updatable = false, nullable = false)





//
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hibernate_pos")
//    @GenericGenerator(name = "hibernate_pos",
//    strategy = "org.thoughts.on.java.generators.StringPrefixSequenceIdGenerator",
//
//    parameters = {
//            @Parameter(name = StringPrefixSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
//            @Parameter(name = StringPrefixSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "C00-"),
//            @Parameter(name = StringPrefixSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
//    }
//)
    private  int id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "customer")
    private List<Order> orders=new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer() {
    }

    public Customer(int id, String name, String address) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
    }

    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                '}';
    }
}

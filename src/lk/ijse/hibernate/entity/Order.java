package lk.ijse.hibernate.entity;

import java.sql.Date;

public class Order {
    private int oid;
    private Date date;
    private int customerId;

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

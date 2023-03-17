package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "Order_detail")
public class OrderDetail {
    @Id
    @Column(name = "order_id")
    private int oId;
    private int itemCode;
    private BigDecimal unitPrice;
    private int qty;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "orderId",
            insertable = false, updatable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(
            referencedColumnName = "code",
            insertable = false, updatable = false)
    private Item item;



    public OrderDetail() {
    }

    public OrderDetail(int oId, int itemCode, BigDecimal unitPrice, int qty) {
        this.oId = oId;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

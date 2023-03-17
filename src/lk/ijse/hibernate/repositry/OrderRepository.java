package lk.ijse.hibernate.repositry;

import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Order;
import lk.ijse.hibernate.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;

public class OrderRepository {
    private final Session session;

    public OrderRepository() {
        session = SessionFactoryConfiguration.getInstance().getSession();

    }
    public boolean existOrder(int id){
        Transaction transaction = session.beginTransaction();
        try {
            session.get(Order.class,id);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }
    public int saveOrder(Order order) {
        Transaction transaction = session.beginTransaction();

        try {
            int id = (Integer) session.save(order);
            transaction.commit();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return -1;
        }


    }
    public BigInteger getNext() {
        Query query =
                session.createSQLQuery("select orderId_seq.next_val as num from orderId_seq");

        return (BigInteger) query.uniqueResult();
    }
}

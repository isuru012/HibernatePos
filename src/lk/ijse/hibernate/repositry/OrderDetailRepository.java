package lk.ijse.hibernate.repositry;

import lk.ijse.hibernate.entity.Order;
import lk.ijse.hibernate.entity.OrderDetail;
import lk.ijse.hibernate.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailRepository {
    private final Session session;

    public OrderDetailRepository() {
        session = SessionFactoryConfiguration.getInstance().getSession();

    }

    public int saveOrderDetail(OrderDetail orderDetail) {
        Transaction transaction = session.beginTransaction();

        try {
            int id = (Integer) session.save(orderDetail);
            transaction.commit();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return -1;
        }


    }
}

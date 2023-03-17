package lk.ijse.hibernate.repositry;

import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigInteger;
import java.util.ArrayList;

public class ItemRepository {
    private final Session session;

    public ItemRepository() {
        session = SessionFactoryConfiguration.getInstance().getSession();

    }

    public ArrayList<Item> allItems() {
        Transaction transaction = session.beginTransaction();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteria = builder.createQuery(Item.class);
            criteria.from(Item.class);
            ArrayList<Item> products = (ArrayList<Item>) session.createQuery(criteria).getResultList();


            transaction.commit();
            return products;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return null;
        }

    }

    public int saveItem(Item item) {
        Transaction transaction = session.beginTransaction();

        try {
            int id = (Integer) session.save(item);
            transaction.commit();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return -1;
        }


    }

    public boolean deleteItem(int id){
        Transaction transaction = session.beginTransaction();
        try {
            Item item=session.load(Item.class,id);

            session.delete(item);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateItem(Item item){
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(item);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            session.close();
            return false;
        }
    }
    public boolean existItem(int id){
        Transaction transaction = session.beginTransaction();
        try {
            session.get(Item.class,id);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }
    public BigInteger getNext() {
        Query query =
                session.createSQLQuery("select item_seq.next_val as num from item_seq");

        return (BigInteger) query.uniqueResult();
    }

    public Item getItemById(int id){
        Transaction transaction = session.beginTransaction();
        try {
            Item load = session.load(Item.class, id);
            transaction.commit();
            return  load;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            session.close();
            return null;
        }
    }
}

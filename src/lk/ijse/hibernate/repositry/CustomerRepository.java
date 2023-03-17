package lk.ijse.hibernate.repositry;

import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigInteger;
import java.util.ArrayList;

public class CustomerRepository {
    private final Session session;

    public CustomerRepository() {
        session = SessionFactoryConfiguration.getInstance().getSession();
    }

    public ArrayList<Customer> allCustomers() {
        Transaction transaction = session.beginTransaction();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
            criteria.from(Customer.class);
            ArrayList<Customer> products = (ArrayList<Customer>) session.createQuery(criteria).getResultList();


            transaction.commit();
            return products;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return null;
        }


    }

    public int saveCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();

        try {
            int id = (Integer) session.save(customer);
            transaction.commit();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return -1;
        }


    }

    public boolean updateCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(customer);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            session.close();
            return false;
        }
    }

    public boolean deleteCustomer(int id){
        Transaction transaction = session.beginTransaction();
        try {
            Customer customer=session.load(Customer.class,id);

            session.delete(customer);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        }
    }

    public boolean existCustomer(int id){
        Transaction transaction = session.beginTransaction();
        try {
            session.get(Customer.class,id);
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
                session.createSQLQuery("select customer_seq.next_val as num from customer_seq");

        return (BigInteger) query.uniqueResult();
    }
    public Customer getCustomerById(int id){
        Transaction transaction = session.beginTransaction();
        try {
            Customer load = session.load(Customer.class, id);
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
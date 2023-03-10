package lk.ijse.hibernate.repositry;

import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CustomerRepository {
    private final Session session;

    public CustomerRepository() {
        session = SessionFactoryConfiguration.getInstance().getSession();
    }

    public ArrayList allCustomers() {
        Transaction transaction = session.beginTransaction();
        try {
            ArrayList arrayList = (ArrayList) session.createQuery("from AppInitializer").list();
            transaction.commit();
            return arrayList;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return null;
        }


    }

    public Long saveCustomer(Customer customer) {
        Transaction transaction = session.beginTransaction();

        try {
            Long id = (Long) session.save(customer);
            transaction.commit();
            return id;
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println(ex);
            ex.printStackTrace();
            return -1L;
        }


    }

    public boolean updateCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();
        try {
            session.update(customer);
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

    public boolean deleteCustomer(String id){
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

    public boolean existCustomer(String id){
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


}
package com.riccio.customtracker.dao;

import com.riccio.customtracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session

        Session currentSession = sessionFactory.getCurrentSession();

        //get all customers and sort them by lastname
        Query<Customer> listCustomerQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
        //execute query and get result list
        List<Customer> customers = listCustomerQuery.getResultList();
        //return results
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {

        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = currentSession.get(Customer.class, id);
        return customer;

    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query deleteByIdQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        deleteByIdQuery.setParameter("customerId", customerId);
        deleteByIdQuery.executeUpdate();
    }

    @Override
    public List<Customer> search(String searchName) {
        if (searchName!=null && searchName.trim().length()>0) {
            Session currentSession = sessionFactory.getCurrentSession();
            Query searchCustomers = currentSession.createQuery("from Customer c where c.lastName like :searchName");
            searchCustomers.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
            List<Customer> customers = searchCustomers.getResultList();

            return customers;
        }else{
            return getCustomers();
        }
    }
}

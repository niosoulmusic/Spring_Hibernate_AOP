package com.riccio.customtracker.service;

import com.riccio.customtracker.dao.CustomerDAO;
import com.riccio.customtracker.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public Customer getCustomer(int id) {

        return customerDAO.getCustomer(id);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    @Override
    @Transactional
    public List<Customer> search(String searchName) {
        return customerDAO.search(searchName);
    }
}

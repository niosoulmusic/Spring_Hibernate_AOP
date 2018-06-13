package com.riccio.customtracker.dao;


import com.riccio.customtracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    public Customer getCustomer(int customerId);

    public void saveCustomer(Customer customer);

    public void deleteCustomer(int customerId);

    public List<Customer> search(String searchName);

}

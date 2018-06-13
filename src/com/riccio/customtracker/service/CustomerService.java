package com.riccio.customtracker.service;

import com.riccio.customtracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public Customer getCustomer(int id);
    //public void saveCustomer();
    public void saveCustomer(Customer customer);

    public void deleteCustomer(int id);

    public List<Customer> search(String name);
}

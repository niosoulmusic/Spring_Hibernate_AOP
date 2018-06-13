package com.riccio.customtracker.controller;

import com.riccio.customtracker.entity.Customer;
import com.riccio.customtracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //inject customer dao
    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public String listCustomer(Model model){

        //get customers from the dao
        List<Customer> customers = customerService.getCustomers();
        //add thos customers to the model
        model.addAttribute(customers);

        return "list-customer";
    }

    @GetMapping("/showFormAdd")
    public String showFormAdd(Model model){

        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){

        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId,Model model){


        //get teh customer from service (who internally get it from database)
        Customer customer = customerService.getCustomer(customerId);
        //set the customer as a model attribute to pre populate the form
        model.addAttribute("customer",customer);

        //send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customerId") int customerId){

        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers (@RequestParam("searchName") String searchName,Model model){
        List<Customer> customers = customerService.search(searchName);
        model.addAttribute("customerList",customers);

        return "list-customer";
    }
}

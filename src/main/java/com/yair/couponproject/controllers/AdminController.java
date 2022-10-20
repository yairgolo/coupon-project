package com.yair.couponproject.controllers;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
@CrossOrigin(exposedHeaders = "Authorization", origins = "*")
public class AdminController {

    // ------------- http request for admin -------------
    private final AdminService adminService;

    // add company request
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-company")
    public Company createCompany(@RequestBody Company company) throws AppException {
        return adminService.addCompany(company);
    }

    // update company request
    @PutMapping("/update-company")
    public Company updateCompany(@RequestBody Company company) throws AppException {
        return adminService.updateCompany(company);
    }

    // delete company request
    @DeleteMapping("/delete-company/{id}")
    public void deleteCompany(@PathVariable Long id) {
        adminService.deleteCompany(id);
    }

    // get company request
    @GetMapping("/get-company/{id}")
    public Company getCompany(@PathVariable long id) throws AppException {
        return adminService.getCompanyById(id);
    }

    // get all companies request
    @GetMapping("/get-all-companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }

    // add customer request
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-customer")
    public Customer createCustomer(@RequestBody Customer customer) throws AppException {
        return adminService.addCustomer(customer);
    }

    // update customer request
    @PutMapping("/update-customer")
    public Customer updateCustomer(@RequestBody Customer customer) throws AppException {
        return adminService.updateCustomer(customer);
    }

    // delete customer request
    @DeleteMapping("/delete-customer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        adminService.deleteCustomer(id);
    }

    // get customer request
    @GetMapping("/get-customer/{id}")
    public Customer getCustomer(@PathVariable long id) throws AppException {
        return adminService.getCustomerById(id);
    }

    // get all customers request
    @GetMapping("/get-all-customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }
}

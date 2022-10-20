package com.yair.couponproject.services;

import com.yair.couponproject.entities.Company;
import com.yair.couponproject.entities.Customer;
import com.yair.couponproject.errors.exceptions.AppException;
import com.yair.couponproject.repositories.CompanyRepository;
import com.yair.couponproject.repositories.CouponRepository;
import com.yair.couponproject.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;

    // add a company
    public Company addCompany(Company company) throws AppException {
        if (companyRepository.existsByName(company.getName())) {
            throw new AppException("the company with name: " + company.getName() + " is exists!");
        }
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new AppException("the company with email: " + company.getEmail() + " is exists!");
        }
        return companyRepository.save(company);
    }

    // update a company
    public Company updateCompany(Company company) throws AppException {
        Company company1 = companyRepository.findById(company.getId()).orElse(null);
        if (company1 == null) {
            throw new AppException("the company with id: " + company.getId() + " is not found!");
        }
        if (!company.getName().equals(company1.getName())) {
            throw new AppException("you cant update the company name!");
        }
        return companyRepository.save(company);
    }

    // delete a company
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
        couponRepository.deleteByCompanyId(id);
    }

    // get all companies
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    // get a company by id
    public Company getCompanyById(Long id) throws AppException {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new AppException("the company with id: " + id + " is not found!");
        }
        return company.get();
    }

    // add a customer
    public Customer addCustomer(Customer customer) throws AppException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new AppException("the customer with email: " + customer.getEmail() + " is exists!");
        }
        return customerRepository.save(customer);
    }

    // update a customer
    public Customer updateCustomer(Customer customer) throws AppException {
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        if (customer1.isEmpty()) {
            throw new AppException("the customer with id: " + customer.getId() + " is not found!");
        }
        return customerRepository.save(customer);
    }

    // delete a customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id );
    }

    // get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // get a customer by id
    public Customer getCustomerById(Long id) throws AppException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new AppException("the customer with id: " + id + " is not found!");
        }
        return customer.get();
    }

}

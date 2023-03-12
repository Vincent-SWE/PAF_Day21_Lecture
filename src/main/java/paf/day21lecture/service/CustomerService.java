package paf.day21lecture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day21lecture.model.Customer;
import paf.day21lecture.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired 
    CustomerRepository custRepo; 
 
    public List<Customer> retrieveAllCustomers() { 
        return custRepo.getAllCustomers(); 
    } 

    // doing offset
    public List<Customer> retrieveAllCustomersWithLimitOffset(int limit, int offset) {
        return custRepo.getAllCustomersWithLimitOffset(limit, offset);
    }

    
    public Customer getCustomerById(int id) {
        return custRepo.getCustomerById(id);
    }


}

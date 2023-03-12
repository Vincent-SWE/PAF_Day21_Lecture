package paf.day21lecture.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paf.day21lecture.model.Customer;
import paf.day21lecture.service.CustomerService;

@RestController
@RequestMapping(path="/api/customers")
public class CustomerController {
    
    @Autowired
    CustomerService custSvc;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return custSvc.retrieveAllCustomers();
    }


    @GetMapping("/limit")
    public List<Customer> getAllCustomers(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return custSvc.retrieveAllCustomersWithLimitOffset(limit, offset);
    }


    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") int id) {
        return custSvc.getCustomerById(id);
    }

    // // the code below is incomplete, go pull from Darryl's github
    // @GetMapping("/{customer_id/orders}")
    // public Customer getCustomerById(@PathVariable("id") int id) {
    //     return custSvc.getCustomerById(id);
    // }



}

package paf.day21lecture.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import paf.day21lecture.model.Customer;

@Repository
public class CustomerRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;



    private final String findAllSQL = "select * from customer";


    private final String findAllSQLLimitOffset = "select * from customer limit ? offset ?";


    private final String findByIdSQL = "select * from customer where id = ?";

    //     // the code below is incomplete, go pull from Darryl's github
    // private final String findorderByCustomer = "select * from customer where id = ?";




    // Learning to get all customers
    public List<Customer> getAllCustomers() {
        // look at slide 24
        final List<Customer> custList = new ArrayList<Customer>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);

        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            custList.add(cust);
            
        }

        return Collections.unmodifiableList(custList);

    }


    // Learning to get all customers with offset
    public List<Customer> getAllCustomersWithLimitOffset(int limit, int offset) {
        final List<Customer> custList = new ArrayList<Customer>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQLLimitOffset, limit, offset);

        while (rs.next()) {
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setFirstName(rs.getString("first_name"));
            cust.setLastName(rs.getString("last_name"));
            cust.setDob(rs.getDate("dob"));
            custList.add(cust);
        }
        return Collections.unmodifiableList(custList);
    }


    // Learning to get customer by ID
    public Customer getCustomerById(int id) {
        return jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Customer.class), id);
    }





    // // the code below is incomplete, go pull from Darryl's github
    // public List<Customer> getAllCustomerOrder(int customer_id) {

    //     final List<Customer> custList = new ArrayList<Customer>();

    //     final SqlRowSet rs = jdbcTemplate.queryForRowSet(findAllSQL);

    //     while (rs.next()) {
    //         Customer cust = new Customer();
    //         cust.setId(rs.getInt("id"));
    //         cust.setFirstName(rs.getString("first_name"));
    //         cust.setLastName(rs.getString("last_name"));
    //         cust.setDob(rs.getDate("dob"));
    //         custList.add(cust);
            
    //     }

    //     return Collections.unmodifiableList(custList);

    // }







}

package paf.day21lecture.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf.day21lecture.model.Employee;
import paf.day21lecture.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

// Autowired
// Then @RequestBody and @PathVariables
// Then enclose in ResponseEntity
// then set the correct type (Eg. the "Boolean bSaved" in Boolean bSaved = emp.Svc.save(employee))
// then return the ResponseEntity
// then put in the mappings (Eg. PostMapping, PutMapping, DeleteMapping)

// for the mapping path we use "Kebab case"

    @Autowired
    EmployeeService empSvc;

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody Employee employee) {
        Boolean bSaved = empSvc.save(employee);

        return new ResponseEntity<Boolean>(bSaved, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody Employee employee) {
        Integer iUpdated =  empSvc.update(employee);

        return new ResponseEntity<Integer>(iUpdated, HttpStatus.OK);
    }

    @DeleteMapping("{/employee-id}")
    public ResponseEntity<Integer> delete(@PathVariable("employee-id") Integer id) {
        Integer iDeleted =  empSvc.delete(id);

        return new ResponseEntity<Integer>(iDeleted, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = empSvc.findAll();

        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<Employee> findByEmployeeId(@PathVariable("employee-id") Integer employeeId) {
        Employee employee = empSvc.findByEmployeeId(employeeId);

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }




}

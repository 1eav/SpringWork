package org.example.springexample.controller;

import org.example.springexample.entity.Employee;
import org.example.springexample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElse(null);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List <Employee> employees = repository.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployees(@RequestBody Employee employee) {
        Employee createEmployee = repository.save(employee);
        return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Employee> updateEmployees(@RequestBody Employee employee) {
        if (repository.existsById(employee.getId())) {
            Employee updateEmployee = repository.save(employee);
            return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
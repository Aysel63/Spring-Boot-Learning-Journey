package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private JsonMapper jsonMapper;


    // quick and dirty: inject employee dao(use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
        this.employeeService = theEmployeeService;
        jsonMapper = theJsonMapper;
    }


    // expose "/employees" and return a list of employees

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET/employees//{employeIf}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException(("Employee id not found-" + employeeId));

        }
        return theEmployee;
    }

    // add mapping for POST/employees-add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update eexisting employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PATCH

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {
        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tempEmployee == null) {
            throw new RuntimeException(("Employee id not found-" + employeeId));
        }

        // throw exception if request body caontains "id" key
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body-" + employeeId);
        }

        Employee patchEmployee = jsonMapper.updateValue(tempEmployee, patchPayload);
        Employee dbEmployee = employeeService.save(patchEmployee);
        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeId}- delete employee
    @DeleteMapping("/employees/{employeId}")
    public String deleteEmployee(@PathVariable int employeId){
        Employee tempEmployee= employeeService.findById(employeId);

        // throe exception if null

        if(tempEmployee == null){
            throw new RuntimeException(("Employee id not found-" + employeId));
        }
        employeeService.deleteById(employeId);
        return "Deleted employee id -" + employeId;
    }

}

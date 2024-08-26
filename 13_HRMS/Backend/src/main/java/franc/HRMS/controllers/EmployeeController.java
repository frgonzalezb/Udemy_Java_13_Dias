package franc.HRMS.controllers;

import franc.HRMS.exceptions.EmployeeNotFoundException;
import franc.HRMS.models.Employee;
import franc.HRMS.services.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hrms")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        logger.info("EmployeeController getEmployees() called.");
        List<Employee> employees = employeeService.getAll()
                .stream()
                .filter(Employee::isActive)
                .toList();
        employees.forEach(employee -> logger.info(employee.toString()));
        logger.info("Employees have been listed successfully.");
        return employees;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        logger.info("EmployeeController addEmployee() called.");
        return employeeService.save(employee);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        logger.info("EmployeeController getEmployee() called.");
        Employee employee = employeeService.getById((long) id);
        if (employee == null) {
            logger.warn("Employee not found with ID: " + id);
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        logger.info("Employee found with ID: " + id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        logger.info("EmployeeController updateEmployee() called.");
        Employee updatedEmployee = employeeService.getById((long) id);
        if (updatedEmployee == null) {
            logger.warn("Employee not found with ID: " + id);
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setJob(employee.getJob());
        updatedEmployee.setSalary(employee.getSalary());
        employeeService.save(updatedEmployee);
        logger.info("Employee updated successfully with ID: " + id);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PutMapping("/employees/{id}/deactivate")
    public ResponseEntity<Employee> deactivateEmployee(@PathVariable("id") int id) {
        logger.info("EmployeeController deactivateEmployee() called.");
        Employee deactivatedEmployee = employeeService.getById((long) id);
        if (deactivatedEmployee == null) {
            logger.warn("Employee not found with ID: " + id);
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        deactivatedEmployee.setActive(false);
        employeeService.save(deactivatedEmployee);
        logger.info("Employee deactivated successfully with ID: " + id);
        return ResponseEntity.ok(deactivatedEmployee);
    }
}

package franc.HRMS.controllers;

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
}

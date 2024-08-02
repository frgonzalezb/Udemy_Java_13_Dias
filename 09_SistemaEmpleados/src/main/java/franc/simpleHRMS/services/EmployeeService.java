package franc.simpleHRMS.services;

import franc.simpleHRMS.models.Employee;
import franc.simpleHRMS.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        // soft deleting only!!
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return;
        }
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }
}

package franc.HRMS.services;

import franc.HRMS.models.Employee;
import franc.HRMS.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = this.getById(id);
        if (employee == null) {
            return;
        }
        employee.setActive(false);
    }
}

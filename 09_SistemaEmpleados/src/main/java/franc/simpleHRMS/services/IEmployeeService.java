package franc.simpleHRMS.services;

import franc.simpleHRMS.models.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> listEmployees();
    public Employee getEmployeeById(Long id);
    public void saveEmployee(Employee employee); // create or update
    public void deleteEmployee(Long id);
}

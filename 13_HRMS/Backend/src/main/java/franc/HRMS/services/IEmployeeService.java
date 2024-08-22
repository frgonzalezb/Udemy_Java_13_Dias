package franc.HRMS.services;

import franc.HRMS.models.Employee;

import java.util.List;

public interface IEmployeeService {
    public List<Employee> getAll();

    public Employee getById(Long id);

    public Employee save(Employee employee);

    public void delete(Long id);
}

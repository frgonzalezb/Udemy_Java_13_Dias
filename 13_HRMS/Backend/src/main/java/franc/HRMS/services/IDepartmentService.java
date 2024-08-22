package franc.HRMS.services;

import franc.HRMS.models.Department;

import java.util.List;

public interface IDepartmentService {
    public List<Department> getAll();

    public Department getById(Long id);
}

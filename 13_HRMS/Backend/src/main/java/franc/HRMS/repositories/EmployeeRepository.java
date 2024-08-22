package franc.HRMS.repositories;

import franc.HRMS.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public class EmployeeRepository extends JpaRepository<Employee, Long> {
}

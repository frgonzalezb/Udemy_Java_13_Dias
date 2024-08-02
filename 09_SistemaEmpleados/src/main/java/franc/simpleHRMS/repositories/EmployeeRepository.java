package franc.simpleHRMS.repositories;

import franc.simpleHRMS.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

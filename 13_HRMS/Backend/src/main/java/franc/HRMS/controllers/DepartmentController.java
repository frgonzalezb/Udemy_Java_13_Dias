package franc.HRMS.controllers;

import franc.HRMS.models.Department;
import franc.HRMS.services.IDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rh-app")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> getDepartments() {
        logger.info("DepartmentController getDepartments() called.");
        List<Department> departments = departmentService.getAll();
        departments.forEach(department -> logger.info(department.toString()));
        logger.info("Departments have been listed successfully.");
        return departments;
    }
}

package franc.simpleHRMS.controllers;

import franc.simpleHRMS.models.Employee;
import franc.simpleHRMS.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(ModelMap model) {
        logger.info("IndexController start()");
        List<Employee> employees = employeeService.listEmployees();
        employees.forEach(employee -> logger.info(employee.toString()));
        // Compartimos el modelo con la vista
        // model.put("employees", employees);
        model.addAttribute("employees", employees);
        return "index"; // index.jsp
    }

}

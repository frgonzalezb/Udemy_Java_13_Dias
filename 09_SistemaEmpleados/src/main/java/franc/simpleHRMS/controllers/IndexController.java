package franc.simpleHRMS.controllers;

import franc.simpleHRMS.models.Employee;
import franc.simpleHRMS.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start(ModelMap model) {
        logger.info("IndexController start()");
        List<Employee> employees = employeeService.listEmployees()
                .stream()
                .filter(employee -> !employee.isDeleted())
                .toList();
        employees.forEach(employee -> logger.info(employee.toString()));
        // Compartimos el modelo con la vista
        // model.put("employees", employees);
        model.put("employees", employees);
        return "index"; // index.jsp
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.GET)
    public String showAddEmployeePage() {
        logger.info("IndexController showAddEmployeePage()");
        return "add-employee"; // add-employee.jsp
    }

    @RequestMapping(value = "/add-employee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employeeForm") Employee employee) {
        logger.info("IndexController addEmployee()");
        employeeService.saveEmployee(employee);
        logger.info("Added employee: " + employee.toString());
        return "redirect:/";
    }

    @RequestMapping(value = "/edit-employee", method = RequestMethod.GET)
    public String showEditEmployeePage(@RequestParam String id, ModelMap model) {
        logger.info("IndexController showEditEmployeePage()");
        Employee employee = employeeService.getEmployeeById(Long.parseLong(id));
        logger.info("Edit employee: " + employee.toString());
        model.put("employee", employee);
        return "edit-employee"; // edit-employee.jsp
    }

    @RequestMapping(value = "/edit-employee", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employeeForm") Employee employee) {
        logger.info("IndexController editEmployee()");
        employeeService.saveEmployee(employee);
        logger.info("Edited employee: " + employee.toString());
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-employee", method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam String id) {
        logger.info("IndexController deleteEmployee()");
        employeeService.deleteEmployee(Long.parseLong(id));
        logger.info("Deleted employee: " + id);
        return "redirect:/";
    }
}

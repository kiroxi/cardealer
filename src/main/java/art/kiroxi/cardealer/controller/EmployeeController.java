package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.forms.CarForm;
import art.kiroxi.cardealer.forms.EmployeeForm;
import art.kiroxi.cardealer.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ModelAndView employees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/view/{employeeId}")
    public ModelAndView employee(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/employees/add")
    public ModelAndView addEmployeeGet() {
        return employeeService.addEmployee();
    }

    @PostMapping("/employees/add")
    public String addEmployeePost(@ModelAttribute EmployeeForm employeeForm) {
        System.out.println(employeeForm);
        return employeeService.addEmployee(employeeForm);
    }

    @GetMapping("/employees/edit/{employeeId}")
    public ModelAndView editCarGet(@PathVariable("employeeId") Long employeeId) {
        return employeeService.editEmployeeGet(employeeId);
    }

    @PostMapping("/employees/edit/{employeeId}")
    public String editCarPost(@PathVariable("employeeId") Long employeeId, @ModelAttribute EmployeeForm employeeForm) {
        System.out.println(employeeForm);
        return employeeService.editEmployeePost(employeeId, employeeForm);
    }

    @GetMapping("/employees/delete/{employeeId}")
    public String deleteService(@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

}

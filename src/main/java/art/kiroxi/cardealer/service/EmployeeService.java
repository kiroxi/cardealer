package art.kiroxi.cardealer.service;

import art.kiroxi.cardealer.domain.*;
import art.kiroxi.cardealer.forms.CarForm;
import art.kiroxi.cardealer.forms.ClientForm;
import art.kiroxi.cardealer.forms.EmployeeForm;
import art.kiroxi.cardealer.repository.DealerRepository;
import art.kiroxi.cardealer.repository.EmployeeRepository;
import art.kiroxi.cardealer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DealerRepository dealerRepository;
    private final RoleRepository roleRepository;

    public ModelAndView getEmployees() {
        ModelAndView mav = new ModelAndView("employees/employees");
        mav.addObject("employees", employeeRepository.findAll(
                Sort.by("dealer.name")
                        .and(Sort.by("role.id"))
                        .and(Sort.by("fullName")))
        );
        return mav;
    }

    public ModelAndView getEmployee(Long employeeId) {
        ModelAndView mav = new ModelAndView("employees/employee");
        mav.addObject("emp", employeeRepository.findById(employeeId).get());
        return mav;
    }

    public ModelAndView addEmployee() {
        ModelAndView mav = new ModelAndView("employees/add_employee");
        mav.addObject("emp", new EmployeeForm());
        mav.addObject("dealers", dealerRepository.findAll());
        mav.addObject("roles", roleRepository.findAll());
        return mav;
    }

    public String addEmployee(EmployeeForm employeeForm) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        DealerEntity dealerEntity = dealerRepository.findById(employeeForm.getDealerId()).get();
        RoleEntity roleEntity = roleRepository.findById(employeeForm.getRoleId()).get();

        employeeEntity.setFirstName(employeeForm.getFirstName());
        employeeEntity.setMiddleName(employeeForm.getMiddleName());
        employeeEntity.setLastName(employeeForm.getLastName());
        employeeEntity.setDealer(dealerEntity);
        employeeEntity.setRole(roleEntity);
        employeeEntity.setPhoneNumber(employeeForm.getPhoneNumber());
        employeeEntity.setEmail(employeeForm.getEmail());

        Long employeeId = employeeRepository.save(employeeEntity).getId();
        return "redirect:/employees/view/" + employeeId;
    }

    public ModelAndView editEmployeeGet(Long employeeId) {
        ModelAndView mav = new ModelAndView("employees/edit_employee");
        mav.addObject("emp", employeeRepository.findById(employeeId).get());
        mav.addObject("carForm", new EmployeeForm());
        mav.addObject("dealers", dealerRepository.findAll());
        mav.addObject("roles", roleRepository.findAll());
        return mav;
    }

    public String editEmployeePost(Long employeeId, EmployeeForm employeeForm) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        if ( employeeEntity != null ) {

            DealerEntity dealerEntity = dealerRepository.findById(employeeForm.getDealerId()).get();
            RoleEntity roleEntity = roleRepository.findById(employeeForm.getRoleId()).get();

            employeeEntity.setFirstName(employeeForm.getFirstName());
            employeeEntity.setMiddleName(employeeForm.getMiddleName());
            employeeEntity.setLastName(employeeForm.getLastName());
            employeeEntity.setDealer(dealerEntity);
            employeeEntity.setRole(roleEntity);
            employeeEntity.setPhoneNumber(employeeForm.getPhoneNumber());
            employeeEntity.setEmail(employeeForm.getEmail());

            employeeRepository.save(employeeEntity);
        }
        return "redirect:/employees/view/" + employeeId;
    }

    public String deleteEmployee(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        if ( employeeEntity != null ) {
            employeeRepository.delete(employeeEntity);
        }
        return "redirect:/employees";
    }

}

package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.forms.ServiceForm;
import art.kiroxi.cardealer.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ServiceController {

    private final ServicesService servicesService;

    @GetMapping("/services")
    public ModelAndView services() {
        return servicesService.getAllServices();
    }

    @GetMapping("/services/add")
    public ModelAndView addServiceGet() {
        return servicesService.addService();
    }

    @PostMapping("/services/add")
    public String addServicePost(@ModelAttribute ServiceForm service) {
        return servicesService.addService(service);
    }

    @GetMapping("/services/edit/{id}")
    public ModelAndView editServiceGet(@PathVariable(name = "id") Long id) {
        return servicesService.editService(id);
    }

    @PostMapping("/services/edit/{id}")
    public String editServicePost(@PathVariable(name = "id") Long id, @ModelAttribute ServiceForm service) {
        return servicesService.editService(id, service);
    }

    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable(name = "id") Long id) {
        return servicesService.deleteService(id);
    }

}

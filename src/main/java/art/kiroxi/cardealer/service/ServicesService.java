package art.kiroxi.cardealer.service;

import art.kiroxi.cardealer.domain.ServiceEntity;
import art.kiroxi.cardealer.forms.ServiceForm;
import art.kiroxi.cardealer.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServiceRepository serviceRepository;

    public ModelAndView getAllServices() {
        ModelAndView mav = new ModelAndView("services/services");
        mav.addObject("services", serviceRepository.findAllByOrderByNameAsc());
        return mav;
    }

    public ModelAndView addService() {
        ModelAndView mav = new ModelAndView("services/add_service");
        mav.addObject("service", new ServiceForm());
        return mav;
    }

    public String addService(ServiceForm service) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(service.getName());
        serviceEntity.setPrice(service.getPrice());
        serviceRepository.save(serviceEntity);
        return "redirect:/services";
    }

    public ModelAndView editService(Long id) {
        ModelAndView mav = new ModelAndView("services/edit_service");
        mav.addObject("service", serviceRepository.findById(id).get());
        return mav;
    }

    public String editService(Long id, ServiceForm service) {
        ServiceEntity serviceEntity = serviceRepository.findById(id).orElse(null);
        if ( serviceEntity != null ) {
            if ( StringUtils.hasText(service.getName()) ) {
                serviceEntity.setName(service.getName());
            }
            if ( service.getPrice() != null ) {
                serviceEntity.setPrice(service.getPrice());
            }
            serviceRepository.save(serviceEntity);
        }
        return "redirect:/services";
    }

    public String deleteService(Long id) {
        ServiceEntity serviceEntity = serviceRepository.findById(id).orElse(null);
        if ( serviceEntity != null ) {
            serviceRepository.delete(serviceEntity);
        }
        return "redirect:/services";
    }

}

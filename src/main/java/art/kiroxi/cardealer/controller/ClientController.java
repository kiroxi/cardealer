package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.domain.ClientEntity;
import art.kiroxi.cardealer.forms.ClientForm;
import art.kiroxi.cardealer.repository.ClientRepository;
import art.kiroxi.cardealer.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clients")
    public ModelAndView clients() {
        return clientService.getClients();
    }

    @GetMapping("/clients/view/{id}")
    public ModelAndView getClient(@PathVariable("id") Long clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping("/clients/add")
    public ModelAndView addClientGet() {
        return clientService.addClient();
    }

    @PostMapping("/clients/add")
    public String addClientPost(@ModelAttribute ClientForm clientForm) {
        return clientService.addClient(clientForm);
    }

    @GetMapping("/clients/edit/{id}")
    public ModelAndView editClientGet(@PathVariable("id") Long clientId) {
        return clientService.editClient(clientId);
    }

    @PostMapping("/clients/edit/{id}")
    public String editClientPost(@PathVariable("id") Long clientId, @ModelAttribute ClientForm clientForm) {
        return clientService.editClient(clientId, clientForm);
    }

}

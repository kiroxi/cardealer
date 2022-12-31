package art.kiroxi.cardealer.service;

import art.kiroxi.cardealer.domain.ClientEntity;
import art.kiroxi.cardealer.forms.ClientForm;
import art.kiroxi.cardealer.repository.ClientRepository;
import art.kiroxi.cardealer.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final SaleRepository saleRepository;

    public ModelAndView getClients() {
        ModelAndView mav = new ModelAndView("clients/clients");
        mav.addObject("clients", clientRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName")));
        mav.addObject("client", new ClientForm());
        return mav;
    }

    public ModelAndView getClient(Long clientId) {
        ModelAndView mav = new ModelAndView("clients/client");
        mav.addObject("client", clientRepository.findById(clientId).get());
        mav.addObject("sales", saleRepository.findSaleEntitiesByClientId(clientId));
        return mav;
    }

    public ModelAndView addClient() {
        ModelAndView mav = new ModelAndView("clients/add_client");
        mav.addObject("client", new ClientForm());
        return mav;
    }

    public String addClient(ClientForm clientForm) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName(clientForm.getFirstName());
        clientEntity.setMiddleName(clientForm.getMiddleName());
        clientEntity.setLastName(clientForm.getLastName());
        clientEntity.setPassport(clientForm.getPassport());
        clientEntity.setBirthDate(clientForm.getBirthDate());
        clientEntity.setPostAddress(clientForm.getPostAddress());
        clientEntity.setPhoneNumber(clientForm.getPhoneNumber());
        clientEntity.setPhoneNumberAlt(clientForm.getPhoneNumberAlt());
        clientEntity.setEmail(clientForm.getEmail());
        clientRepository.save(clientEntity);
        return "redirect:/clients";
    }

    public ModelAndView editClient(Long clientId) {
        ModelAndView mav = new ModelAndView("clients/edit_client");
        mav.addObject("client", clientRepository.findById(clientId).get());
        return mav;
    }

    public String editClient(Long clientId, ClientForm clientForm) {
        ClientEntity clientEntity = clientRepository.findById(clientId).orElse(null);
        if ( clientEntity != null ) {
            clientEntity.setFirstName(clientForm.getFirstName());
            clientEntity.setMiddleName(clientForm.getMiddleName());
            clientEntity.setLastName(clientForm.getLastName());
            clientEntity.setPassport(clientForm.getPassport());
            clientEntity.setBirthDate(clientForm.getBirthDate());
            clientEntity.setPostAddress(clientForm.getPostAddress());
            clientEntity.setPhoneNumber(clientForm.getPhoneNumber());
            clientEntity.setPhoneNumberAlt(clientForm.getPhoneNumberAlt());
            clientEntity.setEmail(clientForm.getEmail());
            clientRepository.save(clientEntity);
        }
        return "redirect:/clients";
    }

}

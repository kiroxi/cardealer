package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.repository.DealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final DealerRepository dealerRepository;

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping("/contacts")
    public ModelAndView contactsPage() {
        ModelAndView mav = new ModelAndView("contacts");
        mav.addObject("contacts", dealerRepository.findAll());
        return mav;
    }

}

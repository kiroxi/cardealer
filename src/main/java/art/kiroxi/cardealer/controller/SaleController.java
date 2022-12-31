package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.forms.SaleForm;
import art.kiroxi.cardealer.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // указание для фреймворка, что это контроллер по URL
@RequiredArgsConstructor // автоматическое создание конструктора с нужными полями класса
public class SaleController {

    private final SaleService saleService; // зависимость на класс с бизнес-логикой

    // вызывается при переходе по пути /sales (GET)
    @GetMapping("/sales")
    public ModelAndView sales() {
        return saleService.getSales();
    }

    // вызывается при перехое по пути /sales/add/N (GET)
    @GetMapping("/sales/add/{carId}") // {carId} - подставляется значение N из URL /sales/add/N
    public ModelAndView addSaleGet(@PathVariable("carId") Long carId) {
        return saleService.addSale(carId);
    }

    // вызывается при отправке формы на странице /sales/add/N (POST)
    @PostMapping("/sales/add/{carId}") // {carId} - подставляется значение N из URL /sales/add/N
    public String addSalesPost(
            @PathVariable("carId") Long carId,
            @ModelAttribute SaleForm saleForm // данные отправленной формы
    ) {
        return saleService.addSale(carId, saleForm);
    }

}

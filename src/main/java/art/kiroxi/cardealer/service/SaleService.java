package art.kiroxi.cardealer.service;

import art.kiroxi.cardealer.domain.CarEntity;
import art.kiroxi.cardealer.domain.SaleEntity;
import art.kiroxi.cardealer.forms.SaleForm;
import art.kiroxi.cardealer.repository.CarRepository;
import art.kiroxi.cardealer.repository.ClientRepository;
import art.kiroxi.cardealer.repository.EmployeeRepository;
import art.kiroxi.cardealer.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Service // указание фреймворку, что это сервис с бизнес-логикой
@RequiredArgsConstructor // автоматическое создание конструктора класса со всеми параметрами
public class SaleService {

    private final SaleRepository saleRepository; // репозиторий продаж (запросы к БД)
    private final CarRepository carRepository; // репозиторий машин (запросы к БД)
    private final ClientRepository clientRepository; // репозиторий клиентов (запросы к БД)
    private final EmployeeRepository employeeRepository; // репозиторий сотрудников (запросы к БД)

    // получение списка продаж
    public ModelAndView getSales() {
        ModelAndView mav = new ModelAndView("sales/sales"); // указание имени шаблона thymeleaf (/sales/sales.html)
        mav.addObject( // добавление списка продаж, в дальнейшем отображается на странице /sales
                "sales",
                saleRepository.findAll( // найти все продажи (стандартный метод)
                        Sort.by("id") // сортировка по id
                )
        );
        return mav;
    }

    // добавление продажи (отрисовка формы)
    public ModelAndView addSale(Long carId) {
        // находим машину в БД по id
        CarEntity car = carRepository.findById(carId).get();

        SaleForm saleForm = new SaleForm(); // создадим пустой класс формы, которая потом будет отправлена

        ModelAndView mav = new ModelAndView("sales/add_sale"); // указание имени шаблона thymeleaf (/sales/add_sale.html)
        // добавление в набор данных конкретную машину, для отображения на странице
        mav.addObject("car", car);
        // добавление в набор данных автосалон, в котором стоит машина, для отображения на странице
        mav.addObject("dealer", car.getDealer());
        // добавление в набор данных объект формы
        mav.addObject("sale", saleForm);
        // добавление в набор данных список всех клиентов, отсортированных по ФИО, для отображения на странице
        mav.addObject("clients", clientRepository.findAll(Sort.by("fullName")));
        // добавление в набор данных список всех менеджеров, отсортированных по названию автосалона и ФИО, для отображения на странице
        mav.addObject(
                "employees",
                employeeRepository.findByRoleId(
                        3L,
                        Sort.by("dealer.name").and(Sort.by("fullName"))
                )
        );
        return mav;
    }

    // обработка отправленной формы на странице
    public String addSale(Long carId, SaleForm saleForm) {
        // создание новой сущности (строка в БД в таблицы sale)
        SaleEntity saleEntity = new SaleEntity();
        // находим машину по id
        CarEntity car = carRepository.findById(carId).get();
        // ставим сущности продажи связь на машину
        saleEntity.setCar(car);
        // ставим сущности продажи связь на клиента
        saleEntity.setClient(clientRepository.findById(saleForm.getClientId()).get());
        // ставим сущности продажи связь на сотрудника
        saleEntity.setManager(employeeRepository.findById(saleForm.getManagerId()).get());
        // ставим сущности продажи связь на автосалон
        saleEntity.setDealer(car.getDealer());
        // ставим сущности продажи дату продажи (текущая дата + время)
        saleEntity.setSaleDate(new Date());
        // сохраняем запись в БД
        saleRepository.save(saleEntity);
        // перенаправляем менеджера на страницу клиента, которому была продана машина
        return "redirect:/clients/view/" + saleForm.getClientId();
    }

}

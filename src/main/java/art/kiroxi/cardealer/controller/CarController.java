package art.kiroxi.cardealer.controller;

import art.kiroxi.cardealer.domain.CarModelEntity;
import art.kiroxi.cardealer.forms.CarForm;
import art.kiroxi.cardealer.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/cars")
    public ModelAndView carMakes() {
        return carService.getCarMakes();
    }

    @GetMapping("/cars/{make}")
    public ModelAndView carsByMake(@PathVariable("make") String make) {
        return carService.getCarsByMake(make);
    }

    @GetMapping("/cars/add")
    public ModelAndView addCarGet() {
        return carService.addCar();
    }

    @PostMapping("/cars/add")
    public String addCarPost(@ModelAttribute CarForm car) {
        return carService.addCar(car);
    }

    @GetMapping("/cars/models")
    @ResponseBody
    public List<CarModelEntity> getCarModels(@RequestParam("make") Long makeId) {
        return carService.getCarModelsByMake(makeId);
    }

    @GetMapping("/cars/edit/{carId}")
    public ModelAndView editCarGet(@PathVariable("carId") Long carId) {
        return carService.editCarGet(carId);
    }

    @PostMapping("/cars/edit/{carId}")
    public String editCarPost(@PathVariable("carId") Long carId, @ModelAttribute CarForm carForm) {
        return carService.editCarPost(carId, carForm);
    }

}

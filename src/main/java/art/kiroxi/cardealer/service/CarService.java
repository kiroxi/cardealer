package art.kiroxi.cardealer.service;

import art.kiroxi.cardealer.domain.*;
import art.kiroxi.cardealer.forms.CarForm;
import art.kiroxi.cardealer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final DealerRepository dealerRepository;
    private final CarMakeRepository carMakeRepository;
    private final CarModelRepository carModelRepository;
    private final CarStatusRepository carStatusRepository;

    public ModelAndView getCarMakes() {
        ModelAndView mav = new ModelAndView("cars/car_makes");
        mav.addObject("makes", carMakeRepository.findAll(Sort.by("name")));
        return mav;
    }

    public ModelAndView getCarsByMake(String make) {
        ModelAndView mav = new ModelAndView("cars/cars");
        mav.addObject("make", make);
        mav.addObject("cars", getCarsForMake(make));
        return mav;
    }

    public List<CarEntity> getCarsForMake(String make) {
        CarMakeEntity carMakeEntity = carMakeRepository.findByNameIgnoreCase(make.toUpperCase(Locale.ROOT));
        //return carRepository.findCarEntitiesByMakeOrderByStatusIdAsc(carMakeEntity);
        return carRepository.findByMake(
                carMakeEntity,
                Sort.by("status.id")
                        .and(Sort.by("year"))
                        .and(Sort.by("model.name"))
        );
    }

    public ModelAndView addCar() {
        ModelAndView mav = new ModelAndView("cars/add_car");
        mav.addObject("car", new CarForm());
        mav.addObject("dealers", dealerRepository.findAll());
        mav.addObject("makes", carMakeRepository.findAll());
        return mav;
    }

    public String addCar(CarForm car) {
        System.out.println(car);
        CarEntity carEntity = new CarEntity();

        DealerEntity dealer = dealerRepository.findById(car.getDealerId()).orElse(null);
        CarMakeEntity carMake = carMakeRepository.findById(car.getMakeId()).orElse(null);
        CarModelEntity carModel = carModelRepository.findById(car.getModelId()).orElse(null);
        CarStatusEntity carStaus = carStatusRepository.findById(1L).get();

        carEntity.setDealer(dealer);
        carEntity.setMake(carMake);
        carEntity.setModel(carModel);
        carEntity.setVin(car.getVin());
        carEntity.setYear(car.getYear());
        carEntity.setColor(car.getColor());
        carEntity.setPrice(car.getPrice());
        carEntity.setStatus(carStaus);

        carRepository.save(carEntity);
        return "redirect:/cars";
    }

    public List<CarModelEntity> getCarModelsByMake(Long makeId) {
        List<CarModelEntity> models = new ArrayList<>();
        CarMakeEntity carMakeEntity = carMakeRepository.findById(makeId).orElse(null);
        if ( carMakeEntity != null ) {
            return carModelRepository.findCarModelEntitiesByMake(carMakeEntity);
        }
        return models;
    }

    public ModelAndView editCarGet(Long carId) {
        ModelAndView mav = new ModelAndView("cars/edit_car");
        mav.addObject("car", carRepository.findById(carId).get());
        mav.addObject("carForm", new CarForm());
        mav.addObject("statuses", carStatusRepository.findAll());
        mav.addObject("dealers", dealerRepository.findAll());
        return mav;
    }

    public String editCarPost(Long carId, CarForm car) {
        CarEntity carEntity = carRepository.findById(carId).orElse(null);
        if ( carEntity != null ) {

            DealerEntity dealer = dealerRepository.findById(car.getDealerId()).orElse(null);
            CarStatusEntity carStaus = carStatusRepository.findById(car.getStatusId()).get();

            carEntity.setDealer(dealer);
            carEntity.setVin(car.getVin());
            carEntity.setYear(car.getYear());
            carEntity.setColor(car.getColor());
            carEntity.setPrice(car.getPrice());
            carEntity.setStatus(carStaus);

            carRepository.save(carEntity);
        }
        return "redirect:/cars/" + carEntity.getMake().getName().toUpperCase();
    }

}

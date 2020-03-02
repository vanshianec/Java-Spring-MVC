package spring_essentials.car_dealer_shop.service.services;

import spring_essentials.car_dealer_shop.service.models.CarServiceModel;

import java.util.List;

public interface CarService {

    CarServiceModel save(CarServiceModel carServiceModel);

    List<CarServiceModel> getCars();

}

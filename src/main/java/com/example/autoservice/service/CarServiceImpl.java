package com.example.autoservice.service;

import java.util.Optional;
import com.example.autoservice.model.Car;
import com.example.autoservice.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car get(Long id) {
        return carRepository.getReferenceById(id);
    }


    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        Optional<Car> carToUpdate = carRepository.findById(car.getId());
        carToUpdate.orElseThrow(() ->
                new RuntimeException("Can't find car by this id: " + car.getId()));
        return carRepository.save(carToUpdate.get());
    }
}

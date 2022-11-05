package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;

import java.util.List;

public interface OwnerService {
    Owner save(Owner owner);

    Owner get(Long id);

    Owner update(Owner owner);

    List<Order> findAllOrdersById(Long id);

    List<Car> findAllCarsById(Long id);
}

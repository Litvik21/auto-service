package com.example.autoservice.service;

import java.util.List;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;

public interface OwnerService {
    Owner save(Owner owner);

    Owner get(Long id);

    Owner update(Owner owner);

    List<Order> findAllOrdersById(Long id);

    List<Car> findAllCarsById(Long id);
}

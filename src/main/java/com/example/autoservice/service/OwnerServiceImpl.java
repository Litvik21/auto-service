package com.example.autoservice.service;

import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner get(Long id) {
        return ownerRepository.getReferenceById(id);
    }

    @Override
    public Owner update(Owner owner) {
        Owner ownerToUpdate = ownerRepository.getReferenceById(owner.getId());
        return ownerRepository.save(ownerToUpdate);
    }

    @Override
    public List<Order> findAllOrdersById(Long id) {
        return ownerRepository.getReferenceById(id).getOrders();
    }

    @Override
    public List<Car> findAllCarsById(Long id) {
        return ownerRepository.getReferenceById(id).getCars();
    }
}

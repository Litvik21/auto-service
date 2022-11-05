package com.example.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;

public interface MasterService {
    Master save(Master master);

    Master update(Master master);

    List<Order> getOrders(Long masterId);

    BigDecimal getSalary(Long masterId);

    Master get(Long id);
}

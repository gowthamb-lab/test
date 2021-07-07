package org.example.Repository;

import org.example.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface orderRepository extends CrudRepository<Orders,String> {

    Page<Orders> findAll(Pageable pageable);
    List<Orders> findAllByDateAndLocalityLocationId(Date date, String locationId);
}

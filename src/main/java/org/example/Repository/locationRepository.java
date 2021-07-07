package org.example.Repository;

import org.example.model.entity.Administrator;
import org.example.model.entity.locations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface locationRepository extends CrudRepository<locations, String> {
    Page<locations> findAll(Pageable pageable);

}

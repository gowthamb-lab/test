package org.example.Repository;

import org.example.model.entity.reservations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface reservationsRepository extends CrudRepository<reservations, String> {

    Page<reservations> findAll(Pageable pageable);

}

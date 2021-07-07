package org.example.Repository;

import org.example.model.entity.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface administratorRepository extends CrudRepository<Administrator,String> {
    Page<Administrator> findAll(Pageable pageable);

}

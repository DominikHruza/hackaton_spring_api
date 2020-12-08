package com.hackatonproject.api.repositories;

import com.hackatonproject.api.models.Heist;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HeistRepository extends CrudRepository<Heist, Integer> {
    Optional<Heist> findByName(String name);
}

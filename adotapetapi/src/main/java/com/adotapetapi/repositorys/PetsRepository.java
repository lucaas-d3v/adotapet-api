package com.adotapetapi.repositorys;

import com.adotapetapi.models.Cuidador;
import com.adotapetapi.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetsRepository extends JpaRepository<Pet, Long> {

    Optional<List<Pet>> findByCuidador(Cuidador cuidador);

    long countByCuidador(Cuidador cuidador);
}

package com.adotapetapi.repositorys;

import com.adotapetapi.models.Cuidador;
import com.adotapetapi.models.Pet;
import com.adotapetapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Long> {

    Optional<Cuidador> findByEmailCuidador(String emailCuidador);

    Optional<Cuidador> findByEmailCuidadorAndSenhaCuidador(String email, String senha);

    // Buscar posts onde o cuidador é o especificado
    @Query("SELECT p FROM Post p WHERE p.cuidador = :cuidador")
    List<Post> findPostsByCuidador(@Param("cuidador") Cuidador cuidador);

    // Buscar pets onde o cuidador é o especificado
    @Query("SELECT pet FROM Pet pet WHERE pet.cuidador = :cuidador")
    List<Pet> findPetsByCuidador(@Param("cuidador") Cuidador cuidador);

    @Query("SELECT c.senhaCuidador FROM Cuidador c WHERE c.emailCuidador = :email")
    Optional<String> getSenhaByEmail(@Param("email") String email);
}
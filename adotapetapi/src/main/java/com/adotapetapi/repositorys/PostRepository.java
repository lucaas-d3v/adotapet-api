package com.adotapetapi.repositorys;

import com.adotapetapi.models.Cuidador;
import com.adotapetapi.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Todos os posts de um cuidador
    List<Post> findByCuidador(Cuidador cuidador);
}

package com.adotapetapi.repositorys;

import com.adotapetapi.models.FotoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPostRepository extends JpaRepository<FotoPost, Long> {


}

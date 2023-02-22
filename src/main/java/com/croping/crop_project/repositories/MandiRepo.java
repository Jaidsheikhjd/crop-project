package com.croping.crop_project.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.Query;

import com.croping.crop_project.entities.Mandi;
// @EnableJpaRepositories
public interface MandiRepo extends JpaRepository<Mandi,Integer> {
    
      //  @Query("SELECT e from where name=?1")
     List<Mandi>  findByNameContaining(String name);

     Page<Mandi> findByTehsilContaining(String tehsil,Pageable pageable);
}

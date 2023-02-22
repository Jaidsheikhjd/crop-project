package com.croping.crop_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croping.crop_project.entities.Crops;

public interface CropsRepo extends JpaRepository <Crops,Integer>{
    
}

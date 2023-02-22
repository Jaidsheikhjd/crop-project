package com.croping.crop_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.croping.crop_project.entities.Crops;
import com.croping.crop_project.entities.SubCrops;

public interface SubCropsRepo extends JpaRepository<SubCrops,Integer> {
    List<SubCrops> findByCrops(Crops crops);
    List<SubCrops> findByNameContaining(String name);
    List<SubCrops> deleteSubCropsByCropsId(Integer cropsId);

}

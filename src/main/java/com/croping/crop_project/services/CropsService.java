package com.croping.crop_project.services;

import com.croping.crop_project.payloads.CropsDto;
import com.croping.crop_project.payloads.payloads.CropResponse;


public interface CropsService {
    
    CropsDto  createCrops(CropsDto cropsDto);
    CropsDto updateCrops(CropsDto cropsDto , Integer cropId);
    CropResponse getAllCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
    void deleteCrops(Integer cropId);
    CropsDto getCropById(Integer cropId);
    
}

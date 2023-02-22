package com.croping.crop_project.services;

import java.util.List;

import com.croping.crop_project.payloads.SubCropsDto;
import com.croping.crop_project.payloads.payloads.PostResponse;


public interface SubCropsService {
    
    SubCropsDto createCrops(SubCropsDto subcropsDto,Integer cropId);
    SubCropsDto updateCrops(SubCropsDto subcropsDto , Integer subcropId);
    PostResponse getAllCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
    void deleteCrops(Integer subcropId);
    SubCropsDto getCropById(Integer subcropId);
     
    List<SubCropsDto> getSubCropsByCrops(Integer cropsId);
    List<SubCropsDto> searchSubCrops(String keyword);
    void deleteSubCropsByCrops(Integer cropsId);

}

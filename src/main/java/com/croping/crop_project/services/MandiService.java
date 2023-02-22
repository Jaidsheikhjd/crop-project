package com.croping.crop_project.services;

import java.util.List;

import com.croping.crop_project.payloads.MandiDto;
import com.croping.crop_project.payloads.payloads.MandiResponse;

public interface MandiService {
    
    MandiDto createMandi(MandiDto mandiDto);
    MandiDto updateMandi(MandiDto mandiDto , Integer mandiId);
    MandiResponse getAllMandi(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
    void deleteMandi(Integer mandiId);
    MandiDto getMandiById(Integer mandiId);
    List<MandiDto> searchMandi(String keyword);
    MandiResponse searchMandiBytehsil(String tehsil,Integer pageNumber, Integer pageSize, String sortBy, String sortdir);
    // List<MandiDto> getAllmandi();
}

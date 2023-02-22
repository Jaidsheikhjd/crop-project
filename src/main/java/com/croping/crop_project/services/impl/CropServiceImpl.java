package com.croping.crop_project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.croping.crop_project.entities.Crops;
import com.croping.crop_project.exceptions.ResourceNotFoundException;
import com.croping.crop_project.payloads.CropsDto;
import com.croping.crop_project.payloads.payloads.CropResponse;
import com.croping.crop_project.repositories.CropsRepo;
import com.croping.crop_project.services.CropsService;


@Service
public class CropServiceImpl implements CropsService
{

       @Autowired
       private CropsRepo cropsRepo;
       @Autowired
       private ModelMapper modelMapper;



    @Override
    public CropsDto createCrops(CropsDto cropsDto) {
       Crops crops=this.dtoToCrops(cropsDto);
        Crops savedCrops=this.cropsRepo.save(crops);
        return this.cropsToDto(savedCrops);
    }

    @Override
    public CropsDto updateCrops(CropsDto cropsDto, Integer cropId) {
        Crops crops=this.cropsRepo.findById(cropId).orElseThrow(()-> new ResourceNotFoundException("Crops","crops id", cropId));
      crops.setName(cropsDto.getName());

      Crops crops1=this.cropsRepo.save(crops);
      CropsDto cropsDto2=this.cropsToDto(crops1);
        return cropsDto2;
    }

    @Override
    public CropResponse getAllCrops(Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
        Sort sorte =(sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest p = PageRequest.of(pageNumber,pageSize,sorte);
          Page<Crops> pagecrop=this.cropsRepo.findAll(p);
          List<Crops> allcrops=pagecrop.getContent();
          List<CropsDto> crps=allcrops.stream().map((crop)->this.modelMapper.map(crop,CropsDto.class)).collect(Collectors.toList());
          CropResponse cropresponse = new CropResponse();
          cropresponse.setContent(crps);
          cropresponse.setPageNumber(pagecrop.getNumber());
          cropresponse.setPageSize(pagecrop.getSize());
          cropresponse.setTotalElement(pagecrop.getTotalElements());
          cropresponse.setTotalPages(pagecrop.getTotalPages());
          cropresponse.setLastPage(pagecrop.isLast());

          return cropresponse;



        // List<Crops> crops=this.cropsRepo.findAll();
        // List<CropsDto> cropsDtos=crops.stream().map((crop)->this.cropsToDto(crop)).collect(Collectors.toList());
    }

    @Override
    public void deleteCrops(Integer cropId) {
       Crops crops=this.cropsRepo.findById(cropId).orElseThrow(()->new ResourceNotFoundException("Crops", "crops id", cropId));
    this.cropsRepo.delete(crops);
        
    }

    @Override
    public CropsDto getCropById(Integer cropId) {
        Crops crops=this.cropsRepo.findById(cropId).orElseThrow(()->new ResourceNotFoundException("Crops", "crops id", cropId));
        CropsDto cropsDto=this.cropsToDto(crops);
        return cropsDto;
    }
   
    public CropsDto cropsToDto(Crops crops){
        CropsDto cropsDto=this.modelMapper.map(crops, CropsDto.class);
           return cropsDto;
    }
    public Crops dtoToCrops(CropsDto cropsDto){
        Crops crops=this.modelMapper.map(cropsDto,Crops.class);
           return crops;
    }

    
}

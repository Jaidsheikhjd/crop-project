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
import com.croping.crop_project.entities.SubCrops;
import com.croping.crop_project.exceptions.ResourceNotFoundException;
import com.croping.crop_project.payloads.SubCropsDto;
import com.croping.crop_project.payloads.payloads.PostResponse;
import com.croping.crop_project.repositories.CropsRepo;
import com.croping.crop_project.repositories.SubCropsRepo;
import com.croping.crop_project.services.SubCropsService;

@Service
public class SubCropsServiceImpl implements SubCropsService {

    @Autowired
    private SubCropsRepo subCropsRepo;
    @Autowired
    private CropsRepo CropsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubCropsDto createCrops(SubCropsDto subcropsDto, Integer cropId) {
        Crops crops = this.CropsRepo.findById(cropId)
        .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", cropId));
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        SubCrops subCrops = this.dtoTosubCrops(subcropsDto);
        subCrops.setCrops(crops);
        SubCrops savedSubcrop = this.subCropsRepo.save(subCrops);
        return this.subcropsToDto(savedSubcrop);
    }

    @Override
    public void deleteCrops(Integer subcropId) {
        SubCrops crops = this.subCropsRepo.findById(subcropId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", subcropId));
        this.subCropsRepo.delete(crops);
    }

    @Override
    public PostResponse getAllCrops(Integer pageNumber, Integer pageSize, String sortBy, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
        Page<SubCrops> pagecrops = this.subCropsRepo.findAll(p);
        List<SubCrops> allcrops = pagecrops.getContent();
        List<SubCropsDto> crops = allcrops.stream().map((crop) -> this.modelMapper.map(crop, SubCropsDto.class))
                .collect(Collectors.toList());

        PostResponse response = new PostResponse();
        response.setContent(crops);
        response.setPageNumber(pagecrops.getNumber());
        response.setPageSize(pagecrops.getSize());
        response.setTotalElement(pagecrops.getTotalElements());
        response.setTotalPages(pagecrops.getTotalPages());
        response.setLastPage(pagecrops.isLast());

        return response;
        // List<SubCrops> subCrops=this.subCropsRepo.findAll(p);
        // List<SubCropsDto>
        // cropsdto=subCrops.stream().map((crops)->this.subcropsToDto(crops)).collect(Collectors.toList());
    }

    @Override
    public SubCropsDto getCropById(Integer subcropId) {
        SubCrops subcrop = this.subCropsRepo.findById(subcropId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", subcropId));
        SubCropsDto dtocrop = this.subcropsToDto(subcrop);
        return dtocrop;
    }

    @Override
    public SubCropsDto updateCrops(SubCropsDto subcropsDto, Integer subcropId) {
        SubCrops subcrop = this.subCropsRepo.findById(subcropId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", subcropId));
        subcrop.setName(subcropsDto.getName());

        SubCrops updatedCrops = this.subCropsRepo.save(subcrop);
        SubCropsDto dto1 = this.subcropsToDto(updatedCrops);
        return dto1;
    }

    public SubCropsDto subcropsToDto(SubCrops crops) {
        SubCropsDto cropsDto = this.modelMapper.map(crops, SubCropsDto.class);
        return cropsDto;
    }

    public SubCrops dtoTosubCrops(SubCropsDto cropsDto)
    {
        SubCrops crops = this.modelMapper.map(cropsDto, SubCrops.class);
        return crops;
    }

    @Override
    public void deleteSubCropsByCrops(Integer cropsId)
 { 
        Crops crop = this.CropsRepo.findById(cropsId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", cropsId));
        List<SubCrops> subcrops = this.subCropsRepo.findByCrops(crop);
        this.CropsRepo.delete(crop);
        this.subCropsRepo.deleteSubCropsByCropsId(cropsId);

    }

    @Override
    public List<SubCropsDto> getSubCropsByCrops(Integer cropsId)
    {
        Crops crop = this.CropsRepo.findById(cropsId)
                .orElseThrow(() -> new ResourceNotFoundException("SubCrops", "subCrop id", cropsId));
        List<SubCrops> subcrop = this.subCropsRepo.findByCrops(crop);
        List<SubCropsDto> cropdto = subcrop.stream().map((crops) -> this.subcropsToDto(crops))
                .collect(Collectors.toList());
        return cropdto;
    }

    @Override
    public List<SubCropsDto> searchSubCrops(String keyword) {

        List<SubCrops> subcrops = this.subCropsRepo.findByNameContaining(keyword);

        List<SubCropsDto> cropsdto = subcrops.stream().map((crops) -> this.subcropsToDto(crops))
                .collect(Collectors.toList());
        return cropsdto;
    }

}

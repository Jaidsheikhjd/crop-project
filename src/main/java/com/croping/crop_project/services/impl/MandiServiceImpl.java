package com.croping.crop_project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.croping.crop_project.entities.Mandi;
import com.croping.crop_project.exceptions.ResourceNotFoundException;
import com.croping.crop_project.payloads.MandiDto;
import com.croping.crop_project.payloads.payloads.MandiResponse;
import com.croping.crop_project.repositories.MandiRepo;
import com.croping.crop_project.services.MandiService;

@Service
public class MandiServiceImpl implements MandiService{

       @Autowired
       private MandiRepo mandiRepo;
       @Autowired
       private ModelMapper modelMapper;

    @Override
    public MandiDto createMandi(MandiDto mandiDto) {
        Mandi mandi=this.dtoToMandi(mandiDto);
        Mandi savedMandi=this.mandiRepo.save(mandi);
        return this.mandiToDto(savedMandi);
    }

    @Override
    public void deleteMandi(Integer mandiId) {
        Mandi mandi=this.mandiRepo.findById(mandiId).orElseThrow(()->new ResourceNotFoundException("mandi", "mandi id", mandiId));
        this.mandiRepo.delete(mandi);
        
    }

    @Override
    public MandiResponse getAllMandi(Integer pageNumber, Integer pageSize, String sortBy, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Mandi> pagemandi=this.mandiRepo.findAll(p);
          List<Mandi> allmandi=pagemandi.getContent();
          List<MandiDto> man=allmandi.stream().map((mandis)->this.modelMapper.map(mandis,MandiDto.class)).collect(Collectors.toList());
          MandiResponse mandiresponse = new MandiResponse();
          
          mandiresponse.setContent(man);
          mandiresponse.setPageNumber(pagemandi.getNumber());
          mandiresponse.setPageSize(pagemandi.getSize());
          mandiresponse.setTotalElement(pagemandi.getTotalElements());
          mandiresponse.setTotalPages(pagemandi.getTotalPages());
          mandiresponse.setLastPage(pagemandi.isLast());

          return mandiresponse;
    }

    @Override
    public MandiDto getMandiById(Integer mandiId)
   {
        Mandi mandi=this.mandiRepo.findById(mandiId).orElseThrow(()->new ResourceNotFoundException("mandi", "mandi id", mandiId));
        MandiDto mandisDto=this.mandiToDto(mandi);
        return mandisDto;
    }

    @Override
    public MandiDto updateMandi(MandiDto mandiDto, Integer mandiId) {
        Mandi mandi=this.mandiRepo.findById(mandiId).orElseThrow(()-> new ResourceNotFoundException("mandi","mandi id", mandiId));
        mandi.setName(mandiDto.getName());

      Mandi mandi2=this.mandiRepo.save(mandi);
      MandiDto mandiDto2=this.mandiToDto(mandi2);
        return mandiDto2;
    }

    @Override
    public List<MandiDto> searchMandi(String keyword) {
         
        List<Mandi> mandi=this.mandiRepo.findByNameContaining(keyword);
        List<MandiDto> mandidto=mandi.stream().map((m)->this.mandiToDto(m)).collect(Collectors.toList());
        return mandidto;
    }
    

    // @Override
    // public List<MandiDto> searchMandiByTehsil(String tehsil) {
    //     List<Mandi> mandi=this.mandiRepo.findByTehsilContaining(tehsil);
    //     List<MandiDto> mandidto=mandi.stream().map((m)->this.mandiToDto(m)).collect(Collectors.toList());
    //     return mandidto;
    // }

         

    // @Override
    // public List<MandiDto> getAllmandi() {
    //         List<Mandi> mandi=this.mandiRepo.findAll();
    //        List<MandiDto> mandidto=mandi.stream().map((m)->this.mandiToDto(m)).collect(Collectors.toList());
    //         return mandidto;
    // }

    public MandiDto mandiToDto(Mandi crops){
        MandiDto mandiDto=this.modelMapper.map(crops, MandiDto.class);
           return mandiDto;
    }
    public Mandi dtoToMandi(MandiDto cropsDto){
        Mandi mandi=this.modelMapper.map(cropsDto,Mandi.class);
           return mandi;
    }
    
//     @Override
// public MandiResponse searchMandiByTehsil(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
// String sortdir) {
//     Sort sort = (sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

//     PageRequest p = PageRequest.of(pageNumber,pageSize,sort);
// Page<Mandi>pageMandi=this.mandiRepo.findByTehsilContaining(keyword);
// List<Mandi>allMandis=pageMandi.getContent();

// List<MandiDto>mandiDtos=allMandis.stream().map((mandi)->this.modelMapper.map(mandi, MandiDto.class)).collect(Collectors.toList());

// MandiResponse response=new MandiResponse();

// response.setContentDtos(mandiDtos);
// response.setPageNumber(pageMandi.getNumber());
// response.setPageSize(pageMandi.getSize());
// response.setLastPage(pageMandi.isLast());
// response.setTotalElements(pageMandi.getTotalElements());

// return response;
// //
// }
      
@Override
    public MandiResponse searchMandiBytehsil(String keyword,Integer pageNumber, Integer pageSize, String sortBy, String sortdir) {
        Sort sort = (sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Mandi> pagemandi=this.mandiRepo.findByTehsilContaining(keyword,p);
          List<Mandi> allmandi=pagemandi.getContent();
          List<MandiDto> man=allmandi.stream().map((mandis)->this.modelMapper.map(mandis,MandiDto.class)).collect(Collectors.toList());
          MandiResponse mandiresponse = new MandiResponse();
          
          mandiresponse.setContent(man);
          mandiresponse.setPageNumber(pagemandi.getNumber());
          mandiresponse.setPageSize(pagemandi.getSize());
          mandiresponse.setTotalElement(pagemandi.getTotalElements());
          mandiresponse.setTotalPages(pagemandi.getTotalPages());
          mandiresponse.setLastPage(pagemandi.isLast());

          return mandiresponse;
    }


}

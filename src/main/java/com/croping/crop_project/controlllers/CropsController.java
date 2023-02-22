package com.croping.crop_project.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.croping.crop_project.configue.appConstant;
import com.croping.crop_project.payloads.CropsDto;
import com.croping.crop_project.payloads.payloads.CropResponse;
import com.croping.crop_project.services.CropsService;




@RestController
public class CropsController {
      
    @Autowired
    private CropsService cropsService;

    @GetMapping(value="/Crops")
    public ResponseEntity<CropResponse> getAllCrops(
    @RequestParam(value = "pageNumber",defaultValue =appConstant.PAGE_NUMBER,required = false)Integer pageNumber,
    @RequestParam(value = "pageSize",defaultValue = appConstant.PAGE_SIZE,required = false)Integer pageSize ,
    @RequestParam(value = "sortBy",defaultValue = appConstant.SORT_BY,required = false)String sortBy,
    @RequestParam(value = "sortdir",defaultValue = appConstant.SORT_DIR,required = false)String sortdir) 
    {
        CropResponse responsed =this.cropsService.getAllCrops(pageNumber,pageSize,sortBy,sortdir);
        return new ResponseEntity<CropResponse>(responsed,HttpStatus.OK);  
    }
    @GetMapping(value="/crop/{cropId}")
    public ResponseEntity<CropsDto> getCropById(@PathVariable Integer cropId) {
        CropsDto cropsDto=this.cropsService.getCropById(cropId);
        return ResponseEntity.ok(cropsDto);
    }
    @PostMapping(value="/crop")
    public ResponseEntity<CropsDto> createCrops(@RequestBody CropsDto cropsDto) {
        CropsDto cropsDtos=this.cropsService.createCrops(cropsDto);
        return new ResponseEntity<>(cropsDtos,HttpStatus.CREATED);
    }
    @PutMapping(value="crop/{cropId}")
    public ResponseEntity<CropsDto> updateCrop(@PathVariable Integer cropId, @RequestBody CropsDto cropsDto) {
       CropsDto updatedcrops=this.cropsService.updateCrops(cropsDto, cropId);
       
       return ResponseEntity.ok(updatedcrops);  
    }
    @DeleteMapping("/crop/{cropId}")
    public void deleteUser(@PathVariable Integer cropId){
        this.cropsService.deleteCrops(cropId);
    }
    


}

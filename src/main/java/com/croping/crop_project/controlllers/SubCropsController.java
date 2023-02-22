package com.croping.crop_project.controlllers;

import java.util.List;

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
import com.croping.crop_project.payloads.ApiResponse;
import com.croping.crop_project.payloads.SubCropsDto;
import com.croping.crop_project.payloads.payloads.PostResponse;
import com.croping.crop_project.services.SubCropsService;



@RestController
public class SubCropsController {
    
    @Autowired
    private SubCropsService subcropsService;

    @GetMapping(value="/subcrops")
    public ResponseEntity<PostResponse> getAllSubCrops(
        @RequestParam(value = "pageNumber",defaultValue =appConstant.PAGE_NUMBER,required = false)Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = appConstant.PAGE_SIZE,required = false)Integer pageSize ,
        @RequestParam(value = "sortBy",defaultValue = appConstant.SORT_BY,required = false)String sortBy,
        @RequestParam(value = "sortdir",defaultValue = appConstant.SORT_DIR,required = false)String sortdir
    ) {
        PostResponse response =this.subcropsService.getAllCrops(pageNumber,pageSize,sortBy,sortdir);
    
        return new ResponseEntity<PostResponse>(response,HttpStatus.OK);  
       }
    @GetMapping(value="/subcrop/{cropId}")
    public ResponseEntity<SubCropsDto> getCropById(@PathVariable Integer cropId) {
         SubCropsDto subcrop=this.subcropsService.getCropById(cropId);
        return ResponseEntity.ok(subcrop);
    }
    @PostMapping(value="/crops/{cropId}/subcrop")
    public ResponseEntity<SubCropsDto> createSubCrops(@RequestBody SubCropsDto subCropsDto,@PathVariable Integer cropId) {
          SubCropsDto crops=this.subcropsService.createCrops(subCropsDto,cropId);
        return new ResponseEntity<>(crops,HttpStatus.CREATED);
    }
    @PutMapping(value="subcrop/{cropId}")
    public ResponseEntity<SubCropsDto> updateCrop(@PathVariable Integer cropId, @RequestBody SubCropsDto subCropsDto) {
        SubCropsDto subcrop=this.subcropsService.updateCrops(subCropsDto, cropId); 
        return ResponseEntity.ok(subcrop);
    }
    @DeleteMapping("/subcrop/{cropId}")
    public ApiResponse deleteCrop(@PathVariable Integer cropId) {
       this.subcropsService.deleteCrops(cropId);; 
        return new ApiResponse("crop are successfully deleted !!!", true);
    }
    // subcrops by crops
    @GetMapping("/crops/{cropId}/subcrop")
    public ResponseEntity<List<SubCropsDto>> getSubcropsByCrops(@PathVariable Integer cropId){
        List<SubCropsDto> subcrop=this.subcropsService.getSubCropsByCrops(cropId);
        return new ResponseEntity<List<SubCropsDto>>(subcrop,HttpStatus.OK);	
    }
//    / searchingg
@GetMapping("/subcrop/search/{keywords}")
public ResponseEntity<List<SubCropsDto>> searchSubCropsByName(
    @PathVariable("keywords") String keywords){		
    List<SubCropsDto> result=this.subcropsService.searchSubCrops(keywords);
    return new ResponseEntity<List<SubCropsDto>> (result,HttpStatus.OK);
}
@DeleteMapping("/crops/{cropId}/subcrop")
public ApiResponse deleteSubcropBycrops(@PathVariable Integer cropId){
    this.subcropsService.deleteSubCropsByCrops(cropId);
    return new ApiResponse("All subcrops are successfully deleted!!!",true);
}
                        

}

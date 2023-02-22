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
import com.croping.crop_project.payloads.MandiDto;
import com.croping.crop_project.payloads.payloads.MandiResponse;
import com.croping.crop_project.services.MandiService;

@RestController
public class MandiController {

    @Autowired
    private MandiService mandiService;

    @GetMapping(value = "/mandi")
    public ResponseEntity<MandiResponse> getAllMandi(
            @RequestParam(value = "pageNumber", defaultValue = appConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = appConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = appConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortdir", defaultValue = appConstant.SORT_DIR, required = false) String sortdir) {
        MandiResponse responsed = this.mandiService.getAllMandi(pageNumber, pageSize, sortBy, sortdir);
        return new ResponseEntity<MandiResponse>(responsed, HttpStatus.OK);
    }

    @GetMapping(value = "/mandi/{mandiId}")
    public ResponseEntity<MandiDto> getMandiById(@PathVariable Integer mandiId) {
        MandiDto mandiDto = this.mandiService.getMandiById(mandiId);
        return ResponseEntity.ok(mandiDto);
    }

    @PostMapping(value = "/mandi")
    public ResponseEntity<MandiDto> createMandi(@RequestBody MandiDto mandiDto) {
        MandiDto mandiDt = this.mandiService.createMandi(mandiDto);
        return new ResponseEntity<>(mandiDt, HttpStatus.CREATED);
    }

    @PutMapping(value = "mandi/{mandiId}")
    public ResponseEntity<MandiDto> updateMandi(@PathVariable Integer mandiId, @RequestBody MandiDto mandiDto) {
        MandiDto updatedmandi = this.mandiService.updateMandi(mandiDto, mandiId);

        return ResponseEntity.ok(updatedmandi);
    }

    @DeleteMapping("/mandi/{mandiId}")
    public void deleteMandi(@PathVariable Integer mandiId) {
        this.mandiService.deleteMandi(mandiId);
    }

    @GetMapping("/mandi/search/{keywords}")
    public ResponseEntity<List<MandiDto>> searchMandiByName(
            @PathVariable String keywords) {
        List<MandiDto> result = this.mandiService.searchMandi(keywords);
        return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    }

    // @GetMapping(value = "/mandi/tehsil/{tehsils}")
    // public ResponseEntity<List<MandiDto>> getByTehsil(@PathVariable String
    // tehsils) {
    // List<MandiDto> result = this.mandiService.searchMandiByTehsil(tehsils);
    // return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    // }

    // @GetMapping(value = "/mandi/tehsil/")
    // public ResponseEntity<List<MandiDto>> getall() {
    //     List<MandiDto> result = this.mandiService.getAllmandi();
    //     return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    // }

    // @GetMapping(value = "/mandi/tehsil/{tehsils}")
    // public ResponseEntity<List<MandiDto>> getByTehsil(@PathVariable String
    // tehsils) {
    // if(tehsils.equals(null)){

    // List<MandiDto> result=this.mandiService.getAllmandi();
    // return new ResponseEntity<List<MandiDto>>(result,HttpStatus.OK);
    // }else{
    // List<MandiDto> result = this.mandiService.searchMandiByTehsil(tehsils);
    // return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    // }
    // }
    
    // @GetMapping(value = "/mandi/tehsil/")
    // public ResponseEntity<List<MandiDto>> getByTehsil(@RequestParam("tehsils") String tehsils) {
    //     if (tehsils.equals(null)) {
    //         List<MandiDto> result = this.mandiService.getAllmandi();
    //         return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    //     } else {
    //         List<MandiDto> result = this.mandiService.searchMandiByTehsil(tehsils);
    //         return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
    //     }

        // @GetMapping(value = "/mandi/tehsil/")
        // public ResponseEntity<List<MandiDto>> getByTehsil(@RequestParam("tehsils") String tehsils) {
        //     if (tehsils.equals(null)) {
        //         List<MandiDto> result = this.mandiService.getAllmandi();
        //         return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
        //     } else {
        //         List<MandiDto> result = this.mandiService.searchMandiByTehsil(tehsils);
        //         return new ResponseEntity<List<MandiDto>>(result, HttpStatus.OK);
        //     }
        // }
        @GetMapping(value = "/mandi/tehsil/")
    public ResponseEntity<MandiResponse> SearchMandiByTehsil(
        @RequestParam(value = "keyword", required = false)String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "mandiId", required = false) String sortBy,
            @RequestParam(value = "sortdir", defaultValue = "asc", required = false) String sortdir
            ) {
        MandiResponse responsed = this.mandiService.searchMandiBytehsil(keyword, pageNumber, pageSize, sortBy, sortdir);
        return new ResponseEntity<MandiResponse>(responsed, HttpStatus.OK);
    } 

    

}

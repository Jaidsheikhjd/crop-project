package com.croping.crop_project.payloads.payloads;

import java.util.List;

import com.croping.crop_project.payloads.SubCropsDto;

// import com.croping.crop_project.payloads.SubCropsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    
    private List<SubCropsDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;

}   
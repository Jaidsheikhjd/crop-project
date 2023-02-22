package com.croping.crop_project.payloads.payloads;

import java.util.List;

import com.croping.crop_project.payloads.CropsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CropResponse {
    
    private List<CropsDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;


}

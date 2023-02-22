package com.croping.crop_project.payloads.payloads;

import java.util.List;

import com.croping.crop_project.payloads.MandiDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MandiResponse {
    
    private List<MandiDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;
}

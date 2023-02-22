package com.croping.crop_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subCrops")
public class SubCrops {   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subId;
    private String name;
    private Long cropId;

    @ManyToOne
    private Crops crops;
}

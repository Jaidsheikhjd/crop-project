package com.croping.crop_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Mandi")
public class Mandi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mandiId;
    private String name;
    private String tehsil;
     
}

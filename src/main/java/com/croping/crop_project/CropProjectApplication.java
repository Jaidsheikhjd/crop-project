package com.croping.crop_project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CropProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropProjectApplication.class, args);
		System.out.println("....done....");
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

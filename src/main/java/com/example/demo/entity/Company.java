package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
	@Id
	private Integer company_id;
	
	@NotBlank
	private String company_name;

}

package com.example.demo.entry;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUsers {
	@Id
	private Integer admin_id;
	
	private String admin_name;
	
	private Integer compny_id;
	
	private String pass;
	
	private String mail;

}

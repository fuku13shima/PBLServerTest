package com.example.demo.form;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUsersForm {
	@Id
	private Integer admin_id;
	
	private String admin_name;
	
	private Integer compny_id;
	
	private String admin_pass;
	
	private String admin_mail;
}

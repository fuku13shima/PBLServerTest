package com.example.demo.form;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersForm {
	@Id
	private Integer user_id;
	
	private String user_name;
	
	private Integer compny_id;
	
	private String user_pass;
	
	private String user_mail;
}

package com.example.demo.model;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;

public class userLogin {
	public boolean userLogin(Iterable<Users> usersList , UsersForm form) {
		
		for(Users temp : usersList) {
			if(temp.getUser_name().equals(form.getUser_name())
					&& temp.getUser_pass().equals(form.getUser_pass())
					&& temp.getCompny_id().equals(form.getCompny_id())) {
				return true;
				
			}else {
				return false;
			}
		}		
		
		return false;
	}
}

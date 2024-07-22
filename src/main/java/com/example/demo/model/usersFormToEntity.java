package com.example.demo.model;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;

public class usersFormToEntity {
	//フォームで受け取った情報をエンティティへ詰め替え
		public Users usersFormToEntity(UsersForm form) {
//			System.out.println("詰め替え");
			Users user = new Users();
			
			user.setCompny_id(form.getCompny_id());
			user.setUser_name(form.getUser_name());
			user.setUser_pass(form.getUser_pass());
			user.setUser_mail(form.getUser_mail());
			
			return user;
		}
}

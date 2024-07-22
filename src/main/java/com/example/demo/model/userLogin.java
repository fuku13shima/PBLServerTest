package com.example.demo.model;

import com.example.demo.entity.Users;
import com.example.demo.form.UsersForm;

public class userLogin {
	public boolean userLogin(Iterable<Users> usersList , UsersForm form) {
		
		boolean loginCheck = false;
		
//		System.out.println("ログインモデル" + usersList);
		System.out.println("取得情報" + form);

		
		for(Users temp : usersList) {
//			System.out.println("userテーブル確認");
//			System.out.println(temp.getUser_name() + ":" + form.getUser_name());
//			System.out.println(temp.getUser_pass() + ":" + form.getUser_pass());
//			System.out.println(temp.getCompny_id() + ":" + form.getCompny_id());

			if(temp.getUser_name().equals(form.getUser_name())
					&& temp.getUser_pass().equals(form.getUser_pass())
					&& temp.getCompny_id().equals(form.getCompny_id())) {
				
				loginCheck = true;
				break;
			}else {
				loginCheck = false;
			}
		}		
		
		return loginCheck;
	}
}

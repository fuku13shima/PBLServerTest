package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Meetings;
import com.example.demo.entity.Users;

public interface RegistService {
//	Iterable<Company> selectAll();
	
	//ユーザ登録
	void userRegist(Users usre);
		
		
	Iterable<Meetings> MselectAll();
	Optional<Meetings> Mselect(Integer no);

	
	//会議登録
	void meetingRegist(Meetings meeting);

	
	//ユーザリスト取得
	Iterable<Users> UselectAll();

	//ログインユーザの会社IDのある会議取得
	Iterable<Meetings> homeSelect(Iterable<Meetings> allList, Integer Compay_no);
	
	
}

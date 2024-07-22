package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Meetings;
import com.example.demo.entity.Users;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.MeetingsRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class RegistServicelmpl implements RegistService {
	@Autowired
	CompanyRepository repository;
	
	@Autowired
	MeetingsRepository Mrepository;
	
	@Autowired
	UsersRepository Urepository;

	
	
	//ユーザ登録・更新
	@Override
	public void userRegist(Users usre) {
		System.out.println("ユーザ登録・更新");
		Urepository.save(usre);
		System.out.println("ユーザ登録・更新完了");
	}
	
	//ユーザリスト取得
	@Override
	public Iterable<Users> UselectAll() {
		return Urepository.findAll();
	}
	
	//会議登録・更新
	@Override
	public void meetingRegist(Meetings meeting) {
		System.out.println("会議登録・更新");
		Mrepository.save(meeting);
		System.out.println("会議登録・更新完了");
	}

	//一覧取得（開催日昇順）
	@Override
	public Iterable<Meetings> MselectAll() {
		return Mrepository.findAll(Sort.by(Sort.Direction.ASC, "mtg_date"));
	}
	
	//取得したno(meeting_id)と一致する行を取得したい
	@Override
	public Optional<Meetings> Mselect(Integer no) {
//		Integer selectId = no;
		System.out.println(no + "会議取得");
		return Mrepository.findById(no);
	}
	
	//ログインユーザの会社IDのある会議取得
	@Override
	public Iterable<Meetings> homeSelect(Iterable<Meetings> allList ,Integer Compay_no) {
		List<Meetings> selectList = new ArrayList<>();
		//会議一覧取得
//		Iterable<Meetings> allList = service.MselectAll();
		
		for(Meetings temp: allList) {
			int cnt = temp.getCompany_id().length;
			
			for(int i = 0 ; i < cnt ; i++) {
//				int k = allList.getCompany_id()[i];
				if(temp.getCompany_id()[i] == Compay_no) {
					selectList.add(temp);
				}
			}
			
		}

		System.out.println("取得会議リスト" + selectList);
		
		return selectList;
	}


		
}

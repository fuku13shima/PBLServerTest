package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entry.Company;
import com.example.demo.entry.Meetings;
import com.example.demo.entry.Users;
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
	

	//一覧取得（開催日昇順）
	@Override
	public Iterable<Meetings> MselectAll() {
		return Mrepository.findAll(Sort.by(Sort.Direction.ASC, "mtg_date"));
	}
	
	//取得したno(meeting_id)と一致する行を取得したい
	@Override
	public Optional<Meetings> Mselect(Integer no) {
		Integer selectId = 1;
		
		return Mrepository.findById(selectId);
	}
	
	//取得したno(meeting_id)と一致する行を取得したい
	@Override
	public Optional<Users> WriterSelect(Integer uNo) {
		Integer WriterId = 1;
			
		return Urepository.findById(WriterId);
	}

	@Override
	public void absencesRegist(Meetings meeting) {
		Mrepository.save(meeting);
		
	}

	@Override
	public Iterable<Company> selectAll() {
		
		return null;
	}
		
		
}

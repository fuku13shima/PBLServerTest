package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entry.Company;
import com.example.demo.entry.Meetings;
import com.example.demo.repository.MeetingsRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.CompanyService;

@Controller
public class indMeetingController {
	@Autowired
	CompanyService service;
	
	@Autowired
	MeetingsRepository Mrepository;
	
	@Autowired
	UsersRepository Urepository;

/***home.index***/
		@PostMapping(value = "forMenu")
		public String showList(@RequestParam("no") String no , Company company , Model model) {
			model.addAttribute("no" , no);
			System.out.println("@@@@no" + no);
			int id = Integer.parseInt(no);

//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);

//議事録作成担当者取得
			String writer = Mtemp.getMtg_writer();
			
//			Optional<Users> WriterSelect = Urepository.findById(WriterId);
//			Users Utemp = WriterSelect.get();
//			System.out.println(Utemp);
			model.addAttribute("Writer" , writer);
			
			return "meetingMenu";	
		}
	
		
//案内メール
		@PostMapping(value = "before" , params = "mail")
		public String registMtg(@RequestParam("no") String no  , Company compan , Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
			
			//メール件名作成
			System.out.println(Mtemp.getMtg_title().length());
			
			String tmp = Mtemp.getMtg_title() + "のご案内";
			//改行を除去したかった
			String mTitle = tmp.replaceAll("\n", "").replaceAll("\t", "");
			System.out.println(mTitle);
			model.addAttribute("mTitle" , mTitle);
			
			return "invite";
		}

}

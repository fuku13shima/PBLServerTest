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
import com.example.demo.service.RegistService;

@Controller
public class indMeetingController {
	@Autowired
	RegistService service;
	
	@Autowired
	MeetingsRepository Mrepository;
	
	@Autowired
	UsersRepository Urepository;

/***home.html***/
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
	
		
	/****案内メール作成画面遷移****/
		@PostMapping(value = "before" , params = "mail")
		public String forInvite(@RequestParam("no") String no  , Company compan , Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
			
			//メール件名作成
//			System.out.println(Mtemp.getMtg_title().length());
			String mTitle = Mtemp.getMtg_title() + "のご案内";
			model.addAttribute("mTitle" , mTitle);
			
			return "invite";
		}
		
	/****アジェンダ作成画面遷移****/
		@PostMapping(value = "before" , params = "agenda")
		public String forAgenda(@RequestParam("no") String no  , Company compan , Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
			model.addAttribute("testOutline" , "mtg_outlineてすとだよ\n会議の概要がはいるよ");
			
			return "agenda";
		}
		
		
	/****議事録作成画面遷移****/
		@PostMapping(value = "after" , params = "minutes")
		public String forMinutes(@RequestParam("no") String no  , Company compan , Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
			model.addAttribute("testText" , "mtg_textてすとだよ\n議事録の内容がはいると思われるよ");
			
			return "minutes";
		}

}

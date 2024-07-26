package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Company;
import com.example.demo.entity.Meetings;
import com.example.demo.form.MeetingsForm;
import com.example.demo.model.entityToForm;
import com.example.demo.model.formToEntity;
import com.example.demo.repository.MeetingsRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.RegistService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		public String showList(@RequestParam("no") String no , Model model) {
			model.addAttribute("no" , no);
			System.out.println("forMenu@@@@no" + no);
			Integer id = Integer.parseInt(no);

//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());
//			System.out.println(Mtemp.getMtg_member());
			model.addAttribute("Mselect" , Mtemp);

//議事録作成担当者取得
			String writer = Mtemp.getMtg_writer();
			
//			Optional<Users> WriterSelect = Urepository.findById(WriterId);
//			Users Utemp = WriterSelect.get();
//			System.out.println(Utemp);
			model.addAttribute("Writer" , writer);
			
			return "meetingMenu";	
		}
	

		//会議追加ボタン押下
		@PostMapping(value = "addMtg")
		public String addMtg(Model model , MeetingsForm form ,
				HttpServletRequest request, HttpServletResponse response) {
			System.out.println(form);
			
			//テスト用会社ID
			Integer[] C_idTemp = {1};
			
			form.setCompany_id(C_idTemp);
			//エンティティに詰め替える
			Meetings meeting = new Meetings();
			
			formToEntity fte = new formToEntity();
			meeting = fte.formToEntity(form);
			System.out.println(meeting);
			
			service.meetingRegist(meeting);
			
			//一覧画面遷移用会議リスト取得
			Iterable<Meetings> Mlist = service.MselectAll();
			//セッションスコープ作成
			HttpSession session = request.getSession();
//			// セッションスコープからインスタンスを取得
			Integer Compny_id = (Integer) session.getAttribute("Compny_id");
			
			Iterable<Meetings> temp = service.homeSelect(Mlist , Compny_id);
			
//			Iterable<Meetings> Mlist = service.MselectAll();
//			System.out.println(Mlist);
//			model.addAttribute("Mlist" , Mlist);
			model.addAttribute("Mlist" , temp);
			model.addAttribute("addText" , "追加しました");
			
			return "meetingList";
		}

		//ホーム画面の案内メール作成画面遷移
		@PostMapping(value = "/homeToMail")
		public String addMail(Model model) {
			Meetings Mlist = new Meetings();
			
			model.addAttribute("Mlist" , Mlist);
					
			return "invite";
		}
	
				
/****会議メニュー画面****/				
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
			//詰め替え
			entityToForm etf = new entityToForm();
			MeetingsForm temp = etf.entityToForm(Mtemp);
			
			model.addAttribute("Mselect" , temp);
			model.addAttribute("testOutline" , Mtemp.getMtg_outline());
			
			return "agenda";
		}
		
		
	/****議事録作成画面遷移****/
		@PostMapping(value = "after" , params = "minutes")
		public String forMinutes(@RequestParam("no") String no  , Company compan , Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
//			model.addAttribute("Mselect" , Mtemp);
			
			
			//詰め替え
			entityToForm etf = new entityToForm();
			MeetingsForm temp = etf.entityToForm(Mtemp);
			
			System.out.println("変更日付取得" + temp.getMtg_date());
			
//			model.addAttribute("day" , temp.getMtg_date());
			
			model.addAttribute("Mselect" , temp);
			
			return "minutes";
		}
		
		
		//保存ボタン押下
		@PostMapping(value = "mRegist")
		public String selectAll(MeetingsForm form , @RequestParam("no") String no , @RequestParam("pageText") String pageText ,Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			

			
			
			
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
//			Iterable<absences> aList = service.aSelectAll();
//			model.addAttribute("aList" , aList);
//			System.out.println("保存完了");
			model.addAttribute("saveText" , "保存しました");
			model.addAttribute("testText" , Mtemp.getMtg_text());
			
			return "minutes";
		}
		
		
		@PostMapping(value = "upload")
		public String upload(MeetingsForm form , @RequestParam("no") String no ,Model model) {
			int id = Integer.parseInt(no);
			//該当会議取得
			Optional<Meetings> Mselect = Mrepository.findById(id);
//			System.out.println(Mselect);			
			Meetings Mtemp = Mselect.get();
//			System.out.println(Mtemp.getMeeting_name());			
			
			
//			System.out.println(Mtemp.getMeeting_name());			
			model.addAttribute("Mselect" , Mtemp);
//			Iterable<absences> aList = service.aSelectAll();
//			model.addAttribute("aList" , aList);
//			System.out.println("保存完了");
			model.addAttribute("testText" , Mtemp.getMtg_text());

			return "minutes";
		}

}

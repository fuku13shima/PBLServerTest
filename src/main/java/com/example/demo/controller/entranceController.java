package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entry.Meetings;
import com.example.demo.repository.MeetingsRepository;
import com.example.demo.service.RegistService;

@Controller
public class entranceController {
	@Autowired
	MeetingsRepository Mrepository;
	
	@Autowired
	RegistService service;
	
/****index.html****/
	@RequestMapping(value = "/forRegistar")
    public String forRegistar() {
		
		return "registar";
	}
	
	@RequestMapping(value = "/forLogin")
    public String forLogin() {
		
		return "login";
	}
	
	//開発用会議メニュー直リンク
	@RequestMapping(value = "/testForMeeting")
    public String testForMeeting(Model model) {
		int id = 5;
		Optional<Meetings> Mselect = Mrepository.findById(id);
//		System.out.println(Mselect);			
		Meetings Mtemp = Mselect.get();
//		System.out.println(Mtemp.getMeeting_name());			
		model.addAttribute("Mselect" , Mtemp);
		
		return "meetingMenu";
	}
	
/****login.html****/	
	@PostMapping("login")
	public String login(Model model) {
		Iterable<Meetings> temp = service.MselectAll();
		List<Meetings> Mlist = new ArrayList<>();
		int i = 0;
		
		for(Meetings w : temp) {
			if(i < 5) {
				Mlist.add(w);
			}else {
				break;
			}
			i++;
		}

		
		model.addAttribute("Mlist" , Mlist);
		
		return "home";
	}

	
	
	@GetMapping("/ForList")
	public String ForList(Model model) {
		Iterable<Meetings> Mlist = service.MselectAll();
		
		model.addAttribute("Mlist" , Mlist);
		
		return "meetingList";
	}
	
	
	

}

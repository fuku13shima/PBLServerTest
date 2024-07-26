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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Meetings;
import com.example.demo.entity.Users;
import com.example.demo.form.MeetingsForm;
import com.example.demo.form.UsersForm;
import com.example.demo.model.userLogin;
import com.example.demo.model.usersFormToEntity;
import com.example.demo.repository.MeetingsRepository;
import com.example.demo.service.RegistService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class entranceController {
	@Autowired
	MeetingsRepository Mrepository;
	
	@Autowired
	RegistService service;
	
/************共通************/
	//ログアウト
	@GetMapping("logout")
	public String selectReturn() {
		
		return "index";		
	}
	
/****index.html****/
	//新規登録へ
	@RequestMapping(value = "/forRegistar")
    public String forRegistar() {
		
		return "registar";
	}
	
	//ログインへ
	@RequestMapping(value = "/forLogin")
    public String forLogin() {
		
		return "login";
	}
	
	//開発用会議メニュー直リンク
	@RequestMapping(value = "/testForMeeting")
    public String testForMeeting(Model model) {
		int id = 100;
		Optional<Meetings> Mselect = Mrepository.findById(id);
//		System.out.println(Mselect);			
		Meetings Mtemp = Mselect.get();
//		System.out.println(Mtemp.getMeeting_name());			
		model.addAttribute("Mselect" , Mtemp);
		
		return "meetingMenu";
	}

	/****registar.html****/	
	@PostMapping("regist")
	public String regist(Model model , UsersForm form , 
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("取得データ" + form);
		/*ユーザ登録*/
		//エンティティに詰め替える
		Users user = new Users();
		usersFormToEntity ufte = new usersFormToEntity();
		user = ufte.usersFormToEntity(form);
		System.out.println("つめかえ" + user);
		
		service.userRegist(user);

		System.out.println("登録完了");
		
		return "index";
	}	
	
	
/****login.html****/	
	@PostMapping("login")
	public String login(Model model , UsersForm form , 
			HttpServletRequest request, HttpServletResponse response) {
		//ログイン情報取得
		Iterable<Users> usersList = service.UselectAll();
		userLogin uLogin = new userLogin();
//		System.out.println(usersList);
		boolean loginCheck = uLogin.userLogin(usersList , form);
		System.out.println("ログインチェック" + loginCheck);

		
		if(loginCheck == false) {
			model.addAttribute("errorText" , "ユーザ情報が誤りです");
			
			return "login";
		}
		
		//セッションスコープ作成
		HttpSession session = request.getSession();
		//セッションスコープに会社ID保存
		session.setAttribute("Compny_id", form.getCompny_id());

//		System.out.println("会社IDチェック" + form.getCompny_id());
		
		//ホーム画面表示会議リスト作成
		Iterable<Meetings> allList = service.MselectAll();
		
		Iterable<Meetings> temp = service.homeSelect(allList ,form.getCompny_id());
		
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
		
		model.addAttribute("Compny_id", form.getCompny_id());
		model.addAttribute("Mlist" , Mlist);
		
		return "home";
	}

	
	@PostMapping("/ForHome")
	public String ForHome(@RequestParam("Compny_id") String Compny_id , Model model) {
		int cId = Integer.parseInt(Compny_id);
		//ホーム画面表示会議リスト作成
				Iterable<Meetings> allList = service.MselectAll();
				
				Iterable<Meetings> temp = service.homeSelect(allList ,cId);
				
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
				
				model.addAttribute("Compny_id", cId);
				model.addAttribute("Mlist" , Mlist);
		
		return "home";
	}
	
	@GetMapping("/ForList")
	public String ForList(Model model , 
			HttpServletRequest request, HttpServletResponse response) {
		Iterable<Meetings> Mlist = service.MselectAll();
		
//		//セッションスコープ作成
		HttpSession session = request.getSession();
//		// セッションスコープからインスタンスを取得
		Integer Compny_id = (Integer) session.getAttribute("Compny_id");
		
		Iterable<Meetings> temp = service.homeSelect(Mlist , Compny_id);

		
		model.addAttribute("Mlist" , temp);
		
		return "meetingList";
	}
	
	//会議追加ページ遷移
	@GetMapping("/addMtg")
	public String ForaddMtg(Model model) {
		MeetingsForm mtgForm = new MeetingsForm();

		model.addAttribute("mtgForm" , mtgForm);
		
		return "addMeeting";
	}
	

}

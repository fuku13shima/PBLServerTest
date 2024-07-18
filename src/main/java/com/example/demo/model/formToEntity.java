package com.example.demo.model;

import com.example.demo.entity.Meetings;
import com.example.demo.form.MeetingsForm;

public class formToEntity {
	//フォームで受け取った情報をエンティティへ詰め替え
	public Meetings formToEntity(MeetingsForm form) {
//		System.out.println("詰め替え");
		Meetings meeting = new Meetings();
		//日付フォーマット変更
		dateFormat df = new dateFormat();
		
		meeting.setMtg_id(form.getMtg_id());
		
		meeting.setCompany_id(form.getCompany_id());
		
		meeting.setMtg_title(form.getMtg_title());
		meeting.setMtg_date(df.dateFormat(form.getMtg_date()));
		meeting.setMtg_time(form.getMtg_time());
		meeting.setMtg_place(form.getMtg_place());
		
		meeting.setMtg_writer(form.getMtg_writer());
		meeting.setMtg_member(form.getMtg_member());
		
		meeting.setMtg_topic(form.getMtg_topic());
		meeting.setMtg_outline(form.getMtg_outline());
		meeting.setMtg_text(form.getMtg_text());
		
		
		return meeting;
	}

}

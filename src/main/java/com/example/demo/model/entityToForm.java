package com.example.demo.model;

import com.example.demo.entity.Meetings;
import com.example.demo.form.MeetingsForm;

public class entityToForm {
	//フォームで受け取った情報をエンティティへ詰め替え
	public MeetingsForm entityToForm(Meetings meeting) {
//		System.out.println("詰め替え");
		MeetingsForm fMeeting = new MeetingsForm();
		//日付フォーマット変更
		dateFormat df = new dateFormat();
		
		fMeeting.setMtg_id(meeting.getMtg_id());
		
		fMeeting.setCompany_id(meeting.getCompany_id());
		
		fMeeting.setMtg_title(meeting.getMtg_title());
		
		fMeeting.setMtg_date(df.stringToDate(meeting.getMtg_date()));
////		meeting.setMtg_date(form.getMtg_date());
//		
		fMeeting.setMtg_time(meeting.getMtg_time());
		fMeeting.setMtg_place(meeting.getMtg_place());
		
		fMeeting.setMtg_writer(meeting.getMtg_writer());
		fMeeting.setMtg_member(meeting.getMtg_member());
		
		fMeeting.setMtg_topic(meeting.getMtg_topic());
		fMeeting.setMtg_outline(meeting.getMtg_outline());
		fMeeting.setMtg_text(meeting.getMtg_text());
		
		
		return fMeeting;
	}
}

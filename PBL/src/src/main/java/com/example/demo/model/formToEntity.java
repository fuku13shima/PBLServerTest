package com.example.demo.model;

import com.example.demo.entry.Meetings;
import com.example.demo.form.MeetingsForm;

public class formToEntity {
	public Meetings formToEntity(MeetingsForm form) {
		Meetings meeting = new Meetings();
		
		meeting.setMtg_id(form.getMtg_id());
		
		meeting.setMtg_title(form.getMtg_title());
		meeting.setMtg_date(form.getMtg_date());
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

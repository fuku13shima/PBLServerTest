package com.example.demo.form;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingsForm {
	@Id
	private Integer mtg_id;
	
	private String mtg_title;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date mtg_date;
	
	private String mtg_time;
	
	private String mtg_place;

	private String mtg_writer;

	private String mtg_member;
	
	private String mtg_topic;
	
	private String mtg_outline;
	
	private String mtg_text;
	
}

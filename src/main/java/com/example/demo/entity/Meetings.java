package com.example.demo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meetings {
	@Id
	private Integer mtg_id;
	
	//関係会社ID
	private Integer[] company_id;
	
	//会議名
	private String mtg_title;
	
	//会議日付
	private String mtg_date;
	
	//会議開始時間
	private String mtg_time;
	
	//場所
	private String mtg_place;

	//議事録作成者
	private String mtg_writer;

	//会議参加者
	private String mtg_member;
	
	//議題(ex. 〇〇商品について)
	private String mtg_topic;
	
	//概要(ex. 商品の改善案を検討する)
	private String mtg_outline;
	
	//議事録(？)
	private String mtg_text;
	
}

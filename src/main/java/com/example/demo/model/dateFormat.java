package com.example.demo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateFormat {
	
//フォームのtype="date"で取得した日付を年月日形式の文字列に変換
	public String dateFormat (Date Days){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");	
		String w = sdf.format(Days);
		
//		System.out.println(w);
		
		return w;
	}
	
//年月日形式の文字列をDate型に変換し
//フォームのtype="date"に入るようにする
	public Date stringToDate (String Days){

		Date w = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");	
			w = sdf.parse(Days);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(w);
		
		return w;
	}
	
	
}


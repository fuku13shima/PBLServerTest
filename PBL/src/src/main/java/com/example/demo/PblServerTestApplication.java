package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;

@SpringBootApplication
public class PblServerTestApplication {

	public static void main(String[] args) {
		Model model = null;
		SpringApplication.run(PblServerTestApplication.class, args)
		.getBean(PblServerTestApplication.class).execute(model);
	}

	private void execute(Model model) {
//		model.addAttribute("registLink" , "");
		
	}

}

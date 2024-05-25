package com.iwamih31.MapTest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

	@Autowired
	private MapTestService service;

	/** メインページのURL */
	private final String URL = "/MapTest/";

	@GetMapping("/")
	public String accountBook_index() {
		return "redirect:" + URL;
	}

	@GetMapping("/main")
	public String main0() {
		return "redirect:" + URL;
	}

	@GetMapping("/Main")
	public String main() {
		return "redirect:" + URL;
	}

	/** view 表示に必要な属性データをモデルに登録 */
	private void add_View_Data_(Model model, String template, String title) {
		model.addAttribute("library", template + "::library");
		model.addAttribute("main", template + "::main");
		model.addAttribute("title", title);
	}

}

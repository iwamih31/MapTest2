package com.iwamih31.MapTest2;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/MapTest")
public class Rest_Controller {

	@Autowired
	private MapTestService service;

	/** RequestMappingのURL */
	public String req() {
		return "/MapTest";
	}

	/** RequestMappingのURL + path */
	public String req(String path) {
		return req() + path;
	}

	/** このクラス内の@GetMapping(req() + path)へredirect */
	public String redirect(String path) {
		return "redirect:" + req() + path;
	}

	/** このクラス内の@GetMapping(req() + path)へforward */
	public String forward(String path) {
		return "forward:" + req() + path;
	}

	@PostMapping("/Save")
	public ResponseEntity<List<String>> save(@RequestBody Save_Data data) {
		// 受け取ったデータを処理
		System.out.println("Received data_Id: " + data.data_Id);
		System.out.println("Received party:");
		for (Actor member : data.party) {
			System.out.println("  member:");
			System.out.println("    id: " + member.getId());
			System.out.println("    data_Id: " + member.getData_Id());
			System.out.println("    name: " + member.getActor_name());
			System.out.println("    role: " + member.getRole());
			System.out.println("    exp: " + member.getExp());
			System.out.println("    lev: " + member.getLev());
			System.out.println("    HP: " + member.getHp());
			System.out.println("    MP: " + member.getMp());
			System.out.println("    lev: " + member.getWp());
		}
		System.out.println("Received map_Number: " + data.map_Number);
		System.out.println("Received x: " + data.x);
		System.out.println("Received y: " + data.y);

		// レスポンスを返す
		List<String> response_Data = Arrays.asList(data.data_Id, "1234567890");
    return new ResponseEntity<>(response_Data, HttpStatus.OK);
	}
}

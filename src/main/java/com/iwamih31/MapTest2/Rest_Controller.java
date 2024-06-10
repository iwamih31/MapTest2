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

		// データをコンソールに出力
		service.console_Out(data);

		// データベース更新
		service.save(data);

		// レスポンスを返す
		String data_Key = service.data_Key(Integer.parseInt(data.data_Id));
		List<String> response_Data = Arrays.asList(data.data_Id, data_Key);
    return new ResponseEntity<>(response_Data, HttpStatus.OK);
	}

	@PostMapping("/Good_Person")
	public ResponseEntity<List<String>> good_Person(@RequestBody Save_Data data) {
		List<String> response_Data = service.good_Person(data);
		return new ResponseEntity<>(response_Data, HttpStatus.OK);
	}

	@PostMapping("/Monster")
	public ResponseEntity<List<String>> monster(@RequestBody Save_Data data) {
		List<String> response_Data = service.monster(data);
		return new ResponseEntity<>(response_Data, HttpStatus.OK);
	}

	@PostMapping("/Message")
	public ResponseEntity<List<String>> message(@RequestBody int[] data) {
		// 受け取ったデータを処理
		int data_Id = data[0];
		int count = data[1];
		// データをコンソールに出力
		System.out.println("Received data_Id: " + data_Id);
		System.out.println("Received count: " + count);
		// データベースから表示するメッセージのリストを取得
		List<String> message_List = service.message();
		// レスポンスを返す
		return new ResponseEntity<>(message_List, HttpStatus.OK);
	}

}

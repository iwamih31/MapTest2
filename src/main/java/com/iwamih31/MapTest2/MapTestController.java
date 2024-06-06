package com.iwamih31.MapTest2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/MapTest")
public class MapTestController {

	@Autowired
	private MapTestService service;
	@Autowired
	private HttpSession session;

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

	@GetMapping("/")
	public String index() {
		return redirect("/Main");
	}

	@GetMapping("/Main")
	public String main(
			Model model) {
		model.addAttribute("title", "メイン画面");
		model.addAttribute("req", req());
		model.addAttribute("app_name", req().replace("/", ""));
		return "main";
	}

	@GetMapping("/Start")
	public String start(
			RedirectAttributes redirectAttributes) {
		int[] map_X_Y = service.piece_Position("フィールドA 城A");
		String center_Image = service.center_Image;
		int map_Number = map_X_Y[0];
		int x = map_X_Y[1];
		int y = map_X_Y[2];
		String[][] map_Image = service.map_Image(map_Number, x, y);
		redirectAttributes.addFlashAttribute("map_Image", map_Image);
		redirectAttributes.addFlashAttribute("center_Image", center_Image);
		redirectAttributes.addFlashAttribute("x", x);
		redirectAttributes.addFlashAttribute("y", y);
		return redirect("/Map");
	}

	@GetMapping("/Start2")
	public String start2(
			RedirectAttributes redirectAttributes) {
				int data_Id = 1;
				service.save(data_Id, service.new_Party(data_Id), 0, 0, 0);
		redirectAttributes.addAttribute("data_Id", data_Id);
		redirectAttributes.addAttribute("data_Key", service.data_Key(data_Id));
		return redirect("/Map2");
	}

	@PostMapping("/Map")
	public String map(
			@RequestParam("map_Image")Array map_Image,
			@RequestParam("x")int x,
			@RequestParam("y")int y,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("map_Image", map_Image);
		redirectAttributes.addAttribute("x", x);
		redirectAttributes.addAttribute("y", y);
		return redirect("/Map");
	}

	@GetMapping("/Map")
	public String map(
			@ModelAttribute("map_Image") String[][] map_Image,
			@ModelAttribute("center_Image") String center_Image,
			@ModelAttribute("x")int x,
			@ModelAttribute("y")int y,
			Model model) {
		add_View_Data_(model, "map");
		model.addAttribute("map_Image", map_Image);
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "view";
	}

	@PostMapping("/Map2")
	public String map2(
			@RequestParam("data_Id") int data_Id,
			@RequestParam("party") JSONPObject party_JSON,
			@RequestParam("map_Number") int map_Number,
			@RequestParam("x") int x,
			@RequestParam("y") int y,
			RedirectAttributes redirectAttributes) {

				Actor[] party = service.to_Party(party_JSON);
		service.save(data_Id, party, map_Number, x, y);
		redirectAttributes.addFlashAttribute("data_Id", data_Id);
		return redirect("/Map2");
	}

	@GetMapping("/Map2")
	public String map2(
		@RequestParam("data_Id") Integer data_Id,
		@RequestParam("data_Key") String data_Key,
		Model model) {
			// data_Keyが一致しない場合、トップページに戻る
			if (!data_Key.equals(service.data_Key(data_Id))) return redirect("/");

			// data_Id と data_Key を使用して画面へ渡すデータを取得
			Actor[] party = service.party(data_Id);
			// Actor[] party = service.new_Party(data_Id);
			Data_Info data_Info = service.data_Info(data_Id);
			int map_Number = data_Info.getMap_Number();
			System.out.println("map_Number = " + map_Number);
			int x = data_Info.getX();
			int y = data_Info.getY();
			int[][] map = service.getOriginalMap(map_Number);
			String center_Image = service.center_Image(data_Id);
			String[] map_Image_Names = service.map_Image_Names(map_Number);
			add_View_Data_(model, "map2");
			model.addAttribute("data_Id", data_Id);
			model.addAttribute("party", party);
			model.addAttribute("map_Number", map_Number);
			model.addAttribute("map", map);
			model.addAttribute("x", x);
			model.addAttribute("y", y);
			model.addAttribute("center_Image", center_Image);
			model.addAttribute("map_Image_Names", map_Image_Names);
		return "view2";
	}

	@GetMapping("/Good_Person")
	public String good_Person(
			@RequestParam("data_Id") Integer data_Id,
			@RequestParam("data_Key") String data_Key,
			Model model) {
		// data_Keyが一致しない場合、トップページに戻る
		if (!data_Key.equals(service.data_Key(data_Id))) return redirect("/");
		// 画面へ渡すデータを作成
		String event_name = "良い人";
		String back_Image = service.back_Image(event_name);
		List<String> message = service.first_Message(data_Id);
		Actor[] party = service.party(data_Id);
		// Actor[] party = service.new_Party(data_Id);
		// 画面へ渡すデータを model にセット
		add_View_Data_(model, "event");
		model.addAttribute("data_Id", data_Id);
		model.addAttribute("message", message);
		model.addAttribute("party", party);
		model.addAttribute("back_Image", back_Image);
		model.addAttribute("event_Image", event_name);
		return "view3";
	}

	// @PostMapping("/Save")
	// public Save_Data save(@RequestBody Save_Data data) {
	// 		// ここで受け取ったデータを処理します

	// 		System.out.println("Received data_Id: " + data.data_Id);
	// 		System.out.println("Received party:");
	// 		for (Actor member : data.party) {
	// 			System.out.println("  member:");
	// 			System.out.println("    id: " + member.getId());
	// 			System.out.println("    data_Id: " + member.getData_Id());
	// 			System.out.println("    name: " + member.getActor_name());
	// 			System.out.println("    role: " + member.getRole());
	// 			System.out.println("    exp: " + member.getExp());
	// 			System.out.println("    lev: " + member.getLev());
	// 			System.out.println("    HP: " + member.getHp());
	// 			System.out.println("    MP: " + member.getMp());
	// 			System.out.println("    lev: " + member.getWp());
	// 		}
	// 		System.out.println("Received map_Number: " + data.map_Number);
	// 		System.out.println("Received x: " + data.x);
	// 		System.out.println("Received y: " + data.y);

	// 		// 必要に応じてレスポンスを返します
	// 		return data;
	// }

	// @PostMapping("/Save")
	// public String save(
	// 		@RequestParam("data_Id") int data_Id,
	// 		@RequestParam("party") JSONPObject party_JSON,
	// 		@RequestParam("map_Number") int map_Number,
	// 		@RequestParam("x") int x,
	// 		@RequestParam("y") int y,
	// 		RedirectAttributes redirectAttributes) {

	// 	Actor[] party = service.to_Party(party_JSON);
	// 	service.save(data_Id, party, map_Number, x, y);
	// 	redirectAttributes.addFlashAttribute("data_Id", data_Id);
	// 	return redirect("/Map2");
	// }

	/** view 表示に必要な属性データをモデルに登録 */
	private void add_View_Data_(Model model, String template, String title) {
		model.addAttribute("library", template + "::library");
		model.addAttribute("main", template + "::main");
		model.addAttribute("title", title);
		model.addAttribute("req", req());
		System.out.println("template = " + template);
	}

	/** view 表示に必要な属性データをモデルに登録 */
	private void add_View_Data_(Model model, String template) {
		model.addAttribute("library", template + "::library");
		model.addAttribute("main", template + "::main");
		model.addAttribute("req", req());
		System.out.println("template = " + template);
	}
}

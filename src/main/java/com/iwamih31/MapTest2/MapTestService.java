package com.iwamih31.MapTest2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class MapTestService {

	@Autowired
	private Actor_Repository actor_Repository;
	@Autowired
	private Data_Info_Repository data_Info_Repository;
	@Autowired
	private Party_Info_Repository party_Info_Repository;
	@Autowired
	private Role_Repository role_Repository;
	@Autowired
	private Message_Repository message_Repository;

	private int view_X =15;
	private int view_Y =15;
  public String center_Image = "勇者";

	public int[][] getOriginalMap(int map_Number) {
		int[][] fieldA = {
				{1,2,3,3,3,1,1,1,2,1,1,3,3,2,1,1,2,3,3,3,3,3,3,3,1},
				{0,0,3,3,2,4,1,4,2,1,2,3,3,3,2,0,4,3,3,2,2,3,3,3,3},
				{3,0,0,0,0,2,1,2,4,1,1,2,3,3,3,3,3,3,3,4,1,2,3,3,3},
				{3,3,3,2,1,0,1,2,2,2,1,1,2,3,3,4,3,3,2,2,1,1,2,3,3},
				{3,3,0,1,0,2,2,2,0,4,2,1,2,3,3,3,3,3,4,8,2,1,1,1,3},
				{3,0,1,2,2,1,1,0,0,3,4,0,2,1,4,3,3,1,1,2,4,1,2,1,3},
				{3,1,2,4,2,1,2,2,0,0,3,2,1,1,3,3,1,1,2,3,2,2,1,1,3},
				{4,1,2,1,2,1,1,1,2,1,2,1,1,3,3,3,1,2,9,2,1,1,1,3,3},
				{2,1,1,1,2,2,2,1,2,1,1,1,2,1,5,2,1,1,1,1,1,3,2,5,3},
				{3,2,2,2,2,9,1,1,9,2,1,1,1,1,2,2,2,2,2,3,1,1,1,1,3},
				{1,1,0,0,0,2,1,1,2,1,1,0,0,1,1,2,4,0,0,1,1,3,3,3,3},
				{1,2,3,3,1,1,1,2,4,2,1,3,3,3,1,1,2,1,3,3,1,0,0,9,3},
				{1,1,1,3,3,3,3,1,1,2,1,3,9,3,3,1,1,1,3,2,1,1,0,3,3},
				{3,3,4,3,3,3,1,1,2,0,1,1,1,3,3,3,3,4,3,4,1,3,3,3,3},
				{2,3,3,3,3,1,1,1,2,1,1,1,3,3,4,2,3,3,3,3,3,3,3,3,4},
				{1,2,3,3,2,1,2,1,1,2,1,3,3,2,1,1,2,3,3,3,3,3,3,2,1},
				{8,3,3,3,2,1,1,2,1,2,1,3,3,3,2,0,0,3,3,2,2,3,3,3,2},
				{3,3,3,9,1,2,1,2,1,1,1,2,3,3,3,3,0,0,0,1,1,2,3,3,3},
				{3,3,3,2,1,1,1,5,2,2,1,5,3,3,3,3,3,3,2,2,1,1,2,3,3},
				{2,4,3,2,2,1,3,3,9,1,1,3,3,3,2,2,4,2,1,1,1,1,4,3,3},
				{1,1,3,4,0,1,3,4,3,1,3,3,3,8,0,1,1,2,0,1,1,0,0,3,3},
				{1,2,3,3,1,1,1,1,2,1,1,3,3,3,3,1,2,1,0,2,1,2,2,2,3},
				{1,1,1,3,1,3,3,1,1,3,1,4,3,3,3,1,1,1,3,2,1,2,9,2,3},
				{3,2,1,1,1,3,3,3,1,3,2,2,1,9,3,3,3,3,3,3,1,2,0,2,3},
				{3,3,3,3,3,3,3,1,1,2,1,1,1,1,1,2,3,3,3,2,1,1,1,1,1}
		};
		int[][] castleA_F1 = {
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,1,1,2,3,3,3},
				{3,3,3,3,3,3,3,3,3,3,2,3,3,3,3,3,3,3,3,2,0,2,3,3,3},
				{3,3,3,3,3,3,3,3,1,2,1,2,1,1,1,3,3,3,3,3,3,0,0,3,3},
				{3,3,3,3,3,2,3,2,1,1,1,2,1,2,1,2,3,3,3,3,3,3,0,8,3},
				{3,3,3,3,8,2,3,0,2,1,2,0,0,2,1,1,0,2,2,2,3,3,0,8,3},
				{3,3,3,2,1,2,0,0,2,0,0,0,2,2,2,2,0,0,0,0,0,0,0,3,3},
				{3,3,3,3,1,2,2,0,2,0,2,2,4,0,0,0,2,2,2,2,1,2,3,3,3},
				{3,3,1,3,1,2,0,0,2,0,0,2,2,2,2,0,0,0,0,2,1,1,3,3,3},
				{3,1,1,2,1,2,0,2,2,2,0,2,0,0,0,2,2,0,0,2,1,1,3,3,3},
				{1,1,2,3,1,2,0,0,0,0,0,0,0,2,0,0,0,0,0,2,1,2,3,3,3},
				{3,3,3,3,1,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,1,3,3,3,3},
				{3,3,2,1,1,2,0,0,0,0,0,2,0,5,0,2,0,0,0,2,1,3,3,3,3},
				{3,1,0,2,1,2,0,0,2,0,0,2,0,2,0,0,0,0,0,2,1,3,3,3,3},
				{2,1,1,2,1,2,0,0,2,0,0,2,0,0,0,2,2,2,2,2,1,2,3,3,3},
				{1,2,1,3,1,2,2,0,2,0,2,2,2,0,2,2,4,0,0,2,1,2,3,3,3},
				{3,1,2,3,1,2,0,0,2,0,2,0,0,0,0,0,2,0,0,2,1,3,3,3,3},
				{3,3,2,3,1,2,0,0,2,0,0,0,0,0,0,0,7,2,0,2,1,3,3,3,3},
				{3,3,3,3,1,2,0,2,2,2,2,0,0,0,0,0,2,0,0,2,1,3,3,3,3},
				{3,3,0,1,1,0,0,0,0,4,6,0,0,0,0,0,2,0,0,2,1,2,3,3,3},
				{3,2,2,3,2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,1,3,3,3,3},
				{2,1,1,2,5,1,1,1,1,1,1,1,2,0,2,1,1,1,1,1,1,3,3,3,3},
				{1,1,1,1,2,2,0,8,0,0,8,1,8,8,8,1,8,0,8,8,3,3,3,3,3},
				{1,2,1,1,8,4,0,0,3,3,3,1,1,1,1,1,3,3,3,3,3,3,3,3,1},
				{1,1,1,0,0,2,2,8,3,3,3,3,3,8,3,3,3,3,3,3,3,3,3,3,1},
				{1,0,0,1,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,3,3,3,3}
		};
		int[][] dungeonA_B1 = {
				{ 0, 2, 0, 2, 0, 2, 2, 2, 2, 2, 0, 9, 0, 0, 0, 0, 2, 0, 2, 6, 0, 0, 0, 0, 0 },
				{ 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 0, 2, 0, 2, 6, 6, 6, 6, 6, 2 },
				{ 0, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2 },
				{ 0, 2, 0, 0, 0, 2, 6, 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 2, 6, 0, 5, 2 },
				{ 0, 2, 0, 2, 0, 2, 2, 2, 2, 2, 0, 9, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2 },
				{ 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 0, 2, 0, 2, 6, 2, 0, 0, 0, 0 },
				{ 0, 0, 0, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 0, 0, 0, 2, 6, 2, 0, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 6 },
				{ 0, 0, 2, 2, 0, 2, 0, 2, 0, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 2, 6, 0, 2, 0 },
				{ 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 6, 2, 0, 3, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
				{ 6, 2, 2, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 6, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 6, 2, 0, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 0, 2, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 6, 2, 6, 0, 2, 6 },
				{ 0, 2, 0, 0, 0, 6, 2, 0, 2, 0, 2, 0, 2, 0, 2, 2, 4, 0, 0, 0, 2, 0, 0, 0, 0 },
				{ 0, 2, 2, 2, 2, 2, 6, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 2, 2, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 0, 2, 2, 6 },
				{ 0, 0, 0, 2, 0, 2, 0, 0, 0, 4, 2, 2, 2, 2, 2, 2, 2, 0, 2, 6, 2, 0, 0, 0, 2 },
				{ 2, 2, 6, 2, 0, 0, 2, 2, 2, 2, 5, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 2, 0, 0 },
				{ 0, 2, 2, 2, 4, 0, 0, 0, 0, 2, 6, 0, 0, 2, 2, 0, 2, 0, 2, 6, 2, 5, 2, 2, 0 },
				{ 0, 0, 0, 2, 2, 2, 6, 0, 6, 2, 2, 2, 2, 0, 0, 0, 2, 0, 2, 2, 2, 0, 0, 2, 2 },
				{ 2, 2, 0, 0, 0, 4, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 6, 2, 0, 0, 0 },
				{ 0, 0, 2, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 2, 0, 0, 6, 2, 2, 0 },
				{ 2, 0, 0, 0, 0, 2, 6, 6, 6, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 6, 6, 6, 2 },

		};
		int[][] castleA_F2 = {
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,3,3,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,8,8,2,0,6,2,3,3,3,3,3,3,3,3,8,8,3,3,3},
				{3,3,3,3,3,8,1,0,0,0,8,8,3,2,3,3,3,2,2,2,0,6,8,3,3},
				{3,3,3,3,3,2,2,0,2,2,2,8,2,6,2,8,2,6,6,2,0,0,8,3,3},
				{3,3,3,3,3,2,6,0,0,0,0,0,0,0,0,0,2,0,6,2,0,2,3,3,3},
				{3,3,3,3,3,8,0,0,0,6,2,0,0,7,0,0,0,0,0,0,0,2,3,3,3},
				{3,3,3,3,3,2,6,0,0,0,2,0,0,6,0,0,2,0,6,2,6,2,3,3,3},
				{3,3,3,3,3,2,2,0,2,2,2,0,0,0,0,0,2,6,6,2,2,2,3,3,3},
				{3,3,3,3,3,2,6,0,0,6,2,0,0,0,0,0,2,2,2,2,3,3,3,3,3},
				{3,3,3,3,3,2,2,2,2,2,2,0,0,5,0,0,2,0,6,2,3,3,3,3,3},
				{3,3,3,3,3,2,6,0,0,0,2,0,0,2,0,0,2,0,0,8,3,3,3,3,3},
				{3,3,3,3,3,2,0,0,2,0,0,2,0,0,0,2,2,0,2,2,3,3,3,3,3},
				{3,3,3,3,3,2,2,0,2,0,6,2,2,0,2,2,0,0,6,2,3,3,3,3,3},
				{3,3,3,3,3,8,0,0,0,2,2,6,2,0,0,0,0,0,0,2,3,3,3,3,3},
				{3,3,3,3,3,2,0,0,6,2,0,0,2,2,6,0,2,2,0,8,3,3,3,3,3},
				{3,3,3,3,3,2,0,2,2,6,0,0,0,0,2,2,6,0,0,2,3,3,3,3,3},
				{3,3,3,3,3,2,0,0,0,0,0,0,2,0,0,0,0,0,9,2,3,3,3,3,3},
				{3,3,3,3,2,2,2,2,8,2,2,2,0,0,2,2,8,2,2,2,3,3,3,3,3},
				{3,3,3,2,8,2,3,3,3,3,8,0,0,0,8,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,2,3,3,3,3,3,8,8,8,8,8,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
				{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
		};
		switch (map_Number) {
			case 0:return fieldA;
			case 1:return castleA_F1;
			case 2:return dungeonA_B1;
			case 3:return castleA_F2;
			default:return fieldA;
		}
	}

	int[] piece_Position(String piece_Name) {
		switch (piece_Name) {
			case "フィールドA 城A" 		:return new int[] { 0,  0,  0};
			case "城A 1階 正面出口"		:return new int[] { 1,  1,  8};
			case "城A 1階 階段A"  		:return new int[] { 1,  1, -1};
			case "城A 1階 洞窟A" 			:return new int[] { 1, -3,  6};
			case "城A 1階 洞窟B"			:return new int[] { 1,  4,  2};
			case "城A 2階 階段A"			:return new int[] { 3,  1, -1};
			case "洞窟A 地下1階 入口"	:return new int[] { 2,  7,  7};
			case "洞窟B 地下1階 入口"	:return new int[] { 2,  4,  2};
		default	:return new int[] { 0,  0,  0};
		}
	}

	int[] next_Map(int map_Number, int x, int y) {
		int[] now_Piece = new int[] {map_Number, x, y};
		if (fit(now_Piece, "フィールドA 城A")) 		return piece_Position("城A 1階 正面出口");
		if (fit(now_Piece, "城A 1階 正面出口")) 	return piece_Position("フィールドA 城A");
		if (fit(now_Piece, "城A 1階 階段A")) 			return piece_Position("城A 2階 階段A");
		if (fit(now_Piece, "城A 1階 洞窟A")) 			return piece_Position("洞窟A 地下1階 入口");
		if (fit(now_Piece, "城A 1階 洞窟B")) 			return piece_Position("洞窟B 地下1階 入口");
		if (fit(now_Piece, "城A 2階 階段A")) 			return piece_Position("城A 1階 階段A");
		if (fit(now_Piece, "洞窟A 地下1階 入口")) return piece_Position("城A 1階 洞窟A");
		return piece_Position("フィールドA 城A");
	}

	private boolean fit(int[] piece_Position, String piece_Name) {
		int[] check_Position = piece_Position(piece_Name);
		if (piece_Position[0] == check_Position[0] &&
				piece_Position[1] == check_Position[1] &&
				piece_Position[2] == check_Position[2]) return true;
		return false;
	}

	MapPiece mapPiece(int map_Number, int piece_Number) {
		switch (map_Number) {
			case 0: // フィールドA
				return field(piece_Number);
			case 1: // 城A 1階
				return castle1(piece_Number);
			case 2: // 洞窟1
				return dungeon(piece_Number);
			case 3: // 城A 2階
				return castle2(piece_Number);
		default: // その他
			return field(piece_Number);
		}
	}

	// 0 障害物
	// 1 通路
	// 2 魔物出現率アップ
	// 3 ダメージ
	// 4 別マップへ
	// 5 （入口）別マップへ
	// 6 体力回復
	// 7 アトラクション
	// 8 （出口）別マップへ
	// 9 別マップへ

	private MapPiece dungeon(int piece_Number) {
		switch (piece_Number) {
			case 0 :return new MapPiece("闇", 1); // 通路
			case 1 :return new MapPiece("草", 2);
			case 2 :return new MapPiece("山", 0); // 壁
			case 3 :return new MapPiece("海", 0); // 水
			case 4 :return new MapPiece("洞窟", 4);
			case 5 :return new MapPiece("洞窟", 5); // 階段（入口）
			case 6 :return new MapPiece("山", 2);
			case 7 :return new MapPiece("宝箱", 7);
			case 8 :return new MapPiece("草", 8); // 扉（出口）
			case 9 :return new MapPiece("城", 9);
		default :return new MapPiece("山", 1); // 通れる壁
		}
	}

	private MapPiece field(int piece_Number) {
		switch (piece_Number) {
			case 0 :return new MapPiece("砂", 1); // 道
			case 1 :return new MapPiece("草", 2); // 魔物出現率アップ
			case 2 :return new MapPiece("山", 0); // 障害物
			case 3 :return new MapPiece("海", 0); // 障害物
			case 4 :return new MapPiece("洞窟", 4); // 洞窟
			case 5 :return new MapPiece("城", 4); // 村
			case 6 :return new MapPiece("山", 2); // 通れる山
			case 7 :return new MapPiece("砂", 0); // 通れない道
			case 8 :return new MapPiece("城", 4); // 神殿
			case 9 :return new MapPiece("城", 4); // 城
		default :return new MapPiece("砂", 0); // 通れない道
		}
	}

	public String map_Name(int mapNumber) {
		switch (mapNumber) {
		case 0: return "フィールドA";
		case 1: return "城A 1階";
		case 2: return "洞窟A 地下1階";
		case 3: return "城A 2階";
		}
		return "どこか";
	}

	private MapPiece castle1(int piece_Number) {
		switch (piece_Number) {
			case 0 :return new MapPiece("砂", 1);
			case 1 :return new MapPiece("草", 2);
			case 2 :return new MapPiece("山", 0);
			case 3 :return new MapPiece("海", 0);
			case 4 :return new MapPiece("洞窟", 4);
			case 5 :return new MapPiece("城", 5); // 階段（入口）
			case 6 :return new MapPiece("山", 2);
			case 7 :return new MapPiece("海", 7); // 宝箱（イベント）
			case 8 :return new MapPiece("草", 8); // 扉（出口）
			case 9 :return new MapPiece("城", 9);
		default :return new MapPiece("砂", 0);
		}
	}

	private MapPiece castle2(int piece_Number) {
		switch (piece_Number) {
			case 0 :return new MapPiece("砂", 1); // 通路
			case 1 :return new MapPiece("草", 3); // バリア
			case 2 :return new MapPiece("山", 0); // 壁
			case 3 :return new MapPiece("海", 0); // 空
			case 4 :return new MapPiece("城", 4); // 階段（上り）
			case 5 :return new MapPiece("洞窟", 4); // 階段（下り）
			case 6 :return new MapPiece("草", 7); // イベント
			case 7 :return new MapPiece("勇者", 0); // 王様
			case 8 :return new MapPiece("海", 4); // 扉（出口）
			case 9 :return new MapPiece("闇", 7); // 穴
		default :return new MapPiece("砂", 0);
		}
	}

	void pushSound() {
		sound(440f,100);
	}

	int[][] shift_Map(int[][] originalMap, int x, int y) {
		int[][] map = new int[originalMap.length][originalMap[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int row = i + y;
				if (row < 0) row += map.length;
				else if (map.length <= row) row -= map.length;
				int column = j + x;
				if (column < 0) column += map[0].length;
				else if (map[0].length <= column) column -= map[0].length;
				map[i][j] = originalMap[row][column];
			}
		}
		return map;
	}

	private int[] center_XY(int[][] baseArray) {
		int[] centerXY = {baseArray[0].length / 2, baseArray.length / 2};
		return centerXY;
	}

	MapPiece[][] map_Data(int map_Number, int[][] map) {
		MapPiece[][] map_Data = new MapPiece[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int piece_Number = map[i][j];
				map_Data[i][j] = mapPiece(map_Number, piece_Number);
			}
		}
		return map_Data;
	}

	public int piece_Number(int map_Number, int x, int y) {
		int[][] originalMap = getOriginalMap(map_Number);
		int[][] shift_Map = shift_Map(originalMap, x, y);
		int[] mapCenter = center_XY(shift_Map);
		int center_X = mapCenter[0];
		int center_Y = mapCenter[1];
		return shift_Map[center_X][center_Y];
	}

	public boolean isBarrier(int map_Number, int target_X, int target_Y) {
		boolean isBarrier = false;
		int[][] map = getOriginalMap(map_Number);
		int[][] shift_Map = shift_Map(map, target_X, target_Y);
		int[] center_XY = center_XY(shift_Map);
		int center_X = center_XY[0];
		int center_Y = center_XY[1];
		MapPiece mapPiece = mapPiece(map_Number, shift_Map[center_X][center_Y]);
		int role = mapPiece.getRole();
		if (role < 1 ) isBarrier = true;
		return isBarrier;
	}

	public boolean isBarrier(MapPiece mapPiece) {
		boolean isBarrier = false;
		int role = mapPiece.getRole();
		if (role < 1 ) isBarrier = true;
		return isBarrier;
	}

	public int[][] cut_Map(int[][] map, int cut_X, int cut_Y) {
		int[][] cut_Map = new int[map.length - cut_Y * 2][map[0].length - cut_X * 2];
		for (int i = 0; i < cut_Map.length; i++) {
			for (int j = 0; j < cut_Map[0].length; j++) {
				cut_Map[i][j] = map [i + cut_Y][j + cut_X];
			}
		}
		return cut_Map;
	}

	private void sound(float frequency, int soundLength) {
		try {
			new Sound(frequency, soundLength);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	void mapChangeSound() {
		sound(100f,150);
		sound(100f,150);
	}

	public void fall_Sound() {
		sound(1200f,600);
		sound(400f,150);
	}

	private String[][] map_Image(MapPiece[][] map_Data) {
	String[][] map_Image = new String[map_Data.length][map_Data[0].length];
	for (int i = 0; i < map_Data.length; i++) {
		for (int j = 0; j < map_Data[i].length; j++) {
			map_Image[i][j] = map_Data[i][j].getImage();
		}
	}
	return map_Image;
	}

	String[][] map_Image(int map_Number, int x, int y) {
		int[][] map = getOriginalMap(map_Number);
		map = shift_Map(map, x, y);
		MapPiece[][] map_Data = map_Data(map_Number,map);
		return map_Image(map_Data);
	}

	private int[][] view_Map(int[][] map, int view＿X, int view＿Y) {
		int cut_X = (map[0].length - view＿X) / 2;
		int cut_Y = (map.length - view＿Y) / 2;
		return cut_Map(map, cut_X, cut_Y);
	}

	private int[][] view_Map(int map_Number, int x, int y) {
		int[][] original_Map = getOriginalMap(map_Number);
		int[][] map = shift_Map(original_Map, x, y);
		return view_Map(map, view_X, view_Y);
	}

	String[][] view_Map_Image(int map_Number, int x, int y) {
		int[][] view_Map = view_Map(map_Number, x, y);
		MapPiece[][] view_Map_Data = map_Data(map_Number, view_Map);
		return map_Image(view_Map_Data);
	}

	public String[] map_Image_Names(int map_Number) {
		// TODO Auto-generated method stub
		switch (map_Number) {
			case 0: return new String[] { "闇", "海", "砂", "山", "城", "草", "洞窟", "勇者" };
			default: return new String[] { "闇", "海", "砂", "山", "城", "草", "洞窟", "勇者" };
		}
	}

  public int[] map_X_Y(int data_Id) {
    // DBに保存されている map_Number, X, Y を配列で返す 
		Data_Info data = data_Info_Repository.getReferenceById(data_Id);
		int map_Number = data.getMap_Number();
		int x = map_Number = data.getX();
		int y = map_Number = data.getY();
		int[] map_X_Y = {map_Number, x, y};
		return map_X_Y;
  }

  public void save(int data_Id, Actor[] party, int map_Number, int x, int y) {
		// 新しいdata_keyを作成
		String data_Key = data_Key();
		// data_Info テーブルを更新
		Data_Info data_Info = new Data_Info(data_Id, data_Key, map_Number, x, y);
		data_Info_Repository.saveAndFlush(data_Info);
		// actor テーブルを更新
		for (Actor member : party) {
			actor_Repository.saveAndFlush(member);
		}
  }



	public void party_Info(int data_Id, Actor[] party) {
		// 引数のdata_Idを持つパーティー情報を削除
		release_Party(data_Id);
		for (int index = 0; index < party.length; index++) {
			Actor member = party[index];
			Integer actor_Id = member.getId();
			// party_info テーブルを更新
			Party_Info party_Info = new Party_Info();
			party_Info.setData_Id(data_Id);
			party_Info.setNo(index + 1);
			party_Info.setActor_Id(actor_Id);
			party_Info_Repository.saveAndFlush(party_Info);
		}
	}

	/**
	 * 引数のdata_Idを持つパーティー情報を削除
	 * 
	 * @param data_Id
	 */
	private void release_Party(int data_Id) {
		List<Party_Info> party_List = party_Info_Repository.list(data_Id);
		for (Party_Info party_Info : party_List) {
			party_Info_Repository.delete(party_Info);
		}
	}

	public void save(Save_Data data) {
		int data_Id = Integer.parseInt(data.data_Id);
		int map_Number = Integer.parseInt(data.map_Number);
		int x = Integer.parseInt(data.x);
		int y = Integer.parseInt(data.y);
		Actor[] party = data.party;
		save(data_Id, party, map_Number, x, y);
	}

  String data_Key() {
		// TODO Auto-generated method stub
		// 新しいデータKeyを作成して返す
		return "1234567890";
	}

	public String center_Image(int data_Id) {
    // DBに保存されている center_Image の名前を返す
		// TODO Auto-generated method stub
		String center_Image = "勇者"; // 仮データ
		return center_Image;
  }

	public Actor[] new_Party() {
		Actor[] party = new Actor[4];
		party[0] = new Actor(1, "戦士", "戦士", 0, 1, 100, 0, 0);
		party[1] = new Actor(2, "勇者", "勇者", 0, 1, 80, 20, 0);
		party[2] = new Actor(3, "僧侶", "僧侶", 0, 1, 60, 30, 0);
		party[3] = new Actor(4, "魔術師", "魔術師", 0, 1, 30, 50, 0);
		return party;
	}

  public Actor[] party(int data_Id) {
		// DBに保存されている party情報 を返す

		List<Party_Info> party_Info = party_Info_Repository.list(data_Id);
		Actor[] party = new Actor[party_Info.size()];
		for (int i = 0; i < party.length; i++) {
			Integer actor_Id = party_Info.get(i).getActor_Id();
			party[i] = actor_Repository.getReferenceById(actor_Id);
			console_Out(party[i]);
		}
		return party;
  }

	public Actor[] to_Party(JSONPObject party_JSON) {
		// TODO Auto-generated method stub
		Actor[] party = new Actor[]{};
		return party;
	}

	public String data_Key(int data_Id) {
		// data_infoテーブルよりdata_Idの現在のdata_Keyを取得して返す
		String data_Key = data_Info_Repository.getReferenceById(data_Id).getData_Key();
		System.out.println("data_Key =  " + data_Key);
		return data_Key;
	}

	public Data_Info data_Info(Integer data_Id) {
		return data_Info_Repository.getReferenceById(data_Id);
	}

	public void console_Out(Save_Data data) {
		System.out.println("Received data_Id: " + data.data_Id);
		System.out.println("Received party:");
		for (Actor member : data.party) {
			System.out.println("  member:");
			console_Out(member);
		}
		System.out.println("Received map_Number: " + data.map_Number);
		System.out.println("Received x: " + data.x);
		System.out.println("Received y: " + data.y);
	}

	public void console_Out(Actor actor) {
			System.out.println("    id: " + actor.getId());
			System.out.println("    name: " + actor.getActor_Name());
			System.out.println("    role: " + actor.getRole());
			System.out.println("    exp: " + actor.getExp());
			System.out.println("    lev: " + actor.getLev());
			System.out.println("    HP: " + actor.getHp());
			System.out.println("    MP: " + actor.getMp());
			System.out.println("    lev: " + actor.getWp());
	}

	public Integer max_HP(Actor actor) {
		int ap = ap(actor.getRole());
		return actor.getLev() * ap * 10;
	}

	public Integer max_MP(Actor actor) {
		int ep = ep(actor.getRole());
		return actor.getLev() * ep * 3;
	}

  private int ep(String role_Name) {
		Role role = role(role_Name);
		return role.getEp();
	}

	private int ap(String role_Name) {
		Role role = role(role_Name);
		return role.getAp();
	}

	private Role role(String role_Name) {
		// TODO Auto-generated method stub
		switch (role_Name) {
			case "戦士": return new Role(1, role_Name, 10, 10, 0);
			case "勇者": return new Role(1, role_Name, 8, 8, 5);
			case "僧侶": return new Role(1, role_Name, 6, 5, 8);
			case "魔術師": return new Role(1, role_Name, 3, 1, 10);
			default: return new Role(1, role_Name, 1, 1, 0);
		}
	}

	private int sp(String role_Name) {
		Role role = role_Repository.role(role_Name);
		if (role == null) return 1;
		return role.getSp();
	}
	
	public void register_Message(ArrayList<String> message_List) {
		// 古いメッセージデータが残っていたら削除
		message_Repository.deleteAll();
		// 新しいメッセージデータを登録
		for (String line : message_List) {
			Message message = new Message();
			message.setText(line);
			message_Repository.save(message);
		}
	}
	
  public String back_Image(String event_name) {
    switch (event_name) {
			case "良い人": return "フィールド";
			default: return "エアー";
		}
  }
	
	/**
	 * データベースから表示するメッセージを取得し、表示用リストにして返す
	 * @return 
	 */
	public List<String> message() {
		// 返却用 List を初期化
		List<String> message = new ArrayList<>();
		// 表示するメッセージを初期化
		String full_Message = "----- コメント -----";
		// メッセージデータが在ればfull_Messageに代入しデータベースから削除
		if (message_Repository.findAll() != null) {
			// データベースから1番目のメッセージデータを取得
			Message message_Data = message_Repository.findAll().get(0);
			full_Message = message_Data.getText();
			// データベースから削除
			message_Repository.delete(message_Data);
		}
		System.out.println(full_Message);
		// 文字数に応じて List にセット
		int limit_Size = word_Count();
		int message_Size = full_Message.length();
			// 制限数以下の場合
			if (message_Size <= limit_Size) {
				// 2個目にメッセージ、1と3個目は"　"をセット
				message.add("　");
				message.add(full_Message);
				message.add("　");
			} else {
				// 制限数を超える場合
				// 制限数毎に分割し、1個目から順番にセット。3個目が無い場合は"　"をセット
				for (;;) {
					if (full_Message.length() == 0) {
						if (message.size() <= 3) {
							message.add("　");
						}
						return message;
					}
					String line ="";
					if ( limit_Size < full_Message.length()) {
						line = full_Message.substring(0,limit_Size);
					} else {
						line = full_Message;
					}
					message.add(line);
					full_Message = full_Message.replace(line, "");
				}
			}
		// Listを返す
		return message;
	}

	private int word_Count() {
		return 70;
	}

  public List<String> good_Person(Save_Data data) {
		String hero_Name = "";
		// 受け取ったデータを処理
		for (Actor member : data.party) {
			// ステータス変更
			member.setHp(max_HP(member));
			member.setMp(max_MP(member));
			// 役割が勇者の場合名前を取得
			if (member.getRole().equals("勇者")) hero_Name = member.getActor_Name();
		}
		// メッセージ作成
		ArrayList<String> message = new ArrayList<>();
		message.add("「 ・・・!!? 」");
		message.add(hero_Name + "達は、良い人に出会った♪");
		message.add("「少し元気をもらった  ↑↑↑」");
		// 使用するメッセージをDBに登録
		register_Message(message);
		// データをコンソールに出力
		console_Out(data);
		// データベースを更新
		save(data);
		// レスポンスを返す
		String data_Key = data_Key(Integer.parseInt(data.data_Id));
		List<String> response_Data = Arrays.asList(data.data_Id, data_Key);
		return response_Data;
  }

	public List<String> Monster(Save_Data data) {
		String hero_Name = "";
		// 受け取ったデータを処理
		for (Actor member : data.party) {
			// ステータス変更
			member.setHp(member.getHp() / 90);
			member.setMp(member.getMp() / 90);
			member.setLev(member.getExp() + 10);
			if (member.getLev() * (10 +member.getLev()) < member.getExp()) {
				member.setLev(member.getLev() + 1);
			}
			// 役割が勇者の場合名前を取得
			if (member.getRole().equals("勇者"))
				hero_Name = member.getActor_Name();
		}
		// メッセージ作成
		ArrayList<String> message = new ArrayList<>();
		message.add("「 ・・・!!? 」");
		message.add(hero_Name + "達は、モンスターに出会った‼");
		message.add(hero_Name + "達は、モンスターを倒した♪");
		message.add(hero_Name + "達は、モンスターを倒した♪");
		message.add(hero_Name + "達は、レベルが上がった‼");
		// 使用するメッセージをDBに登録
		register_Message(message);
		// データをコンソールに出力
		console_Out(data);
		// データベースを更新
		save(data);
		// レスポンスを返す
		String data_Key = data_Key(Integer.parseInt(data.data_Id));
		List<String> response_Data = Arrays.asList(data.data_Id, data_Key);
		return response_Data;
	}

  public int message_Count() {
    List<Message> message_Data = message_Repository.findAll();
		return message_Data.size();
  }

}

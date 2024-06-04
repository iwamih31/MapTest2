/**
 *
 */

window.addEventListener("load", (e) => {

	const req = document.querySelector("#req").textContent;
	const map = document.querySelector("#map");
	const party = document.querySelector("#party");
	const data = document.querySelector("#data");
	const message = document.querySelector("#message");
	const map_data_rows = data.querySelectorAll(".map_data_row");
	const map_Number = Number(data.querySelector("#map_number").textContent);
	const map_X = map_data_rows[0].length;
	const map_Y = map_data_rows.length;
	const button_A = document.querySelector("#A");
	const button_B = document.querySelector("#B");
	const button_C = document.querySelector("#C");
	const button_D = document.querySelector("#D");
	const up = document.querySelector("#up");
	const down = document.querySelector("#down");
	const left = document.querySelector("#left");
	const right = document.querySelector("#right");
	let row_Size = 15;
	let column_Size = 15;
	let mode = "○";
	// let mouse_X = e.clientX;
	// let mouse_Y = e.clientY;
	let x = Number(data.querySelector("#x").textContent);
	let y = Number(data.querySelector("#y").textContent);
	
	// マップの元データ
	const map_Table = [];
	
	const set_map_Table = () => {
		map_data_rows.forEach(map_data_row => {
			const map_Table_Row = [];
			const piece_Numbers = map_data_row.querySelectorAll(".piece_number");
			piece_Numbers.forEach(piece_Number => {
				map_Table_Row.push(Number(piece_Number.textContent));
			});
			map_Table.push(map_Table_Row);
		});
	}
	
	set_map_Table();



	const inRange = (range, num) => {
		const half_Range = range / 2;
	let newNum = num;
	if(half_Range <num) newNum -= range;
	if(num < - half_Range) newNum += range;
	return newNum;
	}

	const map_Up = () => {
		y = inRange(map_Y, y-1);
		up.classList.add("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}
	const map_Down = () => {
		y = inRange(map_Y, y + 1);
		up.classList.remove("click_button");
		down.classList.add("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}
	const map_Left = () => {
		x = inRange(map_X, x - 1);
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.add("click_button");
		right.classList.remove("click_button");
	}
	const map_right = () => {
		x = inRange(map_X, x + 1);
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.add("click_button");
	}

	const map_stop = () => {
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}

	const piece_Info = (image_Name, role_Number) => {
		return { image_Name: image_Name, role: role_Number };
	}

	const field = (piece_Number) => {
		switch (Number(piece_Number)) {
			case 0: return piece_Info("砂", 2); // 魔物出現率高
			case 1: return piece_Info("草", 1); // 通常
			case 2: return piece_Info("山", 0); // 通行不可
			case 3: return piece_Info("海", 0); // 通行不可
			case 4: return piece_Info("洞窟", 4); // イベント（洞窟）
			case 5: return piece_Info("城", 4); // イベント（村）
			case 6: return piece_Info("山", 2); // 魔物出現率高 (通れる山)
			case 7: return piece_Info("砂", 0); // 通行不可(通れない道)
			case 8: return piece_Info("城", 4); // イベント（神殿）
			case 9: return piece_Info("城", 4); // イベント（城）
			default: return piece_Info("闇", 0); // 通行不可(通れない道)
		}
	}

	const castle1 = (piece_Number) => {
		switch(piece_Number) {
			case 0: return piece_Info("砂", 1); // 通常（床）
			case 1: return piece_Info("草", 2); // // 魔物出現率高（草）
			case 2: return piece_Info("山", 0); // 通行不可(壁)
			case 3: return piece_Info("海", 0); // 通行不可(水)
			case 4: return piece_Info("洞窟", 4); // イベント（下り階段）
			case 5: return piece_Info("城", 4); // イベント（上り階段）
			case 6: return piece_Info("山", 2); // 魔物出現率高 (通れる山)
			case 7: return piece_Info("海", 4); // イベント（宝箱）
			case 8: return piece_Info("草", 4); // イベント（見えない出口）
			case 9: return piece_Info("海",  4); // イベント (神の御加護)
			default : return piece_Info("砂", 0); // 通行不可(通れない道)
		}
	}

	const castle2 = (piece_Number) => {
		switch(piece_Number) {
			case 0: return piece_Info("砂",  1); // 通路
			case 1: return piece_Info("草",  3); // ダメージ（バリア）
			case 2: return piece_Info("山",  0); // 壁
			case 3: return piece_Info("海",  0); // 空
			case 4: return piece_Info("城",  4); // 階段（上り）
			case 5: return piece_Info("洞窟",  4); // 階段（下り）
			case 6: return piece_Info("草",  7); // イベント
			case 7: return piece_Info("勇者",  0); // 王様
			case 8: return piece_Info("海",  4); // 扉（出口）
			case 9: return piece_Info("闇",  7); // 穴
			default: return piece_Info("砂",  0);
		}
	}

	const dungeon = (piece_Number) => {
		switch(piece_Number) {
			case 0: return piece_Info("砂", 1);// 通常（床）
			case 1: return piece_Info("草", 2);// // 魔物出現率高（草）
			case 2: return piece_Info("闇", 0);// 通行不可(壁)
			case 3: return piece_Info("海", 0); // 通行不可(泉)
			case 4: return piece_Info("洞窟", 4); // イベント（下り階段）
			case 5: return piece_Info("城", 4); // イベント（上り階段）
			case 6: return piece_Info("山", 5); // イベント（宝箱）
			case 7: return piece_Info("海", 7); // イベント（回復）
			case 8: return piece_Info("海", 6); // イベント（脱出）
			case 9: return piece_Info("草", 5); // イベント（落とし穴）
			default: return piece_Info("闇", 2); // 魔物出現率高 (通れる壁)
		}
	}

	const	map_Piece = (map_Number, piece_Number) => {
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

	const shift_Map = (originalMap, x, y) => {
		const return_Map = new Array(originalMap.length);
		let shift_i;
		let shift_j;
		for (let i = 0; i < originalMap.length; i++) {
			shift_i = (i + y) % originalMap.length;
			if (shift_i < 0) shift_i += originalMap.length;
			return_Map[i] = new Array(originalMap[i].length);
			for (let j = 0; j < originalMap[i].length; j++) {
				shift_j = (j + x) % originalMap[i].length;
				if (shift_j < 0) shift_j += originalMap[i].length;
				return_Map[i][j] = originalMap[shift_i][shift_j];
			}
		}
		return return_Map;
	}

	const to_Map_Images = (current_map, map_Number) => {
		const return_Data = [];
		current_map.forEach(row => {
			const data_Row = [];
			row.forEach(piece_Number => {
				const map_Image = map_Piece(map_Number, piece_Number).image_Name;
				data_Row.push(map_Image);
			});
			return_Data.push(data_Row);
		});
		return return_Data;
	}

	const draw_Map = () => {
		const current_map = shift_Map(map_Table, x, y);
		const map_Images = to_Map_Images(current_map, map_Number);
		// map の子要素を全部消去
		while (map.firstChild) {
			map.removeChild(map.firstChild);
		}
		// 子要素作成用のデータを作成
		// 新しいmap の子要素を作成
		map_Images.forEach(map_Images_Row => {
			const map_row = document.createElement('div');
			map_row.className = 'map_row';
			map_Images_Row.forEach(map_Image_Name => {
				const map_Image = data.querySelector("." + map_Image_Name);
				const clone_Map_Image = map_Image.cloneNode();
				map_row.appendChild(clone_Map_Image);
			});
			map.appendChild(map_row);
		});
		map_View_Range(row_Size, column_Size);
	};

	// // パーティーステータス部分自体を全部書き換える
	// const draw_Party = () => {
	// 	let label;
	// 	let value
	// 	// map の子要素を全部消去
	// 	while (party.firstChild) {
	// 		party.removeChild(party.firstChild);
	// 	}
	// 	// 子要素作成用のデータを取得
	// 	const party_data = data.querySelectorAll(".member_data");
	// 	// 新しいmap の子要素を作成
	// 	party_data.forEach(member_data => {
	// 		// <div class="member">
	// 		const member = document.createElement('div');
	// 		member.className = 'member';
	// 		// 名前
	// 		const member_name = document.createElement('div');
	// 		member_name.className = 'center';
	// 		member_name.textContent = member_data.querySelector(".member_name").textContent;
	// 		// Lev.
	// 		const member_lev = document.createElement('div');
	// 		member_lev.className = 'flex_space_between';
	// 		label = document.createElement('span');
	// 		label.textContent = 'Lev:';
	// 		value = document.createElement('span');
	// 		value.textContent = member_data.querySelector(".member_lev").textContent;
	// 		member_lev.appendChild(label);
	// 		member_lev.appendChild(value);
	// 		// HP
	// 		const member_hp = document.createElement('div');
	// 		member_hp.className = 'flex_space_between';
	// 		label = document.createElement('span');
	// 		label.textContent = 'Lev:';
	// 		value = document.createElement('span');
	// 		value.textContent = member_data.querySelector(".member_hp").textContent;
	// 		member_hp.appendChild(label);
	// 		member_hp.appendChild(value);
	// 		// MP
	// 		const member_mp = document.createElement('div');
	// 		member_mp.className = 'flex_space_between';
	// 		label = document.createElement('span');
	// 		label.textContent = 'Lev:';
	// 		value = document.createElement('span');
	// 		value.textContent = member_data.querySelector(".member_mp").textContent;
	// 		member_mp.appendChild(label);
	// 		member_mp.appendChild(value);
	// 		member.appendChild(member_name);
	// 		member.appendChild(member_lev);
	// 		member.appendChild(member_hp);
	// 		member.appendChild(member_mp);
	// 		party.appendChild(member);
	// 	});
	// };

	// パーティーステータスのデータ部分のみ書き換える
	const draw_Party = () => {
		// パーティーのデータを取得
		const party_data = data.querySelectorAll('.member_data');
		// 新しいデータで書き換え
		for (let index = 0; index < party_data.length; index++) {
			const member = party_data[index];
			const member_name = member.querySelector('.member_name').textContent;
			const member_lev = member.querySelector('.member_lev').textContent;
			const member_hp = member.querySelector('.member_hp').textContent;
			const member_mp = member.querySelector('.member_mp').textContent;
			party.querySelector('#member_name' + index).textContent = member_name;
			party.querySelector('#member_lev' + index).textContent = member_lev;
			party.querySelector('#member_hp' + index).textContent = member_hp;
			party.querySelector('#member_mp' + index).textContent = member_mp;
		}
	};

	const languor = () => {
		// パーティーのデータを取得
		const party_data = data.querySelectorAll('.member_data');
		// 新しいデータで書き換え
		party_data.forEach(member => {
			const member_hp = member.querySelector('.member_hp');
			const member_mp = member.querySelector('.member_mp');
			member_hp.textContent--;
			member_mp.textContent++;
		});
		draw_Party();
	}

	const comment = (message_List) => {
		// message の子要素を全部消去
		while (message.firstChild) {
			message.removeChild(message.firstChild);
		}
		// 新しい message の子要素を作成
		message_List.forEach(one_Message => {
			const message_row = document.createElement('div');
			message_row.textContent = one_Message;
			message.appendChild(message_row);
		});
	}

	const center_XY = (baseArray) => {
		const center_X = Math.floor(baseArray[0].length / 2);
		const center_Y = Math.floor(baseArray.length / 2);
		return { "X": center_X, "Y":center_Y};
	}

	const after_XY = (mode, x, y) => {
		let after_X = x;
		let after_Y = y;
		if (mode === "上") after_Y = y - 1;
		if (mode === "下") after_Y = y + 1;
		if (mode === "左") after_X = x - 1;
		if (mode === "右") after_X = x + 1;
		return {"X":after_X, "Y":after_Y};
	}

	const barrier_Check = (role) => {
		if (role < 1) move("○");
		console.log(role);
		console.log(mode);
		// 衝突音を鳴らす
		// fall_Sound();
		return mode;
	}

	const random = (min, max) => {
		return Math.floor(Math.random() * (max + 1 - min)) + min;
	}

	// const load_Session_Data = (key) => {
	// 	// セッションからデータを取得
	// 	let session_Data = null;
	// 	if (sessionStorage.getItem(key) !== null) {
	// 		session_Data = sessionStorage.getItem(key);
	// 		sessionStorage.removeItem(key);
	// 	}
	// 	return session_Data;
	// }


	const event = (path) => {
		const url = ".." + req + "/" + path;
		const send_Data = {
			data_Id: document.querySelector("#data_id").textContent,
			party: party_Data(),
			map_Number: map_Number,
			x: x,
			y: y,
		};
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(send_Data),
		})
			.then((response) => response.json())
			.then((data) => {
				console.log('Success:', data);
				// alert('Success:'+ data);
				// 結果データの要素[1]（data_Key）を Session に保存
				sessionStorage.setItem('data_Key', data[1]);
			})
			.catch((error) => {
				console.error('Error:', error);
			});
	}
	
	const transition = (key) => {
		let path = "";
		switch (key) {
			case "次マップ":
				path = "Map2";
				break;
			case "良い人":
				path = "Good_Person";
				event(path);
				break;
			case "モンスター":
				path = "";
				break;
			case "アイテム":
				path = "";
				break;
			case "情報":
				path = "";
				break;
			default:
				comment(['　', key + '???', '　']);
				return;
		}
		comment(['　', key + '画面に遷移します', '　']);
		// 画面内の data_Id を取得
		const data_Id = document.querySelector("#data_id").textContent;
		// セッションに保存された data_Key を取得
		const data_Key = sessionStorage.getItem('data_Key');
		// 画面遷移（GET）
		window.location.href = ".." + req + "/" + path + "?data_Id=" + data_Id + "&data_Key=" + data_Key;
	}

	const piece_Position = (piece_Name) => {
		switch (piece_Name) {
			case "フィールドA 城A": return [ 0, 0, 0 ];
			case "城A 1階 正面出口": return [ 1, 1, 8 ];
			case "城A 1階 階段A": return [ 1, 1, -1 ];
			case "城A 1階 洞窟A": return [ 1, -3, 6 ];
			case "城A 1階 洞窟B": return [ 1, 4, 2 ];
			case "城A 2階 階段A": return [ 3, 1, -1 ];
			case "洞窟A 地下1階 入口": return [ 2, -3, 6 ];
			case "洞窟B 地下1階 入口": return [ 2, 4, 2 ];
			default: return [ 0, 0, 0 ];
		}
	}
	
	const fit = (map_Number_X_Y, piece_Name) => {
		const check_Position = piece_Position(piece_Name);
		if (map_Number_X_Y[0] === check_Position[0] &&
				map_Number_X_Y[1] === check_Position[1] &&
				map_Number_X_Y[2] === check_Position[2]) {
			return true;
		}
		return false;
	}
		
	const next_Map_Name = (map_Number, x, y) => {
		const now_Piece = [ map_Number, x, y ];
		if (fit(now_Piece, "フィールドA 城A")) return "城A 1階 正面出口";
		if (fit(now_Piece, "城A 1階 正面出口")) return "フィールドA 城A";
		if (fit(now_Piece, "城A 1階 階段A")) return "城A 2階 階段A";
		if (fit(now_Piece, "城A 1階 洞窟A")) return "洞窟A 地下1階 入口";
		if (fit(now_Piece, "城A 1階 洞窟B")) return "洞窟B 地下1階 入口";
		if (fit(now_Piece, "城A 2階 階段A")) return "城A 1階 階段A";
		if (fit(now_Piece, "洞窟A 地下1階 入口")) return "城A 1階 洞窟A";
		return "フィールドA 城A";
	}

	const party_Data = () => {
		// パーティーのデータを取得
		const party_Data = data.querySelectorAll('.member_data');
		// Objectにして配列にセット
		let member_Array = new Array;
		party_Data.forEach(member => {
			const id = member.querySelector('.member_id').textContent;
			const data_Id = document.querySelector('#data_id').textContent;
			const actor_name = member.querySelector('.member_name').textContent;
			const role = member.querySelector('.member_role').textContent;
			const exp = member.querySelector('.member_exp').textContent;
			const lev = member.querySelector('.member_lev').textContent;
			const hp = member.querySelector('.member_hp').textContent;
			const mp = member.querySelector('.member_mp').textContent;
			const wp = member.querySelector('.member_wp').textContent;
			member_Array.push({ id: id, data_Id: data_Id, actor_name: actor_name, role: role, exp: exp, lev: lev, hp: hp, mp: mp, wp: wp });
		});
		// alert(member_Array);
		return member_Array;
	}

	const save = (map_Number, x_Number, y_Number) => {
		const url = ".." + req + "/Save";
		const save_Data = {
			data_Id: document.querySelector("#data_id").textContent,
			party: party_Data(),
			map_Number: map_Number,
			x: x_Number,
			y: y_Number,
		};
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(save_Data),
		})
			.then((response) => response.json())
			.then((data) => {
				console.log('Success:', data);
				// alert('Success:'+ data);
				// 結果データの要素[1]（data_Key）を Session に保存
				sessionStorage.setItem('data_Key', data[1]);
			})
			.catch((error) => {
				console.error('Error:', error);
			});
	}

	// const transition_Parameter = (url, data) => {
	// 	fetch(url, {
	// 		method: 'POST',
	// 		headers: {
	// 			'Content-Type': 'application/json',
	// 		},
	// 		body: JSON.stringify(data),
	// 	})
	// 	.then((response) => response.json())
	// 	.then((data) => {
	// 		console.log('Success:', data);
	// 		window.location.href = url; // ここで画面遷移を行う
	// 	})
	// 	.catch((error) => {
	// 		console.error('Error:', error);
	// 	});
	// }

		
	const map_Change = (piece_Name) => {
		const after_Map_X_Y = piece_Position(piece_Name);
		const after_Map_Number = after_Map_X_Y[0];
		const after_X = after_Map_X_Y[1];
		const after_Y = after_Map_X_Y[2];
		//サーバーのデータベースに保存
		save(after_Map_Number, after_X, after_Y);
		comment(["　", "Map_Number = " + after_Map_Number + ", X = " + after_X + ", Y = " + after_Y  + " に移動します", "　"]);
		// 画面遷移
		setTimeout(() => {
			transition("次マップ");
		}, 1500);
		
	}

	const piece_Event = (map_Number, x, y) => {
		const piece_Name = next_Map_Name(map_Number, x, y);
		if (piece_Name === "") transition(piece_Name);
		map_Change(piece_Name);
	};

	const comment_Clear = () => {
		comment(["　", "　", "　"]);
	}
	
	const walk_Event = (role) => {
		comment_Clear();
		let event_Rate = 0;
		if (role === 1) event_Rate = 30;
		if (role === 2) event_Rate = 60;
		if (role === 4) { 
			move("○"); 
			setTimeout(() => {
				piece_Event(map_Number, x, y);
			}, 1500);
			return
		}
		if (random(1,100) < event_Rate) {
			move("○");
			let event_Name = "";
			let event_Number = random(1, 100);
			if (event_Number < (100 - event_Rate) / 10) event_Name = "良い人";
			else if (event_Number < 90) event_Name = "モンスター";
			else if (event_Number < 95) event_Name = "アイテム";
			else event_Name = "情報";
			setTimeout(() => {
				transition(event_Name);
			}, 1500);
		}
	}
	
	const action = (mode, x, y) => {
		// 移動先の Role を取得
		const destination = after_XY(mode, x, y)
		const after_MAP = shift_Map(map_Table, destination.X, destination.Y);
		const center = center_XY(after_MAP);
		const after_Role = map_Piece(map_Number, after_MAP[center.Y][center.X]).role;
		// 進行できるかチェック
		const result = barrier_Check(after_Role);
		if (result === "○") {
			map_stop();
		} else {
			// スタミナ消費とMP回復
			languor();
			// ポジション移動
			if (result === "上") map_Up();
			if (result === "下") map_Down();
			if (result === "左") map_Left();
			if (result === "右") map_right();
			// 移動後のマップを描画
			draw_Map();
			// 移動先のイベント決定
			walk_Event(after_Role);
		}
	};

	const map_View_Range = (row_Size, column_Size) => {
		map_rows = document.querySelectorAll(".map_row");
		let offset = (map_rows.length - row_Size) / 2;
		for (let i = 0; i < map_rows.length; i++) {
			let map_row = map_rows[i];
			if (i < offset || offset + row_Size < i) {
				map_row.classList.add("display_none");
			} else {
				map_row.classList.remove("display_none");
				const pieces = map_row.querySelectorAll(".map_piece");
				offset = (pieces.length - column_Size) / 2;
				for (let j = 0; j < pieces.length; j++) {
					let piece = pieces[j];
					if (j < offset || offset + column_Size < j) {
						piece.classList.add("display_none");
					} else {
						piece.classList.remove("display_none");
					}
				}
			}
		}
	};

	// const position = (event_Data) => {
	// 	return `X = ${event_Data.clientX} Y = ${event_Data.clientY}`;
	// }

	const search = () => {
		draw_Map();
		languor();
		// イベント決定
		const current_Map = shift_Map(map_Table, x, y);
		const center = center_XY(current_Map);
		const role = map_Piece(map_Number, current_Map[center.Y][center.X]).role;
			walk_Event(role);
	}

	const move = (destination) => {
		if (destination !== mode) {
			mode = destination;
			console.log("移動先 = " + mode);
			if (mode === "○") {
				map_stop();
			} else {
				walk();
			}
		}
	}

	const button = (button_Name, event_Data) => {
		switch (button_Name) {
			case "A":
			case "B":
			case "C":
			case "D":
				alert(button_Name + "ボタンが押されました");
				// alert_Position(event_Data);
				break;
			case "○":
				search();
				break;
			case "上":
			case "下":
			case "左":
			case "右":
				move(button_Name);
				break;
			default:
				break;
		}
		console.log(button_Name + "ボタンが押されました");
		// console.log(position(event_Data));
	}

	button_A.addEventListener("click", (e) => { button("A", e); });
	button_B.addEventListener("click", (e) => { button("B", e); });
	button_C.addEventListener("click", (e) => { button("C", e); });
	button_D.addEventListener("click", (e) => { button("D", e); });
	center.addEventListener("click", (e) => { button("○", e); });
	up.addEventListener("click", (e) => { button("上", e); });
	down.addEventListener("click", (e) => { button("下", e); });
	left.addEventListener("click", (e) => { button("左", e); });
	right.addEventListener("click", (e) => { button("右", e); });
	up.addEventListener("mouseover", (e) => { button("上", e); });
	down.addEventListener("mouseover", (e) => { button("下", e); });
	left.addEventListener("mouseover", (e) => { button("左", e); });
	right.addEventListener("mouseover", (e) => { button("右", e); });
	up.addEventListener("mouseout", () => { move("○"); });
	down.addEventListener("mouseout", () => { move("○"); });
	left.addEventListener("mouseout", () => { move("○"); });
	right.addEventListener("mouseout", () => { move("○"); });
	
	const walk = () => {
		const current_Mode = mode;
		// mouse_X = e.clientX;
		// mouse_Y = e.clientY;
		const loop = setInterval(() => {
			if (current_Mode !== mode) {
				clearInterval(loop);
			}
			action(mode, x, y);
		}, 2000);
	}

	// // マウス移動による操作
	// map.addEventListener("pointermove", (e) => {
	// 	if (mode === "移動") {
	// 		console.log(`Mouse position: X = ${e.clientX}, Y = ${e.clientY}`);
	// 		if (mouse_Y > e.clientY) move("上");
	// 		if (mouse_Y < e.clientY) move("下");
	// 		if (mouse_X > e.clientX) move("左");
	// 		if (mouse_X < e.clientX) move("右");
	// 		if (mouse_X == e.clientX) move("○");
	// 		mouse_X = e.clientX;
	// 		mouse_Y = e.clientY;
	// 		// repaint(); // 画面を再描画して待つ
	// 	}
	// });

	window.addEventListener("keydown", (e) => {
		// alert(e.key + " キーが押されました");
		console.log(e.key + " キーが押されました");
		switch (e.key){
			case "ArrowUp"   : move("上"); break;
			case "ArrowDown" : move("下"); break;
			case "ArrowLeft" : move("左"); break;
			case "ArrowRight": move("右"); break;
		};
	});

	// load_Session_Data('data_Key');
	draw_Map();
	draw_Party();
	
});



const n3 = document.getElementsByClassName('num3');
for (var i = 0; n3.length; i++){
	let p = n3[i].textContent.replace('\xA5', '');
	if(isFinite(p)){
		n3[i].innerHTML = Number(p).toLocaleString('ja-JP', {"style":"currency", "currency":"JPY"});
	}
};

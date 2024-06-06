/**
 *
 */

window.addEventListener("load", (e) => {

	const req = document.querySelector("#req").textContent;
	// const map = document.querySelector("#map");
	const party = document.querySelector("#party");
	const data = document.querySelector("#data");
	const message = document.querySelector("#message");
	// const map_data_rows = data.querySelectorAll(".map_data_row");
	// const map_Number = Number(data.querySelector("#map_number").textContent);
	// const map_X = map_data_rows[0].length;
	// const map_Y = map_data_rows.length;
	const button_A = document.querySelector("#A");
	const button_B = document.querySelector("#B");
	const button_C = document.querySelector("#C");
	const button_D = document.querySelector("#D");
	const up = document.querySelector("#up");
	const down = document.querySelector("#down");
	const left = document.querySelector("#left");
	const right = document.querySelector("#right");
	// let row_Size = 15;
	// let column_Size = 15;
	let mode = "○";
	// let mouse_X = e.clientX;
	// let mouse_Y = e.clientY;
	// let x = Number(data.querySelector("#x").textContent);
	// let y = Number(data.querySelector("#y").textContent);
	
	// // マップの元データ
	// const map_Table = [];
	
	// const set_map_Table = () => {
	// 	map_data_rows.forEach(map_data_row => {
	// 		const map_Table_Row = [];
	// 		const piece_Numbers = map_data_row.querySelectorAll(".piece_number");
	// 		piece_Numbers.forEach(piece_Number => {
	// 			map_Table_Row.push(Number(piece_Number.textContent));
	// 		});
	// 		map_Table.push(map_Table_Row);
	// 	});
	// }
	
	// set_map_Table();



	const inRange = (range, num) => {
		const half_Range = range / 2;
	let newNum = num;
	if(half_Range <num) newNum -= range;
	if(num < - half_Range) newNum += range;cursor
	return newNum;
	}

	const cursor_Up = () => {
		y = inRange(map_Y, y-1);
		up.classList.add("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}
	const cursor_Down = () => {
		y = inRange(map_Y, y + 1);
		up.classList.remove("click_button");
		down.classList.add("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}
	const cursor_Left = () => {
		x = inRange(map_X, x - 1);
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.add("click_button");
		right.classList.remove("click_button");
	}
	const cursor_right = () => {
		x = inRange(map_X, x + 1);
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.add("click_button");
	}

	const cursor_stop = () => {
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}


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

	const transition = (key) => {
		let path = "";
		switch (key) {
			case "次マップ":
				path = "Map2";
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
		return member_Array;
	}

	// const save = (map_Number, x_Number, y_Number) => {
	// 	const url = ".." + req + "/Save";
	// 	const save_Data = {
	// 		data_Id: document.querySelector("#data_id").textContent,
	// 		party: party_Data(),
	// 		map_Number: map_Number,
	// 		x: x_Number,
	// 		y: y_Number,
	// 	};
	// 	fetch(url, {
	// 		method: 'POST',
	// 		headers: {
	// 			'Content-Type': 'application/json',
	// 		},
	// 		body: JSON.stringify(save_Data),
	// 	})
	// 		.then((response) => response.json())
	// 		.then((data) => {
	// 			console.log('Success:', data);
	// 			// alert('Success:'+ data);
	// 			// 結果データの要素[1]（data_Key）を Session に保存
	// 			sessionStorage.setItem('data_Key', data[1]);
	// 		})
	// 		.catch((error) => {
	// 			console.error('Error:', error);
	// 		});
	// }

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

	const comment_Clear = () => {
		comment(["　", "　", "　"]);
	}

	const get_Message = (count) => {
		const url = ".." + req + "/Message";
		const send_Data = [
			document.querySelector("#data_id").textContent,
			count
		];
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
				// 結果データを コメント欄に表示
				comment(data);
			})
			.catch((error) => {
				console.error('Error:', error);
			});
	}

	const action = (mode) => {
		// カーソル移動
		if (mode === "上") cursor_Up();
		if (mode === "下") cursor_Down();
		if (mode === "左") cursor_Left();
		if (mode === "右") cursor_right();
	};

	const mode_Change = (destination) => {
		if (destination !== mode) {
			mode = destination;
			console.log("移動先 = " + mode);
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
				const count = document.querySelector("#count").textContent;
				if (count){
					get_Message(count);
				} else {
					transition("次マップ");
				}
				break;
			case "上":
			case "下":
			case "左":
			case "右":
				mode_Change(button_Name);
				action(mode);
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
	up.addEventListener("mouseout", () => { mode_Change("○"); });
	down.addEventListener("mouseout", () => { mode_Change("○"); });
	left.addEventListener("mouseout", () => { mode_Change("○"); });
	right.addEventListener("mouseout", () => { mode_Change("○"); });

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

	draw_Party();
	
});



const n3 = document.getElementsByClassName('num3');
for (var i = 0; n3.length; i++){
	let p = n3[i].textContent.replace('\xA5', '');
	if(isFinite(p)){
		n3[i].innerHTML = Number(p).toLocaleString('ja-JP', {"style":"currency", "currency":"JPY"});
	}
};

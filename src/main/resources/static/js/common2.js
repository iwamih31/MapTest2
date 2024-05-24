/**
 *
 */


window.addEventListener("load", (e) => {

	const map = document.querySelector("#map");
	const data = document.querySelector("#data");
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
		up.classList.add("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
		y = inRange(map_Y, y-1);
	}
	const map_Down = () => {
		up.classList.remove("click_button");
		down.classList.add("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
		y = inRange(map_Y, y + 1);
	}
	const map_Left = () => {
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.add("click_button");
		right.classList.remove("click_button");
		x = inRange(map_X, x - 1);
	}
	const map_right = () => {
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.add("click_button");
		x = inRange(map_X, x + 1);
	}

	const map_stop = () => {
		up.classList.remove("click_button");
		down.classList.remove("click_button");
		left.classList.remove("click_button");
		right.classList.remove("click_button");
	}

	const field = (piece_Number) => {
	switch (Number(piece_Number)) {
		case 0: return {image_Name:"砂", roll:1}; // 道
		case 1: return {image_Name:"草", roll:2}; // 魔物出現率アップ
		case 2: return {image_Name:"山", roll:0}; // 障害物
		case 3: return {image_Name:"海", roll:0}; // 障害物
		case 4: return {image_Name:"洞窟", roll:4}; // 洞窟
		case 5: return {image_Name:"城", roll:4}; // 村
		case 6: return {image_Name:"山", roll:2}; // 通れる山
		case 7: return {image_Name:"砂", roll:0}; // 通れない道
		case 8: return {image_Name:"城", roll:4}; // 神殿
		case 9: return {image_Name:"城", roll:4}; // 城
		default: return {image_Name:"闇", roll:0}; // 通れない道
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
		// map の子要素を全部消去
		while (map.firstChild) {
			map.removeChild(map.firstChild);
		}
		// 子要素作成用のデータを作成
		const current_map = shift_Map(map_Table, x, y);
		const map_Images = to_Map_Images(current_map, map_Number);
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
	
	const action = (mode) => {
		map_rows = document.querySelectorAll(".map_row");
		if (mode === "上") map_Up();
		if (mode === "下") map_Down();
		if (mode === "左") map_Left();
		if (mode === "右") map_right();
		if (mode === "○") map_stop();
		draw_Map();
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

	const stop = (event_Data) => {
		alert("stop イベント発動");
		mouse_X = event_Data.clientX;
		mouse_Y = event_Data.clientY;
		alert(`X = ${mouse_X} Y = ${mouse_Y}`);
		alert("総タイル数 = " + tiles.length);
		alert("行の列数 = " + map_rows.length);
	};

	const position = (event_Data) => {
		return `X = ${event_Data.clientX} Y = ${event_Data.clientY}`;
	}

	const button = (button_Name, event_Data) => {
		switch (button_Name) {
			case "A":
			case "B":
			case "C":
				case "D":
				case "○":
				alert(button_Name + "ボタンが押されました");
				// alert_Position(event_Data);
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

	const move = (destination) => {
		if (destination !== mode) {
			mode = destination;
			console.log("移動先 = " + destination);
			walk();
		}
	}

	// center.addEventListener("click", (e) => {
	// 	if (clicked === false) {
	// 		clicked = true;
	// 		mouse_X = e.clientX;
	// 		mouse_Y = e.clientY;
	// 		alert(`クリック時の座標 X = ${mouse_X} Y = ${mouse_Y}`);
	// 		stop(e);
	// 		move("移動");
	// 	} else {
	// 		clicked = false;
	// 	}
	// 	// walk();
	// });

	button_A.addEventListener("click", (e) => { button("A", e); });
	button_B.addEventListener("click", (e) => { button("B", e); });
	button_C.addEventListener("click", (e) => { button("C", e); });
	button_D.addEventListener("click", (e) => { button("D", e); });
	center.addEventListener("click", (e) => { button("○", e); });
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
		setTimeout(() => {
			const loop = setInterval(() => {
				action(mode);
				if (current_Mode !== mode) {
					clearInterval(loop);
				}
			}, 1500);
		}, 1500);
	}

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

	draw_Map();

	// if (mode === ""){
	// 	walk();
	// }
	// map_View_Range(row_Size, column_Size);
	
});



const n3 = document.getElementsByClassName('num3');
for (var i = 0; n3.length; i++){
	let p = n3[i].textContent.replace('\xA5', '');
	if(isFinite(p)){
		n3[i].innerHTML = Number(p).toLocaleString('ja-JP', {"style":"currency", "currency":"JPY"});
	}
};

function productCheck(){
	if(document.frm.name.value.length==0){
		alert("상품명을 써주세요.");
		frm.name.select();
		return false;
	}
	
	if(document.frm.price.value.length==0){
		alert("가격을 써주세요.");
		frm.price.select();
		return false;
	}
	
	if(isNaN(document.frm.price.value)){
		alert("가격에는 숫자만 입력해야합니다.");
		frm.price.select();
		return false;
	}
	
	var badword = ['바보','멍청이','병신'];
	var ban_badword = [];
	console.log(document.frm.name.value);
	for(var i = 0;i < badword.length;i++){ 
		if(document.frm.name.value.indexOf(badword[i]) > -1 || document.frm.description.value.indexOf(badword[i]) > -1) {
			if(ban_badword.indexOf('"' + badword[i] + '"') < 0){
				ban_badword.push('"' + badword[i] + '"');
				console.log(ban_badword);
			}
		}
	
	}
	if(ban_badword.length > 0){
		alert("입력하신 제목과 내용에 금칙어인 " + ban_badword.join(", ") + "를 포함하고 있습니다.");
		return false;
	}

	
	return true;
}
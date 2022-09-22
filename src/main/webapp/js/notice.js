function noticeCheck() {
	if (document.frm.name.value.length == 0) {
		alert("작성자를 입력하세요.");
		documet.frm.name.focus();
		return false;
	}
	if (document.frm.pass.value.length == 0) {
		alert("비밀번호를 입력하세요.");
		documet.frm.pass.focus();
		return false;
	}
	if (document.frm.title.value.length == 0) {
		alert("제목을 입력하세요.");
		documet.frm.title.focus();
		return false;
	}
	return true;
}

//url:check_pass.do
//name:수정('update'),삭제('delete') -> 팝업창의 이름

function open_win(url, name){
	window.open(url, name, "width=500, height=230");
}

function passCheck(){
	if(document.frm.pass.value.length==0){
		alert("비밀번호를 입력하세요.")
		documet.frm.pass.focus();
		return false;
	}
	return true;
}
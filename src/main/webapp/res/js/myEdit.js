$(document).ready(function(){
	var img_option = {
					  	url : "/EditImg", type: "POST", 
					  	enctype: 'multipart/form-data', 
					  	processData: false, contentType : false,
					  	cache: false
					 };
	var data_option = {type: "POST", contentType : "application/json; charset=UTF-8"};
	var input = document.createElement("INPUT");
	input.setAttribute("type", "file");
	input.onchange = function(input) {
		var reader = new FileReader();
	    reader.onload = function (e) {
	    	$("#preview").attr("src", e.target.result);
	    }
	    reader.readAsDataURL(input.target.files[0]);
	};
	$("#preview").click(function imgEvent(){
		input.click();
	});
	$("#saveBtn").click(function(){
		if(input.files.length > 0) {
			var formData = new FormData();
			formData.append('file', input.files[0]);
			img_option.data = formData;
			$.ajax(img_option).done(function(d) {
				if(d.result > 0) {
					$("#myImage").attr("src", $("#preview").attr("src"));
				} else {
					alert("이미지 수정 실패!");
				}
			});
		}
		
	});
	function funView() {
		data_option.url = "/page/myEdit";
		$.ajax(data_option).done(function(d){
			$("#form_body").html(d);
			funEvent();
		});
	}
	function funEvent() {
		var intersets = {};
		$(".intersetsItem").click(function(){
			var index = $(".intersetsItem").index(this);
			var num = $(".intersetsItem").eq(index).attr("data-interest");
			var state = $(".intersetsItem").eq(index).attr("data-state");
			if(state == 0) {
				$(".intersetsItem").eq(index).attr("data-state", 1);
				$(".intersetsItem").eq(index).find("div").addClass("txt-active");
				intersets[num] = 1;
			} else {
				$(".intersetsItem").eq(index).attr("data-state", 0);
				$(".intersetsItem").eq(index).find("div").removeClass("txt-active");
				if(intersets[num]) {
					delete intersets[num];
				} else {
					intersets[num] = 0;
				}
			}
		});
		$("form").submit(function(e){
			e.preventDefault();
			var params = {
					"name":	 $("#name").val(),
					"email": $("#email").val(),
					"phone": $("#phone").val(),
					"pwd":	 $("#pwd").val(),
					"intersets": JSON.stringify(intersets),
			};
			data_option.data = JSON.stringify(params);
			data_option.url = "/myEdit";
			$.ajax(data_option).done(function(d){
				if(d.user.result1 > 0) {
					var msg = `관심 목록  : ${d.status} / ${Object.keys(intersets).length}`;
					alert(msg);
				} else {
					alert("수정 중 오류 발생");
				}
			});
		});
	}
	funView();	
});
$(document).ready(function(){
	var option = {url : "/message", type: "POST", contentType : "application/json; charset=UTF-8"};
	$("form").submit(function(e) {
		e.preventDefault();
		var params = {
				"target_num":	$("#targetUser").val(),
				"message":		$("#comment").val()		
		};
		option.data = JSON.stringify(params);
		$.ajax(option).done(function(d) {
			if(d.result > 0) {
				location.href = $(".nav-list li").eq(1).find("a").attr("href");
			} else {
				alert("작성 실패!");
			}
		});
	});
});
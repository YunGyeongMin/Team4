$(document).ready(function(){
	var data_option = {type: "POST", contentType : "application/json; charset=UTF-8"};
	function funView() {
		var targetUser = $(".nav-list").find(".active").find("a").attr("href");
		var params = {
				"num":	 targetUser.substring(targetUser.lastIndexOf("/") + 1, targetUser.length)
		};
		data_option.data = JSON.stringify(params);
		data_option.url = "/page/profile";
		$.ajax(data_option).done(function(d){
			$("#form_body").html(d);
		});
	}
	funView();	
});
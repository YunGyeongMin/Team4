$(document).ready(function(){
	var option = {url : "/page/myList/", type: "POST", contentType : "application/json; charset=UTF-8"};
	function funSelect() {
		var targetUser = $(".nav-list").find(".active").find("a").attr("href");
		option.url = "/page" + targetUser;
		$.ajax(option).done(function(d) {
			$("#message_list").html(d);
		});
	}
	funSelect();
});
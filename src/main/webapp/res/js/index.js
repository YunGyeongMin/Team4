$(document).ready(function(){
	var option = {url : "/page/index", type: "POST", contentType : "application/json; charset=UTF-8"};
	function funUserSelect() {
		$.ajax(option).done(function(d) {
			$("#user_list").html(d);
			funTargetUser();
		});
	}
	function funTargetUser() {
		$(".user").click(function(){
			var index = $(".user").index(this);
			var targetUser = $(".user").eq(index).attr("data-targetUser");
			console.log(index, targetUser);
			location.href = "/myList/" + targetUser;
		});
	}
	funUserSelect();
});
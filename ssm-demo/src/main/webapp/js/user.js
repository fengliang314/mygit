function login() {
	var auser=$("#username").val();
	var apassword=$("#password").val();
	$.ajax({
		type: "POST",
		url: "../system/user/login",
		data: "userName=" + auser + "&password=" + apassword,
		dataType: "json",
		success: function(data) {
			console.log(data);
			window.location.href="success.html";
		}
	})
}
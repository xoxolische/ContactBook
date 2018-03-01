function createUser() {
	if ($("#registrationForm").valid()) {
		var item = {
			"email" : $("#userEmail").val(),
			"nickName" : $("#nickName").val(),
			"password" : $("#userPassword").val()
		}
		$.ajax({
			url : $('#path').val() + '/api/user/register',
			type : 'POST',
			data : JSON.stringify(item),
			contentType : "application/json",
			dataType : 'json'
		}).done(function(data) {
			window.location.href = $('#path').val() + "/login";
		}).fail(function(data) {
			console.log(data.responseText);
		});
	}
}
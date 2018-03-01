$(function() {
	$("#registrationForm").validate({
		rules : {
			rName : {
				required : true,
				minlength : 3,
				maxlength : 20
			},
			rPass : {
				required : true,
				minlength : 8,
				maxlength : 255
			},
			rPassConfirm : {
				required : true,
				equalTo : "#userPassword"
			},
			rEmail : {
				required : true,
				email : true
			}			
		},
		messages : {
			rName : {
				required : "Please enter your nickName!",
				maxlength : "Name is too big!",
				minlength : "Name is too short!"
			},
			rPass : {
				required : "Please enter your password!",
				maxlength : "Password is too big!",
				minlength : "Password is too short!"
			},
			rPassConfirm : {
				required : "Confirm your password!",
				equalTo : "Passwords are not equals!"
			},
			email : {
				required : "Please enter your email address",
				email : "Email has wrong format!"
			}
		}
	});
});
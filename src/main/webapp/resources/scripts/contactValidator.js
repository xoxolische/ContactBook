$(function() {
	$("#contactForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 120
			},
			surname : {
				required : true,
				minlength : 2,
				maxlength : 120
			},
			email : {
				required : true,
				email : true
			},
			phone : {
				required : true,
				digits : true
			},
			birth : {
				required : true
			}
		},
		messages : {
			name : {
				required : "Please enter contact name!",
				maxlength : "Name is too big!",
				minlength : "Name is too short!"
			},
			surname : {
				required : "Please enter contact surname!",
				maxlength : "Surname is too big!",
				minlength : "Surname is too short!"
			},
			birth : {
				required : "Contact birth date is required!"
			},
			email : {
				required : "Please enter a valid email address",
				email : "Email has wrong format!"
			},
			phone : {
				required : "Phone is required!",
				digits : "Phone must contain only numbers!"
			}
		}
	});

	$("#upContactForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				maxlength : 120
			},
			surname : {
				required : true,
				minlength : 2,
				maxlength : 120
			},
			email : {
				required : true,
				email : true
			},
			phone : {
				required : true,
				digits : true
			},
			birth : {
				required : true
			}
		},
		messages : {
			name : {
				required : "Please enter contact name!",
				maxlength : "Name is too big!",
				minlength : "Name is too short!"
			},
			surname : {
				required : "Please enter contact surname!",
				maxlength : "Surname is too big!",
				minlength : "Surname is too short!"
			},
			birth : {
				required : "Contact birth date is required!"
			},
			email : {
				required : "Please enter a valid email address",
				email : "Email has wrong format!"
			},
			phone : {
				required : "Phone is required!",
				digits : "Phone must contain only numbers!"
			}
		}
	});
});

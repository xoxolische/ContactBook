$(function() {
	$("#contactBirthDate").datepicker({
		yearRange : "-100:+0",
		changeMonth : true,
		changeYear : true
	});
});

$(function() {
	$("#csBirthDate").datepicker({
		yearRange : "-100:+0",
		changeMonth : true,
		changeYear : true
	});
});

function loadMore() {
	var lastDate = $("#lastDate").val();
	if (!lastDate) {
		lastDate = 0;
	}
	$.ajax({
		url : $('#path').val() + '/api/contact/getMore/' + lastDate,
		type : 'GET'
	}).done(function(data) {
		displayData(data, true, false);
	}).fail(function(data) {
		console.log(data.responseText);
	});
}

function displayData(data, a, delay) {
	if (data && data.length != 0) {
		var p = $('#path').val();
		var html = "";
		var curId = $("#currentId").val();
		for (var i = 0; i < data.length; i++) {
			html += '<tr id="row-' + data[i].id + '">';
			html += '<th>' + data[i].name + '</th>';
			html += '<th>' + data[i].email + '</th>';
			html += '<th>' + data[i].phone + '</th>';
			html += '<th id="action-for-' + data[i].id + '"></th>';
			html += '</tr>';
			if (a) {
				$("#tableBody").append(html);
			} else {
				if (delay) {
					$("#tableBody").prepend(html);
					$("#row-" + data[i].id).attr("style", "display:none");
					$("#row-" + data[i].id).fadeIn(1500);
				} else {
					$("#tableBody").prepend(html);
				}
			}

			$("#action-for-" + data[i].id).append(
					'<a class="btn btn-outline-primary" href="' + p
							+ '/contact/' + data[i].id
							+ '"><i class="fas fa-eye"></i></a>');

			if (data[i].author.id == curId) {
				$("#action-for-" + data[i].id).append(
						'<a class="btn btn-outline-primary" href="' + p
								+ '/contact/edit/' + data[i].id
								+ '"><i class="fas fa-pencil-alt"></i></a>');
				$("#action-for-" + data[i].id).append(
						'<a class="btn btn-outline-primary" href="#" onclick="deleteContact('
								+ data[i].id
								+ ')"><i class="fas fa-trash-alt"></i></a>');
			}
			html = "";
		}
		if (a) {
			$("#lastDate").val(data[data.length - 1].createdAt);
		}
	}
}

function addContact() {
	if ($("#contactForm").valid()) {
		var birthDate = $("#contactBirthDate").val();
		var item = {
			"name" : $("#contactName").val(),
			"surname" : $("#contactSurname").val(),
			"email" : $("#contactEmail").val(),
			"phone" : $("#contactPhone").val(),
			"birthDate" : moment(birthDate, "MM/DD/YYYY"),
			"author" : {
				"id" : $("#currentId").val()
			}
		};

		$.ajax({
			url : $('#path').val() + '/api/contact/create',
			type : 'POST',
			data : JSON.stringify(item),
			contentType : "application/json",
			dataType : 'json'
		}).done(function(data) {
			var a = new Array();
			a.push(data);
			displayData(a, false, true);
			$('#addContact').modal('toggle');
			$("#contactList").scrollTop(0);
			//$("#addContact").reset();
			//document.getElementById("addContact").reset();
			clear($("#addContact"));
		}).fail(function(data) {
			console.log(data);
			if (data.responseJSON != null && data.responseJSON.length != 0) {
				var ar = data.responseJSON;
				for (var i = 0; i < ar.length; i++) {
					$("#contactFormError").append("*" + ar[i] + "<br>");
				}
				$("#contactFormError").removeClass("d-none");
			} else {
				$("#contactFormError").append("Error! Try again later.");
				$("#contactFormError").removeClass("d-none");
			}
		});
	}
}
function clear(e) {

    $(e).find(':input').each(function() {
        switch(this.type) {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });

}
function deleteContact(id) {
	return $.ajax({
		url : $('#path').val() + '/api/contact/delete/' + id,
		type : 'DELETE'
	}).done(function(data) {
		$("#row-" + id).hide();
	}).fail(function(data) {
		alert("Error!");
	});
}

function edit() {
	enable(false);
	$("#editButton").hide();
	$("#buttonContainer").removeClass("d-none");
}

function enable(b) {
	$("#csEmail").prop("disabled", b);
	$("#csName").prop("disabled", b);
	$("#csSurname").prop("disabled", b);
	$("#csPhone").prop("disabled", b);
	$("#csBirthDate").prop("disabled", b);
}

function cancel() {
	enable(true)
	$("#editButton").show();
	$("#buttonContainer").attr("class", "d-none");
}

function apply(id) {
	if ($("#upContactForm").valid()) {
		var birthDate = $("#csBirthDate").val();
		var item = {
			"id" : id,
			"name" : $("#csName").val(),
			"surname" : $("#csSurname").val(),
			"email" : $("#csEmail").val(),
			"phone" : $("#csPhone").val(),
			"birthDate" : moment(birthDate, "MM/DD/YYYY"),
			"author" : {
				"id" : $("#currentId").val()
			}
		};
		console.log(item);
		$.ajax({
			url : $('#path').val() + '/api/contact/update/' + id,
			type : 'POST',
			data : JSON.stringify(item),
			contentType : "application/json",
			dataType : 'json'
		}).done(function(data) {
			console.log(data);
			cancel();
		}).fail(function(data) {
			console.log(data);
			if (data.responseJSON != null && data.responseJSON.length != 0) {
				var ar = data.responseJSON;
				for (var i = 0; i < ar.length; i++) {
					$("#uFormError").append("*" + ar[i] + "<br>");
				}
				$("#uFormError").removeClass("d-none");
			} else {
				$("#uFormError").append("Error! Try again later.");
				$("#uFormError").removeClass("d-none");
			}
		});
	}
}
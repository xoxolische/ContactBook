<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta.jsp"%>
<%@include file="header.jsp"%>
<title>Show Contact</title>
</head>
<body>
	<div id="form" class="container-fluid main-wrap mt-4">
		<div class="card col col-md-6 offset-md-3">
			<div class="card-block">
				<div class="form-group">
					<h4 class="page-title">Show Contact</h4>
					<form id="upContactForm">
						<div class="alert alert-danger d-none" id="uFormError"
							role="alert"></div>
						<div class="alert alert-danger d-none" id="contactFormError"
							role="alert"></div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-envelope"></i></span>
							</div>
							<input type="text" name="email" class="form-control"
								placeholder="Email" value="${c.email}" id="csEmail" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"></i></span>
							</div>
							<input type="text" name="name" class="form-control"
								placeholder="Name" id="csName" value="${c.name}" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fa fa-user"></i></span>
							</div>
							<input type="text" name="surname" class="form-control"
								placeholder="Surname" id="csSurname" value="${c.surname}"
								disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa fa-mobile-alt"></i></span>
							</div>
							<input type="text" class="form-control" name="phone"
								placeholder="Phone" id="csPhone" value="${c.phone}" disabled>
						</div>

						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text"><i
									class="fa fa-calendar-alt"></i></span>
							</div>
							<input type="text" name="birth" class="form-control"
								placeholder="Birth Date" id="csBirthDate" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Create
									Date Time</span>
							</div>
							<input type="text" class="form-control" id="createdAt"
								aria-describedby="basic-addon3" disabled>
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text" id="basic-addon3">Author</span>
							</div>
							<input type="text" class="form-control"
								value="${c.author.nickName}" aria-describedby="basic-addon3"
								disabled>
						</div>

					</form>
					<button id="editButton" type="button"
						class="btn btn-outline-primary col-12 d-none" onclick="edit()">Edit</button>

					<div id="buttonContainer" class="container d-none">
						<div class="row">
							<button type="button" class="btn btn-outline-secondary col-6"
								onclick="cancel()">Cancel</button>
							<button type="button" class="btn btn-outline-success col-6"
								onclick="apply(${c.id})">Apply</button>
						</div>
					</div>

				</div>

				<input type="hidden" id="a-id" value="${c.author.id}"> <input
					type="hidden" id="d" value="${c.birthDate}"> <input
					type="hidden" id="cAt" value="${c.createdAt}">
			</div>
		</div>
	</div>
	<input type="hidden" id="currentId"
		value="${sessionScope.currentUserId}" />
	<%@include file="footer.jsp"%>
	<script type="text/javascript"
		src="<c:url value="/resources/scripts/contacts.js"/>"></script>
	<script>
		$(document).ready(function() {
			var dd = moment($("#d").val(), "YYYY-MM-DD").format("MM/DD/YYYY");
			$("#csBirthDate").val(dd);
		});
		$(document).ready(function() {
			var dd = moment($("#cAt").val()).format("MM/DD/YYYY HH:mm:ss");
			$("#createdAt").val(dd);
		});
		if($("#currentId").val() == $("#a-id").val()){
			$("#editButton").removeClass("d-none");
		}
	</script>
</body>
</html>

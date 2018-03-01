<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta.jsp"%>
<%@include file="header.jsp"%>
<title>Registration</title>
</head>
<body>
	<div id="form" class="container-fluid main-wrap mt-4">
		<div class="card col col-md-6 offset-md-3">
			<div class="card-block">
				<div class="form-group">
					<h4 class="page-title">Registration</h4>
					<form id="registrationForm">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text justify-content-center same-width"><i
									class="fa fa-user"></i></span>
							</div>
							<input name="rName" type="text" class="form-control"
								placeholder="Nick Name" id="nickName">
						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text justify-content-center same-width"><i
									class="fa fa-lock"></i></span>
							</div>
							<input name="rPass" type="password" class="form-control"
								placeholder="Password" id="userPassword">

						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text justify-content-center same-width"><i
									class="fa fa-lock"></i></span>
							</div>
							<input name="rPassConfirm" type="password" class="form-control"
								placeholder="Confirm password" id="userPasswordConfirm">

						</div>
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text justify-content-center same-width"><i
									class="fa fa-envelope"></i></span>
							</div>
							<input name="rEmail" type="email" class="form-control"
								placeholder="Email" id="userEmail">
						</div>

					</form>
					<button onclick="createUser()"
						class="btn btn-outline-success col-12">Register</button>

				</div>


			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script type="text/javascript"
		src="<c:url value="/resources/scripts/user.js"/>"></script>
</body>
</html>

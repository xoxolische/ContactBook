<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style>
.datepicker {
	z-index: 9999 !important
}
</style>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark page-nav">

<a class="navbar-brand" href="${contextPath}/home"
	style="font-size: 20pt;">ContactBook</a>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
	<ul class="navbar-nav mr-auto">
		<security:authorize access="isAuthenticated()">
			<li class="nav-item"><a href="#" data-toggle="modal"
				data-target="#addContact" class="nav-link">New Contact</a></li>
		</security:authorize>
	</ul>

	<ul class="navbar-nav">
		<security:authorize access="isAnonymous()">
			<li><a href="${contextPath}/register" class="nav-link"><i
					class="fa fa-user-plus" aria-hidden="false"></i> Register</a></li>
			<li class="nav-item"><a href="${contextPath}/login"
				class="nav-link"><i class="fas fa-sign-in-alt"></i> Login</a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="nav-link" href="javascript:formSubmit()"
				aria-hidden="false"> <i class="fas fa-sign-out-alt"></i> Logout
			</a></li>
		</security:authorize>

	</ul>
</div>

<input type="hidden" value="${contextPath}" id="path">
<form action="${contextPath}/logout" method="post" id="logoutForm">
</form>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script> </nav>






<!-- Modal -->
<div class="modal fade" id="addContact" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">New contact</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="contactForm">
					<div class="alert alert-danger d-none" id="contactFormError"
						role="alert"></div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-envelope"></i></span>
						</div>
						<input type="text" name="email" class="form-control"
							placeholder="Email" id="contactEmail">
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-user"></i></span>
						</div>
						<input type="text" name="name" class="form-control"
							placeholder="Name" id="contactName">
					</div>
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-user"></i></span>
						</div>
						<input type="text" name="surname" class="form-control"
							placeholder="Surname" id="contactSurname">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-mobile-alt"></i></span>
						</div>
						<input type="text" class="form-control" name="phone"
							placeholder="Phone" id="contactPhone">
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-calendar-alt"></i></span>
						</div>
						<input type="text" name="birth" class="form-control datepicker"
							placeholder="Birth Date" id="contactBirthDate">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-success" onclick="addContact()">Add
					contact</button>
			</div>
		</div>
	</div>
</div>
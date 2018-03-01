<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="meta.jsp"%>
<%@include file="header.jsp"%>
<title>Contacts</title>
</head>
<body onload="loadMore();">
	<div class="container mt-5">
		<div id="contactList">
			<div class="row">
				<table class="table table-bordered table-striped col-8 col-offset-2">
					<thead>
						<tr>
							<th style="width: 30%">Name</th>
							<th style="width: 30%">Email</th>
							<th style="width: 25%">Phone</th>
							<th style="width: 15%">Action</th>
						</tr>
					</thead>
					<tbody id="tableBody">

					</tbody>
				</table>
			</div>
		</div>

		<button type="button" class="btn btn-outline-primary"
			onclick="loadMore()">MORE</button>
	</div>
	<input type="hidden" id="currentId"
		value="${sessionScope.currentUserId}" />
	<input type="hidden" id="lastDate">
	<%@ include file="footer.jsp"%>
	<script type="text/javascript"
		src="<c:url value="/resources/scripts/contacts.js"/>"></script>
</body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="../scripts/jquery-1.7.js" type="text/javascript"></script>
<script src="../scripts/jquery.quicksand.js" type="text/javascript"></script>
<script src="../scripts/movies.js" type="text/javascript"></script>
<link rel="stylesheet" href="<c:url value="/styles/movies.css"/>"
	type="text/css" />
<title>Movie Reservations</title>
</head>

<body>
	<div id="main_wrapper">
		<p>
			<br /> <a class="logout"
				href="<c:url value="/j_spring_security_logout"/>">Logout</a> (
			<security:authentication property="principal.username" />
			)
		</p>
		<security:authorize ifAllGranted="ROLE_ADMIN">
			<div class="main_menu">
				<ul>
					<li><a onclick="showMemberListing()" href="#"
						class="List Users" title="List Users">List Users</a></li>
					<li><a onclick="showMember()" href="#" class="Create Users"
						title="Create Users">Create Users</a></li>
				</ul>
			</div>
			<div class="menuList"></div>
			<div class="createMember" id="createMember">
				<table>
					<tr>
						<td>Membership Number:</td>
						<td><input id="mem" type="text" name="mem" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Name:</td>
						<td><input id="name" type="text" name="name" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Location:</td>
						<td><input id="loc" type="text" name="loc" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input id="pwd" type="password" name="pwd" /></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3"><input type="submit" value="Create Member" onclick="createMember()" /></td>
					</tr>
				</table>
			</div>
			<div class="userlist" id="userlist">
				<table id="memberTable"></table>
			</div>
			<div class="userDetails" id="userDetails">
				<table id="movieReservationTable"></table>
			</div>
		</security:authorize>
		<security:authorize ifNotGranted="ROLE_ADMIN">
			<h1>Create Video Store Member</h1>
			<div class="main_menu">
				<ul id="filters">
					<c:forEach items="${genres}" var="genre">
						<li><a onclick="getmoviesForGenre('${genre.genreID}')"
							href="#" class="${genre.genreName}" title="${genre.genreName}">${genre.genreName}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="movielisting" id="movieFront">
				<ul id="source" class="image-grid"></ul>
				<ul id="destination" class="image-grid"></ul>
			</div>
		</security:authorize>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			getmoviesForGenre(1);
		});
	</script>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link rel="stylesheet" type="text/css" href="acceuil.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous">
	
</script>
<script>
	$(function() {
		$("header").load("header.html");
		$("footer").load("footer.html");
	});
</script>
<title>Acceuil</title>
</head>
<%@ page import="model.User"%>
<%@ page import="model.Group"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	session = request.getSession(false);

	if (session.getAttribute("user") == null) {
		response.sendRedirect("connexion.html");
	}
%>
<body>
	<header></header>
	<input type="search" id="site-search" name="q"
		aria-label="Search through site content">

	<button class="btn">
		<i class="fas fa-search"></i>
	</button>

	<button class="btn"
		onclick="document.getElementById('group_modal').style.display='block'">
		Add Group <i class="fas fa-plus"></i>
	</button>
	<!--  Groups modal-->
	<div id="group_modal" class="modal">
		<span
			onclick="document.getElementById('group_modal').style.display='none'"
			class="close" title="Close Modal">&times;</span>
		<form class="modal-content animate" action="/site_web//CreateGroup"
			method="POST">
			<div class="container">
				<label for="uname"><b>Group's name</b></label> <input class="form"
					type="text" placeholder="Enter group's name" name="uname" required>
				<label for="interests"><b>Interests</b></label> <input class="form"
					type="text" placeholder="concert,jazz,museum,..." name="interests"
					required>
				<button id="modal_btn" type="submit">Create Group</button>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('group_modal').style.display='none'"
					class="cancelbtn">Cancel</button>
			</div>
		</form>
	</div>
	<!-- Event Modal -->
	<button class="btn"
		onclick="document.getElementById('event_modal').style.display='block'">
		Add Event <i class="fas fa-plus"></i>
	</button>
	<div id="event_modal" class="modal">
		<span
			onclick="document.getElementById('event_modal').style.display='none'"
			class="close" title="Close Modal">&times;</span>
		<form class="modal-content animate" action="/site_web//CreateEvent"
			method="POST">
			<div class="container">
				<label for="uname"><b>Event's name</b></label> <input class="form"
					type="text" placeholder="Enter event's name" name="uname" required>
				<label for="interests"><b>Event's theme</b></label> <input
					class="form" type="text" placeholder="concert,jazz,museum,..."
					name="interests" required> <label for="date"><b>Event's
						date</b></label> <input class="form" type="date" placeholder="xx/xx/xxxx"
					name="date" required> <label for="country"><b>Country</b></label>
				<input class="form" type="text" placeholder="United Kingdoms"
					name="country" required> <label for="city"><b>City</b></label>
				<input class="form" type="text" placeholder="London" name="city"
					required> <label for="street"><b>Street</b></label> <input
					class="form" type="text" placeholder="Baker Street" name="street"
					required> <label for="street_number"><b>Street
						number</b></label> <input class="form" type="text" placeholder="221b"
					name="street_number" required> <label for="description"><b>Description</b></label>
				<input class="form" id="description" type="text"
					placeholder="Description of your event" name="description">

				<button id="modal_btn" type="submit">Create Event</button>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button"
					onclick="document.getElementById('event_modal').style.display='none'"
					class="cancelbtn">Cancel</button>
			</div>
		</form>
	</div>
	<script>
		// Get the modal
		var modal_group = document.getElementById('group_modal');
		var modal_event = document.getElementById('event_modal');
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal_group) {
				modal_group.style.display = "none";
			} else if (event.target == modal_event) {
				modal_event.style.display = "none";
			}
		}
	</script>



	<!-- Tabs creation -->
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'b_groups')">
			Browse groups</button>
		<button class="tablinks" onclick="openTab(event, 'b_events')">
			Browse events</button>
		<button class="tablinks" onclick="openTab(event, 'my_groups')">
			My groups</button>
	</div>


	<div id="b_groups" class="tabcontent">
		<table style="width: 100%">
			<tr>
				<th>Nom du groupe</th>
				<th>Créateur</th>
				<th>Nombre de membres</th>
				<th>Centres d'intérêts</th>
			<tr>
				<c:forEach items="${groups}" var="group">
					<tr>
						<td><c:out value="${group.getName()}" /></td>
						<td><c:out value="${group.getOwner().getName()}" /> <c:out
								value="${group.getOwner().getSurname()}" /></td>
						<td><c:out value="${group.getmembers().size()}" /></td>
						<td><c:forEach items="${group.getInterests()}" var="is">
								<ul>
									<li><c:out value="${is}" /></li>
								</ul>

							</c:forEach></td>

					</tr>

				</c:forEach>
		</table>


	</div>

	<div id="b_events" class="tabcontent">
		<table>
			<tr>
				<th>Nom</th>
				<th>Nombre de participants</th>
				<th>Adresse</th>
				<th>Date</th>
				<th>S'inscrire</th>
			</tr>
			<c:forEach items="${events}" var="event">
					<tr>
						<td><c:out value="${event.getName()}" /></td>
						<td><c:out value="${event.getParticipants().size()}" /></td>
						<td><c:out value="${event.getAdresse().Full()}" /></td>
						<td><c:out value="${event.getDate()}" /></td>
						<td><button  class="btn">S'inscrire</button></td>
					</tr>
			</c:forEach>


		</table>
	</div>

	<div id="my_groups" class="tabcontent">
		<table>
			<tr>
				<th>Nom</th>
				<th>Nombre de membres</th>
			</tr>
			<c:forEach items="${groups}" var="group">
				<c:if test="${group.getOwner().getEmail().equals(user.getEmail())}">
					<tr style="display:inline-blocks">
						<td><c:out value="${group.getName()}" /></td>
						<td><c:out value="${group.getmembers().size()}" /></td>
						<td><button style="display:inline-blocks" class="btn">Edition</button></td>
						<td><button style="display:inline-blocks" class="btn">Invitation</button></td>
					</tr>
				</c:if>
			</c:forEach>


		</table>
	</div>



	<script>
		function openTab(evt, cityName) {
			var i, tabcontent, tablinks;
			tabcontent = document.getElementsByClassName("tabcontent");
			for (i = 0; i < tabcontent.length; i++) {
				tabcontent[i].style.display = "none";
			}
			tablinks = document.getElementsByClassName("tablinks");
			for (i = 0; i < tablinks.length; i++) {
				tablinks[i].className = tablinks[i].className.replace(
						" active", "");
			}
			document.getElementById(cityName).style.display = "block";
			evt.currentTarget.className += " active";
		}
	</script>


	<footer> </footer>
</body>
</html>
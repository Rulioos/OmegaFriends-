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
<%@ page import="java.io.*"%>
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
		<form class="modal-content animate" action="/site_web//CreateGroup">
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
		<form class="modal-content animate" action="/action_page.php">
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

	<!-- Groups and events-->
	<div class="groups"></div>
	<%
		final User user = (User) session.getAttribute("user");
		//out.println("<a href='acceuil.jsp'>" + user.getEmail() + "</a>");
		/*
		user.getGroups().stream().forEach(k -> {
			try {
				out.println("ho");
			} catch (IOException ie) {
				//
			}
		});
		*/
	%>
	<div class="events"></div>





	<!-- Tabs creation -->
	<div class="tab">
		<button class="tablinks" onclick="openTab(event, 'calendar')">
			My Calendar</button>
		<button class="tablinks" onclick="openTab(event, 'b_groups')">
			Browse groups</button>
		<button class="tablinks" onclick="openTab(event, 'b_events')">
			Browse events</button>
		<button class="tablinks" onclick="openTab(event, 'my_groups')">
			My groups</button>
	</div>

	<div id="calendar" class="tabcontent">
		<h3>Calendar</h3>
	</div>

	<div id="b_groups" class="tabcontent">
		<h3>b_groups</h3>

	</div>

	<div id="b_events" class="tabcontent">
		<h3>b_events</h3>
	</div>

	<div id="my_groups" class="tabcontent">
		<h3>
			My groups
		</h3>

		<%
			for (Group g : user.getGroups()) {
		%>
		<a href="acceuil.jsp">
			<%
				g.getName();
			%>
		</a>

		<%
			}
		%>

		<h3>Groups i'm in</h3>
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
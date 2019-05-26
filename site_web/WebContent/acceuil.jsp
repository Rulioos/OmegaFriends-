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

	<button>
		<i class="fas fa-search"></i>
	</button>

	<!-- Groups and events-->
	<div class="groups"></div>
	<%
		User user = (User) session.getAttribute("user");
	/*
		out.println("<a href='acceuil.jsp'>" + user.getEmail() + "</a>");
		user.getGroups().stream().forEach(k -> {
			try {
				out.println(k.getName());
			} catch (IOException ie) {
				//
			}
		});
	*/
	%>
	<div class="events"></div>
	<footer> </footer>
</body>
</html>
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
<%
    session=request.getSession(false);
    if(session.getAttribute("user")==null)
    {
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

	<div class="contain">
	<table id="groups">
		<tr>
			<th>Prénom</th>
			<th>Nom</th>
		</tr>
		<tr>
			<td>Jean</td>
			<td>Biche</td>
		</tr>
		<tr>
			<td>Marcel</td>
			<td>Patulacci</td>
		</tr>
	</table>
		<table id="events">
		<tr>
			<th>Prénom</th>
			<th>Nom</th>
		</tr>
		<tr>
			<td>Jean</td>
			<td>Biche</td>
		</tr>
		<tr>
			<td>Marcel</td>
			<td>Patulacci</td>
		</tr>
	</table>
	
	</div>
	<footer> </footer>
</body>
</html>
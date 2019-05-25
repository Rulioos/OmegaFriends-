<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
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
<%
    session=request.getSession(false);
    if(session.getAttribute("user")==null)
    {
        response.sendRedirect("connexion.html");
    }

%> 

</head>
<body>
<header></header>
<footer></footer>
</body>
</html>
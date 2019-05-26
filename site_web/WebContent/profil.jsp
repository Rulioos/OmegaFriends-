<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
<link rel="stylesheet" type="text/css" href="profil.css">
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
	session = request.getSession(false);
	if (session.getAttribute("user") == null) {
		response.sendRedirect("connexion.html");
	}
%>

</head>
<body>


	<%@ page import="model.User"%>
	<%@ page import="java.io.*"%>


	<header></header>
	<%
		User user = (User) session.getAttribute("user");
	%>

	<table class="info">
		<tr>
			<td class="label">Email</td>
			<td><%=user.getEmail()%></td>
			<td>
				<form method="post" action="ModifProfil">
					<input type="hidden" name="form_type" value="mail"> <input
						type="email" name="mail" placeholder="New email"><br>
					<input type="password" name="password" placeholder="confirm password">
					<input type="submit" value="Change Email" id="submit">
				</form>
			<td>
		</tr>
		<tr>
			<td class="label">Name</td>
			<td><%=user.getName()%></td>
		</tr>
		<tr>
			<td class="label">Surname</td>
			<td><%=user.getSurname()%></td>
		</tr>
		<tr>
			<td class="label">BirthDate</td>
			<td><%=user.getBirthDate()%></td>
		</tr>
		<tr>


			<td class="label">Phone</td>
			<%
				if (user.getPhone() != null) {
			%>
			<td><%=user.getPhone()%></td>
			<%
				} else {
			%>
			<td></td>
			<%
				}
			%>

			<td>
				<form method="post" action="ModifProfil">
					<input type="hidden" name="form_type" value="phone"> <input
						type="tel" name="phone" placeholder="New phone number"><br>
					<input type="password" name="password" placeholder="confirm password">
					<input type="submit" value="Submit new Phone number" id="submit">
				</form>
			<td>
		</tr>
		<tr>
			<td class="label">Change password</td>
			<td>

				<form method="post" action="ModifProfil">
					<input type="hidden" name="form_type" value="password"> <input
						type="password" name="password" placeholder="Old password"><br>
					<input type="password" name="new_pwd" placeholder="new password"><br>
					<input type="password" name="confirm_pwd"
						placeholder="confirm new password"> <input type="submit"
						value="Change password" id="submit">
				</form>
			</td>
		</tr>

	</table>



	<footer></footer>
</body>
</html>
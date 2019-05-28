<%@page import="java.util.ArrayList"%>
<%@page import="model.VkEvent"%>
<%@page import="model.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>My Events</title>
		<link rel="stylesheet" type="text/css" href="Res_event.css">
	</head>

	<body>
	
		<div id="event">
			<ul>
				<% ArrayList<VkEvent> cal = (ArrayList<VkEvent>) request.getAttribute("calendar");
					for(VkEvent ev : cal){
						out.println("<li>" + ev.getName()+ "</li>");
					}
				%> 
			</ul>
		</div>



	</body>
</html>
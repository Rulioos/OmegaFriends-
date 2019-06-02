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

	<body onload="setEvent()">
	
		<div id="event">
			<ul id="calendar_ul">
			</ul>
		</div>
	</body>	
</html>


	<script type="text/javascript">
	<% ArrayList<VkEvent> cal = (ArrayList<VkEvent>) request.getAttribute("calendar"); %>
	<% ArrayList<VkEvent> calToCompare = (ArrayList<VkEvent>) request.getAttribute("calendarToCompare"); %>
	
	function setEvent(){
		var ul = document.getElementById("calendar_ul");
		
		<% 
		  String s = "";
		  String t = "";
		  String u = "";
		
		  for(VkEvent ev : cal){ 
		    s = s + "," + '"' +  ev.getHeure_debut() + ":" +ev.getMinute_debut() + '"';
		    t = t + "," + '"' +  ev.getHeure_fin() + ":" +ev.getMinute_fin() + '"';
		    u = u+ "," + '"' +  ev.getName() + ":" +ev.getName() + '"';
		  }
		  
		  if(s.length()>0){
			  s = s.substring(1);
			  t = t.substring(1);
			  u = u.substring(1);
		  }
		%>  
		
		var list_debut = [<% out.print(s); %>];
		var list_fin = [<% out.print(t); %>];
		var list_event_names = [<% out.print(u); %>];
		
		var occupied = 0;
		
		for(i=7; i<= 23; i=i+1){
			var li0 = document.createElement("li");
			var li30 = document.createElement("li");
			
 			if(i<10){
				var iToString = "0"+i.toString();
			}
			else{
				var iToString = i.toString();
			}
		
 			var skip = 0;
			for(it = 0; it<list_debut.length; it=it+1){
				if(list_debut[it].substring(0,2) == iToString){
					li0.appendChild(document.createTextNode(list_debut[it] + " " + list_event_names[it]));
					li0.setAttribute("class", "busy");
					ul.appendChild(li0);
					skip = 1;
					occupied = 1;
					break;
				}
			}
			
			for(it = 0; it<list_fin.length; it=it+1){
				if(list_fin[it].substring(0,2) == iToString){
					li0.appendChild(document.createTextNode(list_fin[it] + " busy"));
					li0.setAttribute("class", "busy");
					ul.appendChild(li0);
					skip = 1;
					occupied = 0;
					break;
				}
			}
			
			if(skip != 1 && occupied!=1){
				li0.appendChild(document.createTextNode(iToString + ":00"));
// 				li30.appendChild(document.createTextNode(iToString + ":30"));
				li0.setAttribute("class", "free");

				ul.appendChild(li0);
// 				ul.appendChild(li30);
			}
			
			if(skip != 1 && occupied==1){
				li0.appendChild(document.createTextNode(iToString + ":00 busy"));
				li0.setAttribute("class", "busy");
// 				li30.appendChild(document.createTextNode(iToString + ":30---"));

				ul.appendChild(li0);
// 				ul.appendChild(li30);
			}
			else{
				continue;
			}


		}
	}
	
	</script>

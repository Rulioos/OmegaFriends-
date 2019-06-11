<%@page import="java.util.ArrayList"%>
<%@page import="model.VkEvent"%>
<%@page import="model.Calendar"%>
<%@page import="model.DataFowarder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		
		<title>My Events</title>
		<link rel="stylesheet" type="text/css" href="Res_comparator_cal.css">

	</head>

	<body onload="setEvent()">
	<header></header>
		<div id="event">
			<ul id="calendar_ul1" class= "calendar_ul">
			</ul>
			
			<ul id="calendar_ul2" class= "calendar_ul">
			</ul>
		</div>
		<footer></footer>
	</body>	
</html>


<script type="text/javascript">
function setEvent(){
	var ul1 = document.getElementById("calendar_ul1");
	var ul2 = document.getElementById("calendar_ul2");
	
	  <% DataFowarder toReceived = (DataFowarder) request.getAttribute("toReceived");
	  ArrayList<VkEvent> cal1 = (ArrayList<VkEvent>) toReceived.getNew_cal1();
	  ArrayList<VkEvent> cal2 = (ArrayList<VkEvent>) toReceived.getNew_cal2();
	  %> 
	
	  <% 
	    String s = "";
	    String t = "";
	    String u = "";
	    
	    String v = "";
	    String w = "";
	    String x = "";
	  
	      for(VkEvent ev : cal1){ 
	          s = s + "," + '"' +  ev.getDate_debut().split("  ")[1] + '"';
	          t = t + "," + '"' +  ev.getDate_fin().split("  ")[1] + '"';
	          u = u+ "," + '"' + ev.getName() + '"';
	        }
	      
	      for(VkEvent ev2 : cal2){ 
	          v = v + "," + '"' +  ev2.getDate_debut().split("  ")[1] + '"';
	          w = w + "," + '"' +  ev2.getDate_fin().split("  ")[1] + '"';
	          x = x + "," + '"' + ev2.getName() + '"';
	        }
	    
	    if(s.length()>0){
	      s = s.substring(1);
	      t = t.substring(1);
	      u = u.substring(1);
	    }
	    if(v.length()>0){
		      v = v.substring(1);
		      w = w.substring(1);
		      x = x.substring(1);
		    }
	  %>  
	  
	  var list_debut = [<% out.print(s); %>];
	  var list_fin = [<% out.print(t); %>];
	  var list_event_names = [<% out.print(u); %>];
	  
	  var list_debut2 = [<% out.print(v); %>];
	  var list_fin2 = [<% out.print(w); %>];
	  var list_event_names2 = [<% out.print(x); %>];
	  
	  for(i=0; i<list_debut.length; i++){
			var lical1 = document.createElement("li");
			
			//temps libre commun
			if(list_debut[i]==list_fin[i]){
				if(list_event_names[i] == "busy common"){
					lical1.appendChild(document.createTextNode(list_debut[i] +"  "+ list_event_names[i]));
					lical1.setAttribute("class", "busy");
					ul1.appendChild(lical1);
				}
				
				else{
					lical1.appendChild(document.createTextNode(list_debut[i] +"  "+ list_event_names[i]));
					lical1.setAttribute("class", "commonFreeTime");
					ul1.appendChild(lical1);
				}

			}
			
			// il y a un event 
			if(list_debut[i]!=list_fin[i]){
				lical1.appendChild(document.createTextNode(list_debut[i] +"  "+ list_event_names[i]));
				lical1.setAttribute("class", "busy");
				ul1.appendChild(lical1);
				
				var t1 = parseInt(list_debut[i].substring(0,2),10);
				var t2 = parseInt(list_fin[i].substring(0,2),10);
				
				if(list_event_names[i] == "free time"){
					while(t1 < t2){
						var lical1 = document.createElement("li");
						t1 = t1+1;
						lical1.appendChild(document.createTextNode(t1 + ":00:00" +"  free"));
						lical1.setAttribute("class", "commonFreeTime");
						ul1.appendChild(lical1);
					}
				}
				else{
					while(t1 < t2){
						var lical1 = document.createElement("li");
						t1 = t1+1;
						lical1.appendChild(document.createTextNode(t1 + ":00:00" +"  busy"));
						lical1.setAttribute("class", "busy");
						ul1.appendChild(lical1);
					}	
				}
			}
	  }
	  
	  
	  
	  
	  for(i=0; i<list_debut2.length; i++){
			var lical2 = document.createElement("li");
			
			//temps libre commun
			if(list_debut2[i]==list_fin2[i]){
				if(list_event_names2[i] == "busy common"){
					lical2.appendChild(document.createTextNode(list_debut2[i] +"  "+ list_event_names2[i]));
					lical2.setAttribute("class", "busy");
					ul2.appendChild(lical2);
				}
				
				else{
					lical2.appendChild(document.createTextNode(list_debut2[i] +"  "+ list_event_names2[i]));
					lical2.setAttribute("class", "commonFreeTime");
					ul2.appendChild(lical2);
				}

			}
			
			// il y a un event 
			if(list_debut2[i]!=list_fin2[i]){
				lical2.appendChild(document.createTextNode(list_debut2[i] +"  "+ list_event_names2[i]));
				lical2.setAttribute("class", "busy");
				ul2.appendChild(lical2);
				
				var t1 = parseInt(list_debut2[i].substring(0,2),10);
				var t2 = parseInt(list_fin2[i].substring(0,2),10);
				
				if(list_event_names2[i] == "free time"){
					while(t1 < t2){
						var lical2 = document.createElement("li");
						t1 = t1+1;
						lical2.appendChild(document.createTextNode(t1 + ":00:00"+"  free"));
						lical2.setAttribute("class", "commonFreeTime");
						ul2.appendChild(lical2);
					}
				}
				else{
					while(t1 < t2){
						var lical2 = document.createElement("li");
						t1 = t1+1;
						lical2.appendChild(document.createTextNode(t1 + ":00:00"+"  busy"));
						lical2.setAttribute("class", "busy");
						ul2.appendChild(lical2);
					}
				}
			}
	  }
}
</script>

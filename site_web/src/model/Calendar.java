package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.sun.org.apache.xpath.internal.operations.Div;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class Calendar {
	
	private ArrayList<VkEvent>list_event;
	private String hyperplanningURL;
	
	public Calendar(String hyperplanningURL) {
		this.list_event = new ArrayList<>();
		this.hyperplanningURL = hyperplanningURL;
	}
	
	public ArrayList<VkEvent> getALLEvent() {
	        ArrayList<String>input_text = new ArrayList<>();
	        String DTSTART = "";
	        String DTEND = "";
	        String SUMMARY = "";
	        try {

	            URL url = new URL(this.hyperplanningURL);

	            // read text returned by server
	            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

	            String line;

	            while ((line = in.readLine()) != null) {

	                switch (line){
	                    case "BEGIN:VEVENT":
	                        input_text.clear();
	                        input_text.add(line);

	                        break;

	                    case "END:VEVENT":

	                        for(String s : input_text){
	                            if (s.contains("DTSTART")){
	                                if (s.split(":")[1].contains("Z")){
	                                    DTSTART = s.split(":")[1];

	                                    DTSTART =   DTSTART.substring(6,8)+"/"+
	                                                DTSTART.substring(4,6) + "/"+
	                                                DTSTART.substring(0,4)+ "  " +
	                                                DTSTART.substring(9,11) + ":" +
	                                                DTSTART.substring(11,13) + ":" +
	                                                DTSTART.substring(13,15);
	                                }
	                            }

	                            if (s.contains("DTEND")){
	                                if (s.split(":")[1].contains("Z")){
	                                    DTEND = s.split(":")[1];

	                                    DTEND = DTEND.substring(6,8)+"/"+
	                                            DTEND.substring(4,6) + "/"+
	                                            DTEND.substring(0,4)+ "  " +
	                                            DTEND.substring(9,11) + ":" +
	                                            DTEND.substring(11,13) + ":" +
	                                            DTEND.substring(13,15);
	                                }
	                            }

	                            if (s.contains("SUMMARY")){
	                                SUMMARY = s.split(":")[1];
	                            }
	                        }
	                        this.list_event.add(new VkEvent(DTSTART, DTEND, SUMMARY));
	                        break;

	                    default:
	                        input_text.add(line);
	                }
	            }
	            in.close();

	        }
	        catch (MalformedURLException e) {
	            System.out.println("Malformed URL: " + e.getMessage());
	        }
	        catch (IOException e) {
	            System.out.println("I/O Error: " + e.getMessage());
	        }

	        return this.list_event;
	}

	public ArrayList<VkEvent> getEventOfTheDay(String date){
		ArrayList<VkEvent> events_of_the_day = new ArrayList<>();
		
		//parsing de la date
		int jour = Integer.parseInt(date.split("/")[0]);
		int mois = Integer.parseInt(date.split("/")[1]);
		int annee = Integer.parseInt(date.split("/")[2]);
		
        Date selected_date = new Date(annee,mois,jour);

		
		for(VkEvent ev : getALLEvent()) {	
	        Date date_debut = new Date(Integer.parseInt(ev.getAnnee_debut()),
	        							Integer.parseInt(ev.getMois_debut()),
        								Integer.parseInt(ev.getJour_debut()));
	        
	        Date date_fin = new Date(Integer.parseInt(ev.getAnnee_fin()),
					Integer.parseInt(ev.getMois_fin()),
					Integer.parseInt(ev.getJour_fin()));
	        
	        if(selected_date.compareTo(date_debut)==0) {
	        	events_of_the_day.add(ev);
	        }
		}
			
		System.out.println(getALLEvent().size() + " events");
		return events_of_the_day;
	}
}

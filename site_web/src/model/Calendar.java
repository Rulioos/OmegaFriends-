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
	
	// récupération de tous les évenements sur hyperplanning
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

	// récupération des évenements par jour indiqué
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


	//pour une journée (de 7h à 23h) on récupére les temps libres communs aux deux calendriers
	public DataFowarder getComparedFreeTime(String date){
		Calendar cal1 = this;
		Calendar cal2 = new Calendar("http://planning.isep.fr/Telechargements/ical/Edt_AUBIER_10821.ics?version=2018.0.3.1&idICal=9C07A6222CC71B1BCDE2C53EEEF06399&param=643d5b312e2e36325d2666683d3126663d31");
		
		ArrayList<VkEvent> cal1_ev = cal1.getEventOfTheDay(date);
		ArrayList<VkEvent> cal2_ev = cal2.getEventOfTheDay(date);
		
		ArrayList<VkEvent> new_cal1 = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2 = new ArrayList<VkEvent>();

		
		//si il n'y a rien de la journée pour la personne 1 et 2
		if(cal1_ev.size() == 0 && cal2_ev.size() == 0) {
			return nothingForBoth(cal1_ev, cal2_ev, date);
		}
		
		// si la personne 1 a au moins un event mais l'autre n'a rien
		if(cal1_ev.size() != 0 && cal2_ev.size() == 0) {
			return OnehasEvent2Hasnt(cal1_ev,cal2_ev,date);
		}
		
		// si la personne 2 a au moins un event mais l'autre n'a rien
		if(cal2_ev.size() != 0 && cal1_ev.size() == 0) {			
			return TwohasEventOneHasnt(cal1_ev,cal2_ev,date);
		}
		

		// si les 2 ont quelque chose dans la journée
		if(cal1_ev.size() != 0 || cal2_ev.size() != 0) {
			return BothhasEvent(cal1_ev,cal2_ev,date);
		}
		
		
//		//mettre en commun les temps libres
//		for(VkEvent ev1 : new_cal1_sorted) {
//			for(VkEvent ev2 : new_cal2_sorted) {
//				// on trouve la même heure
//				if(ev1.getHeure_debut().contentEquals(ev2.getHeure_debut())) {
//					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
//					if(ev1.getName().contentEquals("free time") && !ev2.getName().contentEquals("free time")) {
//						ev1.setName("busy common");
//					}
//				}
//				
//				//verifier qu'on est entre le debut et la fin d'un event
//				if( (Integer.parseInt(ev1.getHeure_debut()) > Integer.parseInt(ev2.getHeure_debut())) && (Integer.parseInt(ev1.getHeure_debut()) < Integer.parseInt(ev2.getHeure_fin())) ) {
//					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
//					if(ev1.getName().contentEquals("free time") && !ev2.getName().contentEquals("free time")) {
//						ev1.setName("busy common");
//					}
//				}
//			}
//		}
		
		

		//mon test
		System.out.println("----- show newCal1_sorted");
		for(VkEvent e1 : new_cal1) {
			System.out.println(e1.getDate_debut() + " à " + e1.getDate_fin() + "  ->"+e1.getName());
		}
		
		System.out.println("----- show newCal2_sorted");
		for(VkEvent e2 : new_cal2) {
			System.out.println(e2.getDate_debut() + " à " + e2.getDate_fin() + "  ->"+e2.getName());
		}
		
		return new DataFowarder(new_cal1, new_cal2);
	}
	
	
	public DataFowarder nothingForBoth(ArrayList<VkEvent> cal1_ev, ArrayList<VkEvent> cal2_ev, String date) {
		ArrayList<VkEvent> new_cal1 = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2 = new ArrayList<VkEvent>();

		new_cal1.add(new VkEvent(date + "  07:00:00", date + "  23:00:00", "free time"));
		new_cal2.add(new VkEvent(date + "  07:00:00", date + "  23:00:00", "free time"));
			
		
		//mon test
		System.out.println("----- show newCal1_sorted");
		for(VkEvent e1 : new_cal1) {
			System.out.println(e1.getDate_debut() + " à " + e1.getDate_fin() + "  ->"+e1.getName());
		}
		
		System.out.println("----- show newCal2_sorted");
		for(VkEvent e2 : new_cal2) {
			System.out.println(e2.getDate_debut() + " à " + e2.getDate_fin() + "  ->"+e2.getName());
		}
			
		return new DataFowarder(new_cal1, new_cal2);
		}

	public DataFowarder OnehasEvent2Hasnt(ArrayList<VkEvent> cal1_ev, ArrayList<VkEvent> cal2_ev, String date) {
		ArrayList<VkEvent> new_cal1 = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2 = new ArrayList<VkEvent>();
		
		new_cal2.add(new VkEvent(date + "  07:00:00", date + "  23:00:00", "free time"));
		ArrayList<Boolean>Tlibre = new ArrayList<Boolean>();
		
		for (int i = 7; i <= 23; i++) {
			String heure = "";
			
			if(i<10) {
				heure = "0"+Integer.toString(i);
			}
			else {
				heure = Integer.toString(i);
			}
			//remplir les nouvelles listes avec les moments libres communs 
			Tlibre.clear();
			VkEvent current_ev = null;
			for(VkEvent ev1 : cal1_ev) {
				current_ev = ev1;
				Tlibre.add( ((Integer.parseInt(heure) < Integer.parseInt(ev1.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev1.getHeure_fin()))) );
				//System.out.println("heure : "+ heure + " date : "+((Integer.parseInt(heure) < Integer.parseInt(ev1.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev1.getHeure_fin()))) + " " + ev1.getDate_debut() + " " + ev1.getDate_fin());
			}
			
			if(!Tlibre.contains(false)) {
				new_cal1.add(new VkEvent(date  + "  " + heure + ":00:00", date  + "  " + heure + ":00:00", "free time"));
			}
		}
		for(VkEvent ev1 : cal1_ev) {
			new_cal1.add(new VkEvent(ev1.getDate_debut(), ev1.getDate_fin(), ev1.getName()));
		}		
		
		//enelever les doublons présents dans les listes
		ArrayList<Integer> new_cal1_index_to_remove = new ArrayList<Integer>();
		ArrayList<Integer> new_cal2_index_to_remove = new ArrayList<Integer>();
		
		if(new_cal1.size()>1) {
			for (int i = 0; i < new_cal1.size()-1; i++) {
				for (int j = i+1; j < new_cal1.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal1.get(i).getDate_debut().contentEquals(new_cal1.get(j).getDate_debut()) ) {
						new_cal1_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal1 : new_cal1_index_to_remove) {
				new_cal1.remove(indexcal1 - recule);
				recule = recule+1;
			}
		}

		if(new_cal2.size()>1) {
			for (int i = 0; i < new_cal2.size()-1; i++) {
				for (int j = i+1; j < new_cal2.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal2.get(i).getDate_debut().contentEquals(new_cal2.get(j).getDate_debut()) ) {
						new_cal2_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal2 : new_cal1_index_to_remove) {
				new_cal2.remove(indexcal2 - recule);
				recule = recule+1;
			}
		}
		
		
		//trier les events
		ArrayList<VkEvent> new_cal1_sorted = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2_sorted = new ArrayList<VkEvent>();
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev1 : new_cal1) {
				if(ev1.getHeure_debut().contentEquals(iToSting)) {
					new_cal1_sorted.add(ev1);
				}
				else {
					continue;
				}
			}
		}
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev2 : new_cal2) {
				if(ev2.getHeure_debut().contentEquals(iToSting)) {
					new_cal2_sorted.add(ev2);
				}
				else {
					continue;
				}
			}
		}
		//mon test
		System.out.println("----- show newCal1_sorted");
		for(VkEvent e1 : new_cal1_sorted) {
			System.out.println(e1.getDate_debut() + " à " + e1.getDate_fin() + "  ->"+e1.getName());
		}
		
		System.out.println("----- show newCal2_sorted");
		for(VkEvent e2 : new_cal2_sorted) {
			System.out.println(e2.getDate_debut() + " à " + e2.getDate_fin() + "  ->"+e2.getName());
		}
		
		return new DataFowarder(new_cal1_sorted, new_cal2_sorted);
	}

	public DataFowarder TwohasEventOneHasnt(ArrayList<VkEvent> cal1_ev, ArrayList<VkEvent> cal2_ev, String date) {
		ArrayList<VkEvent> new_cal1 = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2 = new ArrayList<VkEvent>();
		
		new_cal1.add(new VkEvent(date + "  07:00:00", date + "  23:00:00", "free time"));
		ArrayList<Boolean>Tlibre = new ArrayList<Boolean>();
		
		for (int i = 7; i <= 23; i++) {
			String heure = "";
			
			if(i<10) {
				heure = "0"+Integer.toString(i);
			}
			else {
				heure = Integer.toString(i);
			}
			//remplir les nouvelles listes avec les moments libres communs 
			Tlibre.clear();
			VkEvent current_ev = null;
			for(VkEvent ev2 : cal2_ev) {
				current_ev = ev2;
				Tlibre.add( ((Integer.parseInt(heure) < Integer.parseInt(ev2.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev2.getHeure_fin()))) );
				//System.out.println("heure : "+ heure + " date : "+((Integer.parseInt(heure) < Integer.parseInt(ev2.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev2.getHeure_fin()))) + " " + ev2.getDate_debut() + " " + ev2.getDate_fin());
			}
			
			if(!Tlibre.contains(false)) {
				new_cal2.add(new VkEvent(date  + "  " + heure + ":00:00", date  + "  " + heure + ":00:00", "free time"));
			}
		}
		for(VkEvent ev2 : cal2_ev) {
			new_cal2.add(new VkEvent(ev2.getDate_debut(), ev2.getDate_fin(), ev2.getName()));
		}
		
		
		
		
		//enelever les doublons présents dans les listes
		ArrayList<Integer> new_cal1_index_to_remove = new ArrayList<Integer>();
		ArrayList<Integer> new_cal2_index_to_remove = new ArrayList<Integer>();
		
		if(new_cal1.size()>1) {
			for (int i = 0; i < new_cal1.size()-1; i++) {
				for (int j = i+1; j < new_cal1.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal1.get(i).getDate_debut().contentEquals(new_cal1.get(j).getDate_debut()) ) {
						new_cal1_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal1 : new_cal1_index_to_remove) {
				new_cal1.remove(indexcal1 - recule);
				recule = recule+1;
			}
		}

		if(new_cal2.size()>1) {
			for (int i = 0; i < new_cal2.size()-1; i++) {
				for (int j = i+1; j < new_cal2.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal2.get(i).getDate_debut().contentEquals(new_cal2.get(j).getDate_debut()) ) {
						new_cal2_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal2 : new_cal1_index_to_remove) {
				new_cal2.remove(indexcal2 - recule);
				recule = recule+1;
			}
		}
		
		
		//trier les events
		ArrayList<VkEvent> new_cal1_sorted = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2_sorted = new ArrayList<VkEvent>();
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev1 : new_cal1) {
				if(ev1.getHeure_debut().contentEquals(iToSting)) {
					new_cal1_sorted.add(ev1);
				}
				else {
					continue;
				}
			}
		}
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev2 : new_cal2) {
				if(ev2.getHeure_debut().contentEquals(iToSting)) {
					new_cal2_sorted.add(ev2);
				}
				else {
					continue;
				}
			}
		}
		
		//mon test
		System.out.println("----- show newCal1_sorted");
		for(VkEvent e1 : new_cal1) {
			System.out.println(e1.getDate_debut() + " à " + e1.getDate_fin() + "  ->"+e1.getName());
		}
		
		System.out.println("----- show newCal2_sorted");
		for(VkEvent e2 : new_cal2) {
			System.out.println(e2.getDate_debut() + " à " + e2.getDate_fin() + "  ->"+e2.getName());
		}
		
		return new DataFowarder(new_cal1_sorted, new_cal2_sorted);
	}

	public DataFowarder BothhasEvent(ArrayList<VkEvent> cal1_ev, ArrayList<VkEvent> cal2_ev, String date) {
		ArrayList<VkEvent> new_cal1 = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2 = new ArrayList<VkEvent>();
		
		ArrayList<Boolean>Tlibre = new ArrayList<Boolean>();
		
		for (int i = 7; i <= 23; i++) {
			String heure = "";
			
			if(i<10) {
				heure = "0"+Integer.toString(i);
			}
			else {
				heure = Integer.toString(i);
			}
			//remplir les nouvelles listes avec les moments libres communs 
			Tlibre.clear();
			VkEvent current_ev = null;
			for(VkEvent ev1 : cal1_ev) {
				current_ev = ev1;
				Tlibre.add( ((Integer.parseInt(heure) < Integer.parseInt(ev1.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev1.getHeure_fin()))) );
				//System.out.println("heure : "+ heure + " date : "+((Integer.parseInt(heure) < Integer.parseInt(ev1.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev1.getHeure_fin()))) + " " + ev1.getDate_debut() + " " + ev1.getDate_fin());
			}
			
			if(!Tlibre.contains(false)) {
				new_cal1.add(new VkEvent(date  + "  " + heure + ":00:00", date  + "  " + heure + ":00:00", "free time"));
			}
		}
		for(VkEvent ev1 : cal1_ev) {
			new_cal1.add(new VkEvent(ev1.getDate_debut(), ev1.getDate_fin(), ev1.getName()));
		}
		
		for (int i = 7; i <= 23; i++) {
			String heure = "";
			
			if(i<10) {
				heure = "0"+Integer.toString(i);
			}
			else {
				heure = Integer.toString(i);
			}
			//remplir les nouvelles listes avec les moments libres 
			Tlibre.clear();
			VkEvent current_ev = null;
			for(VkEvent ev2 : cal2_ev) {
				current_ev = ev2;
				Tlibre.add( ((Integer.parseInt(heure) < Integer.parseInt(ev2.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev2.getHeure_fin()))) );
				//System.out.println("heure : "+ heure + " date : "+((Integer.parseInt(heure) < Integer.parseInt(ev2.getHeure_debut())) || (Integer.parseInt(heure) > Integer.parseInt(ev2.getHeure_fin()))) + " " + ev2.getDate_debut() + " " + ev2.getDate_fin());
			}
			
			if(!Tlibre.contains(false)) {
				new_cal2.add(new VkEvent(date  + "  " + heure + ":00:00", date  + "  " + heure + ":00:00", "free time"));
			}
		}
		for(VkEvent ev2 : cal2_ev) {
			new_cal2.add(new VkEvent(ev2.getDate_debut(), ev2.getDate_fin(), ev2.getName()));
		}
		
		
		//enelever les doublons présents dans les listes
		ArrayList<Integer> new_cal1_index_to_remove = new ArrayList<Integer>();
		ArrayList<Integer> new_cal2_index_to_remove = new ArrayList<Integer>();
		
		if(new_cal1.size()>1) {
			for (int i = 0; i < new_cal1.size()-1; i++) {
				for (int j = i+1; j < new_cal1.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal1.get(i).getDate_debut().contentEquals(new_cal1.get(j).getDate_debut()) ) {
						new_cal1_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal1 : new_cal1_index_to_remove) {
				new_cal1.remove(indexcal1 - recule);
				recule = recule+1;
			}
		}

		if(new_cal2.size()>1) {
			for (int i = 0; i < new_cal2.size()-1; i++) {
				for (int j = i+1; j < new_cal2.size()-1; j++) {
					//si c'est le meme nom d'event
					if(new_cal2.get(i).getDate_debut().contentEquals(new_cal2.get(j).getDate_debut()) ) {
						new_cal2_index_to_remove.add(j);
						break;	
					}
				}
			}
			int recule = 0;
			for(int indexcal2 : new_cal1_index_to_remove) {
				new_cal2.remove(indexcal2 - recule);
				recule = recule+1;
			}
		}
		
		
		//trier les events
		ArrayList<VkEvent> new_cal1_sorted = new ArrayList<VkEvent>();
		ArrayList<VkEvent> new_cal2_sorted = new ArrayList<VkEvent>();
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev1 : new_cal1) {
				if(ev1.getHeure_debut().contentEquals(iToSting)) {
					new_cal1_sorted.add(ev1);
				}
				else {
					continue;
				}
			}
		}
		
		for (int i = 7; i <= 23; i++) {
			String iToSting = Integer.toString(i);
			if(i<10) {
				iToSting = "0"+Integer.toString(i);
			}
			
			for(VkEvent ev2 : new_cal2) {
				if(ev2.getHeure_debut().contentEquals(iToSting)) {
					new_cal2_sorted.add(ev2);
				}
				else {
					continue;
				}
			}
		}
		
		
		//mettre en commun les temps libres pour 1
		for(VkEvent ev1 : new_cal1_sorted) {
			for(VkEvent ev2 : new_cal2_sorted) {
				// on trouve la même heure
				if(ev1.getHeure_debut().contentEquals(ev2.getHeure_debut())) {
					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
					if(ev1.getName().contentEquals("free time") && !ev2.getName().contentEquals("free time")) {
						ev1.setName("busy common");
					}
				}
				
				//verifier qu'on est entre le debut et la fin d'un event
				if( (Integer.parseInt(ev1.getHeure_debut()) > Integer.parseInt(ev2.getHeure_debut())) && (Integer.parseInt(ev1.getHeure_debut()) < Integer.parseInt(ev2.getHeure_fin())) ) {
					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
					if(ev1.getName().contentEquals("free time") && !ev2.getName().contentEquals("free time")) {
						ev1.setName("busy common");
					}
				}
			}
		}
		
		
		//mettre en commun les temps libres pour 2
		for(VkEvent ev2 : new_cal2_sorted) {
			for(VkEvent ev1 : new_cal1_sorted) {
				// on trouve la même heure
				if(ev2.getHeure_debut().contentEquals(ev1.getHeure_debut())) {
					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
					if(ev2.getName().contentEquals("free time") && !ev1.getName().contentEquals("free time")) {
						ev2.setName("busy common");
					}
				}
				
				//verifier qu'on est entre le debut et la fin d'un event
				if( (Integer.parseInt(ev2.getHeure_debut()) > Integer.parseInt(ev1.getHeure_debut())) && (Integer.parseInt(ev2.getHeure_debut()) <= Integer.parseInt(ev1.getHeure_fin())) ) {
					// verifier que c'est un temps libre mais ce n'est pas le cas pour l'autre : on modifie ev1
					if(ev2.getName().contentEquals("free time") && !ev1.getName().contentEquals("free time")) {
						ev2.setName("busy common");
					}
				}
			}
		}
		
		//mon test
		System.out.println("----- show newCal1_sorted");
		for(VkEvent e1 : new_cal1_sorted) {
			System.out.println(e1.getDate_debut() + " à " + e1.getDate_fin() + "  ->"+e1.getName());
		}
		
		System.out.println("----- show newCal2_sorted");
		for(VkEvent e2 : new_cal2_sorted) {
			System.out.println(e2.getDate_debut() + " à " + e2.getDate_fin() + "  ->"+e2.getName());
		}
		
		return new DataFowarder(new_cal1_sorted, new_cal2_sorted);
		
	}




}

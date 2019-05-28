package model;

public class VkEvent {

    private String date_debut;
    private String date_fin;
    private String name;

    public VkEvent(String date_debut, String date_fin, String name) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.name = name;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getJour_debut(){
        String date = this.getDate_debut();
        String rs = date.substring(0,2);

        return rs;
    }

    public String getMois_debut(){
        String date = this.getDate_debut();
        String rs = date.substring(3,5);

        return rs;
    }

    public String getAnnee_debut(){
        String date = this.getDate_debut();
        String rs = date.substring(6,10);

        return rs;
    }

    public String getHeure_debut(){
        String date = this.getDate_debut();
        String rs = date.substring(12,14);

        return rs;
    }

    public String getMinute_debut(){
        String date = this.getDate_debut();
        String rs = date.substring(15,17);

        return rs;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getJour_fin(){
        String date = this.getDate_fin();
        String rs = date.substring(0,2);

        return rs;
    }

    public String getMois_fin(){
        String date = this.getDate_fin();
        String rs = date.substring(3,5);

        return rs;
    }

    public String getAnnee_fin(){
        String date = this.getDate_fin();
        String rs = date.substring(6,10);

        return rs;
    }

    public String getHeure_fin(){
        String date = this.getDate_fin();
        String rs = date.substring(12,14);

        return rs;
    }

    public String getMinute_fin(){
        String date = this.getDate_fin();
        String rs = date.substring(15,17);

        return rs;
    }

    public String getName() {
        return name;
    }
}

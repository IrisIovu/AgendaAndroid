package eu.ase.angedasincronizareonline.utils;

public class Proiect {
    private String proiectId;
    private String numeProiect;
    private int procentSucces;

    public Proiect(){

    }
    public Proiect(String proiectId, String numeProiect, int procentSucces) {
        this.proiectId = proiectId;
        this.numeProiect = numeProiect;
        this.procentSucces = procentSucces;
    }

    public String getProiectId() {
        return proiectId;
    }

    public void setProiectId(String proiectId) {
        this.proiectId = proiectId;
    }

    public String getNumeProiect() {
        return numeProiect;
    }

    public void setNumeProiect(String numeProiect) {
        this.numeProiect = numeProiect;
    }

    public int getProcentSucces() {
        return procentSucces;
    }

    public void setProcentSucces(int procentSucces) {
        this.procentSucces = procentSucces;
    }

    @Override
    public String toString() {
        return "Proiect{" +
                "proiectId='" + proiectId + '\'' +
                ", numeProiect='" + numeProiect + '\'' +
                ", procentSucces=" + procentSucces +
                '}';
    }
}

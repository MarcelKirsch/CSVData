package org.de.csv.person;

import java.util.Date;

public class Person {
    private String vorname;
    private String nachname;
    private Date geburtsDatum;
    private String geschlecht;
    private String telefonnr;
    private String bundesland;
    private String stadt;
    private String plz;
    private String hausnummmer;
    private String adresszusatz;

    public Person(String vorname, String nachname, Date geburtsDatum, String geschlecht, String telefonnr, String bundesland, String stadt, String plz, String hausnummmer, String adresszusatz) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsDatum = geburtsDatum;
        this.geschlecht = geschlecht;
        this.telefonnr = telefonnr;
        this.bundesland = bundesland;
        this.stadt = stadt;
        this.plz = plz;
        this.hausnummmer = hausnummmer;
        this.adresszusatz = adresszusatz;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(Date geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getHausnummmer() {
        return hausnummmer;
    }

    public void setHausnummmer(String hausnummmer) {
        this.hausnummmer = hausnummmer;
    }

    public String getAdresszusatz() {
        return adresszusatz;
    }

    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }
}

package org.de.csv.person;

import java.util.Date;

public class PersonBuilder {
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

    public PersonBuilder setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public PersonBuilder setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public PersonBuilder setGeburtsDatum(Date geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
        return this;
    }

    public PersonBuilder setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
        return this;
    }

    public PersonBuilder setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
        return this;
    }

    public PersonBuilder setBundesland(String bundesland) {
        this.bundesland = bundesland;
        return this;
    }

    public PersonBuilder setStadt(String stadt) {
        this.stadt = stadt;
        return this;
    }

    public PersonBuilder setPlz(String plz) {
        this.plz = plz;
        return this;
    }

    public PersonBuilder setHausnummmer(String hausnummmer) {
        this.hausnummmer = hausnummmer;
        return this;
    }

    public PersonBuilder setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
        return this;
    }

    public Person createPerson() {
        return new Person(vorname, nachname, geburtsDatum, geschlecht, telefonnr, bundesland, stadt, plz, hausnummmer, adresszusatz);
    }
}
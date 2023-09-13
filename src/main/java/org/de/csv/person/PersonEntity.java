package org.de.csv.person;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Person", schema = "dbo", catalog = "CSVData")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "personid")
    private int personid;
    @Basic
    @Column(name = "Vorname")
    private String vorname;
    @Basic
    @Column(name = "Nachname")
    private String nachname;
    @Basic
    @Column(name = "Geburtsdatum")
    private LocalDate geburtsdatum;
    @Basic
    @Column(name = "Geschlecht")
    private String geschlecht;
    @Basic
    @Column(name = "Telefonnummer")
    private String telefonnummer;
    @Basic
    @Column(name = "Bundesland")
    private String bundesland;
    @Basic
    @Column(name = "Stadt")
    private String stadt;
    @Basic
    @Column(name = "PLZ")
    private String plz;
    @Basic
    @Column(name = "Strasse")
    private String strasse;
    @Basic
    @Column(name = "Hausnummer")
    private String hausnummer;
    @Basic
    @Column(name = "Adresszusatz")
    private String adresszusatz;

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
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

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
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

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getAdresszusatz() {
        return adresszusatz;
    }

    public void setAdresszusatz(String adresszusatz) {
        this.adresszusatz = adresszusatz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return personid == that.personid && Objects.equals(vorname, that.vorname) && Objects.equals(nachname, that.nachname) && Objects.equals(geburtsdatum, that.geburtsdatum) && Objects.equals(geschlecht, that.geschlecht) && Objects.equals(telefonnummer, that.telefonnummer) && Objects.equals(bundesland, that.bundesland) && Objects.equals(stadt, that.stadt) && Objects.equals(plz, that.plz) && Objects.equals(strasse, that.strasse) && Objects.equals(hausnummer, that.hausnummer) && Objects.equals(adresszusatz, that.adresszusatz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personid, vorname, nachname, geburtsdatum, geschlecht, telefonnummer, bundesland, stadt, plz, strasse, hausnummer, adresszusatz);
    }
}

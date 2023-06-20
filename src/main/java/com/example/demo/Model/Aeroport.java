package com.example.demo.Model;
import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Aeroport")
public class Aeroport {
    private static final long serialVersionUID = 1L;

    private static int i=1;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "loc")
    private Point loc;

    @Column(name = "nbrPistes")
    private int nbrPistes;

    @Column(name = "nbrPlaces")
    private int nbrPlaces;
    @Column(name = "nbrPistesActuel")
    private static int nbrPistesActuel;
    @Column(name = "nbrPlacesActuel")
    private static int nbrPlacesActuel;//momkin tzid final maxnbrPlacesActuel
    @Column(name = "delaiAttente")
    private double delaiAttente;
    @Column(name = "tempsAcces")
    private double tempsAcces;
    @Column(name = "delaiAnticollision")
    private double delaiAnticollision;
    @Column(name = "tempsDecollage")
    private double tempsDecollage;
    @Column(name = "dureeBoucle")
    private double dureeBoucle;

//    private List<Avion> avionsPark;
//    private ArrayList<Avion> avionsPiste;



//    public Aeroport(String nom, double latitude, double longitude, int nbrPistes, int nbrPlaces, double delaiAttente, double tempsAcces, double delaiAnticollision, double tempsDecollage, double dureeBoucle) {
//
//        this.nom = nom;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.nbrPistes = nbrPistes;
//        this.nbrPlaces = nbrPlaces;
//        this.delaiAttente = delaiAttente;
//        this.tempsAcces = tempsAcces;
//        this.delaiAnticollision = delaiAnticollision;
//        this.tempsDecollage = tempsDecollage;
//        this.dureeBoucle = dureeBoucle;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }

    public int getNbrPistes() {
        return nbrPistes;
    }

    public void setNbrPistes(int nbrPistes) {
        this.nbrPistes = nbrPistes;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public static int getNbrPistesActuel() {
        return nbrPistesActuel;
    }

    public static void setNbrPistesActuel(int nbrPistesActuel) {
        Aeroport.nbrPistesActuel = nbrPistesActuel;
    }

    public static int getNbrPlacesActuel() {
        return nbrPlacesActuel;
    }

    public static void setNbrPlacesActuel(int nbrPlacesActuel) {
        Aeroport.nbrPlacesActuel = nbrPlacesActuel;
    }

    public double getDelaiAttente() {
        return delaiAttente;
    }

    public void setDelaiAttente(double delaiAttente) {
        this.delaiAttente = delaiAttente;
    }

    public double getTempsAcces() {
        return tempsAcces;
    }

    public void setTempsAcces(double tempsAcces) {
        this.tempsAcces = tempsAcces;
    }

    public double getDelaiAnticollision() {
        return delaiAnticollision;
    }

    public void setDelaiAnticollision(double delaiAnticollision) {
        this.delaiAnticollision = delaiAnticollision;
    }

    public double getTempsDecollage() {
        return tempsDecollage;
    }

    public void setTempsDecollage(double tempsDecollage) {
        this.tempsDecollage = tempsDecollage;
    }

    public double getDureeBoucle() {
        return dureeBoucle;
    }

    public void setDureeBoucle(double dureeBoucle) {
        this.dureeBoucle = dureeBoucle;
    }

//    public ArrayList<Avion> getAvionsPark() {
//        return avionsPark;
//    }
//
//    public void setAvionsPark(ArrayList<Avion> avionsPark) {
//        this.avionsPark = avionsPark;
//    }
//
//    public ArrayList<Avion> getAvionsPiste() {
//        return avionsPiste;
//    }
//
//    public void setAvionsPiste(ArrayList<Avion> avionsPiste) {
//        this.avionsPiste = avionsPiste;
//    }
//
//    public void parquer(Avion a){
//        //ajouter un delai de decharegr l'avion
//        if(nbrPlacesActuel > 0){
//            nbrPlacesActuel--;
//            avionsPark.add(a);
//        }
//
//    }
//
//    public void pister(Avion a){
//        if(nbrPistesActuel>0){
//            nbrPistesActuel--;
//            avionsPiste.add(a);
//        }
//
//        // after charger l'avion + tempsAcces Au piste ==> nbrPistesActuel--
//
//    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "nom='" + nom + '\'' +
                ", loc=" + loc +
                ", nbrPistes=" + nbrPistes +
                ", nbrPlaces=" + nbrPlaces +
                ", delaiAttente=" + delaiAttente +
                ", tempsAcces=" + tempsAcces +
                ", delaiAnticollision=" + delaiAnticollision +
                ", tempsDecollage=" + tempsDecollage +
                ", dureeBoucle=" + dureeBoucle +
//                ", avionsPark=" + avionsPark +
//                ", avionsPiste=" + avionsPiste +
                '}';
    }
}
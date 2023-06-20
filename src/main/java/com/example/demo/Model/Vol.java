package com.example.demo.Model;


import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Vol")
public class Vol {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aeroport_depart_id")
    private Aeroport aeroportDepart;

    @ManyToOne
    @JoinColumn(name = "aeroport_arrive_id")
    private Aeroport aeroportArrive;

    @ManyToOne
    @JoinColumn(name = "avion_id")
    private Avion avion;

    @Column(name = "dateDepart")
    private Date dateDepart;

    @Column(name = "dateArrive")
    private Date dateArrive;

    @ManyToOne
    @JoinColumn(name = "escale_id")
    private Aeroport escale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aeroport getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(Aeroport aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public Aeroport getAeroportArrive() {
        return aeroportArrive;
    }

    public void setAeroportArrive(Aeroport aeroportArrive) {
        this.aeroportArrive = aeroportArrive;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Aeroport getEscale() {
        return escale;
    }

    public void setEscale(Aeroport escale) {
        this.escale = escale;
    }
}

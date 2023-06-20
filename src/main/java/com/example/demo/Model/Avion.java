package com.example.demo.Model;
import lombok.Getter;
import lombok.Setter;

import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Avion")
public class Avion implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "consommation")
    private Double consommation;

    @Column(name = "capacite")
    private Double capacite;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if(type.equalsIgnoreCase("court")){
            this.consommation = 800.0;
            this.capacite = 25000.0;
            //dis*800/100 = capacite
        }
        if(type.equalsIgnoreCase("moyen")){
            this.consommation = 1000.0;
            this.capacite = 60000.0;
        }

        if(type.equalsIgnoreCase("long")){
            this.consommation = 1500.0;
            this.capacite = 150000.0;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getConsommation() {
        return consommation;
    }

    public void setConsommation(Double consommation) {
        this.consommation = consommation;
    }

    public Double getCapacite() {
        return capacite;
    }

    public void setCapacite(Double capacite) {
        this.capacite = capacite;
    }


}

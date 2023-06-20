package com.example.demo.service.impl;

import com.example.demo.Model.Aeroport;
import com.example.demo.repository.AeroportRepositry;
import com.example.demo.service.AeroportService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class AeroportServiceImpl implements AeroportService {

    @Autowired
    private AeroportRepositry aeroportRepositry;

    public List<Aeroport> findAll() {
        //System.out.println("gone");
        save_csv();
        return aeroportRepositry.findAll();
    }

    public void save_csv() {
        // Read all the data from our table and store it in the response object
        List<Aeroport> res = aeroportRepositry.findAll();

        if(res.isEmpty() == true){
            System.out.println("No Data");

            String[] HEADERS = {"nom", "x", "y", "nombrePistes", "nombrePlacesAuSol","delaiAttenteAuSol","delaiAccesAuxPistes","delaiAnticollision","tempsDecollageOuAtterrissage","dureeBoucleAttenteEnVol"};
            String fileLocation = "D:\\Users\\HP\\java-workspsace\\demo1\\src\\main\\resources\\aeroports.csv";

            try {
                Reader in = new FileReader(fileLocation);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT
                        .withHeader(HEADERS)
                        .withFirstRecordAsHeader()
                        .parse(in);

                for (CSVRecord record : records) {
                    String nom = record.get("nom");
                    String latitude = record.get("x");
                    String longitude = record.get("y");
                    String nombrePistes = record.get("nombrePistes");
                    String nombrePlacesAuSol = record.get("nombrePlacesAuSol");
                    String delaiAttenteAuSol = record.get("delaiAttenteAuSol");
                    String delaiAccesAuxPistes = record.get("delaiAccesAuxPistes");
                    String delaiAnticollision = record.get("delaiAnticollision");
                    String tempsDecollageOuAtterrissage = record.get("tempsDecollageOuAtterrissage");
                    String dureeBoucleAttenteEnVol = record.get("dureeBoucleAttenteEnVol");

                    // Convert to proper data types
                    Double dLatitude = Double.parseDouble(latitude);
                    Double dLongitude = Double.parseDouble(longitude);
                    Point geom = new GeometryFactory().createPoint(new Coordinate(dLongitude, dLatitude));
                    Integer nbPistes = Integer.valueOf(nombrePistes);
                    Integer nombrePlaces = Integer.valueOf(nombrePlacesAuSol);
                    Double delaiAttente = Double.parseDouble(delaiAttenteAuSol);
                    Double delaiAccesAuxPiste = Double.parseDouble(delaiAccesAuxPistes);
                    Double delaiAnticollisio = Double.parseDouble(delaiAnticollision);
                    Double tempsDecollageOuAtterrissag = Double.parseDouble(tempsDecollageOuAtterrissage);
                    Double dureeBoucleAttenteEnVo = Double.parseDouble(dureeBoucleAttenteEnVol);

                    // Load data into our WaterConsumption Table
                    Aeroport wc = new Aeroport();
                    wc.setNom(nom);
                    wc.setLoc(geom);
                    wc.setNbrPistes(nbPistes);
                    wc.setNbrPlaces(nombrePlaces);
                    wc.setDelaiAttente(delaiAttente);
                    wc.setTempsAcces(delaiAccesAuxPiste);
                    wc.setDelaiAnticollision(delaiAnticollisio);
                    wc.setTempsDecollage(tempsDecollageOuAtterrissag);
                    wc.setDureeBoucle(dureeBoucleAttenteEnVo);
                    aeroportRepositry.save(wc);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Data Loaded");
        }



    }

}


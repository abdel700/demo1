package com.example.demo.service.impl;

import com.example.demo.Model.Avion;
import com.example.demo.Model.Vol;
import com.example.demo.repository.AvionRepository;
import com.example.demo.repository.VolRepositry;
import com.example.demo.service.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

@Service
public class AvionServiceImpl implements AvionService {

    @Autowired
    private AvionRepository avionRepositry;


    public List<Avion> findAll() {
        System.out.println("gone");
        search_vol();
        return avionRepositry.findAll();
    }

    public void search_vol(){
        // Read all the data from our table and store it in the response object
        List<Avion> res = avionRepositry.findAll();

        if(res.isEmpty() == true){
            System.out.println("No Data");

            String[] HEADERS = {"nom", "x", "y", "nombrePistes", "nombrePlacesAuSol","delaiAttenteAuSol","delaiAccesAuxPistes","delaiAnticollision","tempsDecollageOuAtterrissage","dureeBoucleAttenteEnVol"};
            String fileLocation = "D:\\Users\\HP\\java-workspsace\\demo1\\src\\main\\resources\\aeroports.csv";


            // Load data into our WaterConsumption Table
//            Vol wc = new Vol();
//            wc.setDateArrive(new Date());
//            avionRepositry.save(wc);



        } else {
            System.out.println("Data Loaded");
        }



    }

}

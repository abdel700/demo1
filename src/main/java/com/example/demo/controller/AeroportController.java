package com.example.demo.controller;
import com.example.demo.Model.Aeroport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AeroportService;

import java.util.List;

@RestController
@RequestMapping("/aeroport")
public class AeroportController {
    @Autowired
    private AeroportService aeroportService;

    @GetMapping
    public List<Aeroport> findAll() {
        System.out.println("gone1 ");
        return aeroportService.findAll();
    }

//    @GetMapping(path = "/topten")
//    public List<WaterConsumption> findTopTenConsumers() {
//        return waterConsumptionService.findTopTenConsumers();
//    }

}

package com.example.demo.controller;
import com.example.demo.Model.Aeroport;
import com.example.demo.Model.Avion;
import com.example.demo.service.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AeroportService;

import java.util.List;

@RestController
@RequestMapping("/avion")
public class AvionController {
    @Autowired
    private AvionService avionService;

    @GetMapping
    public List<Avion> findAll() {
        System.out.println("gone1 ");
        return avionService.findAll();
    }
}

package com.example.demo.controller;

import com.example.demo.Model.Aeroport;
import com.example.demo.Model.Vol;
import com.example.demo.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AeroportService;


import java.util.List;

@RestController
@RequestMapping("/vol")
public class VolController {

    @Qualifier("volServiceImpl")
    @Autowired
    private VolService vs;

    @GetMapping
    public List<Vol> findAll() {
        System.out.println("gone1 ");
        return vs.findAll();
    }
}

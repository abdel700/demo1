package com.example.demo.service;

import com.example.demo.Model.Aeroport;
import com.example.demo.Model.Vol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VolService {
    List<Vol> findAll();
}

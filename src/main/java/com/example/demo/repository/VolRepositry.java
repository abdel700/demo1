package com.example.demo.repository;

import com.example.demo.Model.Avion;
import com.example.demo.Model.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolRepositry extends JpaRepository<Vol, Integer> {

}

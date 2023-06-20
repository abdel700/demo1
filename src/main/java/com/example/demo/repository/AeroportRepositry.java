package com.example.demo.repository;

import com.example.demo.Model.Aeroport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AeroportRepositry extends JpaRepository<Aeroport, Integer> {
//    @Query(value = "select * from Aeroport"
//            , nativeQuery = true)
//
//    List<Aeroport> findAll();

}
package com.example.demo.repository;

import java.util.List;

import com.example.demo.Model.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AvionRepository extends  JpaRepository<Avion, Integer> {
//    @Query(value = "select * from Avion"
//            , nativeQuery = true)
//    List<Avion> findAllAvions();

}

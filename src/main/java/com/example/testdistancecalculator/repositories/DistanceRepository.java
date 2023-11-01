package com.example.testdistancecalculator.repositories;

import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.entity.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
      Optional<Distance> findByFromCityAndAndToCity (City fromCity, City toCity);
}
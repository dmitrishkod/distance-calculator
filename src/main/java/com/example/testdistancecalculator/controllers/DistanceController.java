package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.enums.CalcType;
import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.entity.Distance;
import com.example.testdistancecalculator.entity.models.DistanceModel;
import com.example.testdistancecalculator.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("distance")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    /**
     * Задание 2. Поиск по любому из представленных расстояний между городами
     * @param firstCityGroup
     * @param secondCityGroup
     * @param calcType
     * @return
     */
    @PostMapping(path = "/calculate", produces = "application/json")
    public ResponseEntity <List<Double>> calculateDistances(List<City> firstCityGroup, List<City> secondCityGroup, CalcType calcType){ //TODO: Класс с этими значениями
        try {
            return ResponseEntity.ok(distanceService.calculateDistances(firstCityGroup,secondCityGroup,calcType));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

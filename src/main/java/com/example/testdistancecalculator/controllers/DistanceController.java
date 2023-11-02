package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.entity.models.DistanceTypeModel;
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
     * @param distanceTypeModel (упаковал в модельку, чтобы можно было например передать с фронта нормально пост)
     * @return
     */
    @PostMapping(path = "/calculate", produces = "application/json")
    public ResponseEntity <String> calculateDistances(DistanceTypeModel distanceTypeModel){
        try {
            return ResponseEntity.ok(distanceService.calculateDistances(distanceTypeModel.getFirstCityGroup(),distanceTypeModel.getSecondCityGroup(),distanceTypeModel.getCalcType()));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

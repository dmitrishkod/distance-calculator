package com.example.testdistancecalculator.controllers;

import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.entity.models.CityModel;
import com.example.testdistancecalculator.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * Задание 1.Получение списка городов (ID, Name)
     *
     * @return
     */
    @GetMapping(path = "/cities", produces = "application/json")
    public ResponseEntity<List<CityModel>> getAllCities () {
        try {
            return ResponseEntity.ok(cityService.getAllCities());
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Задание 3.Загрузка файла и последующая конвертация его в XML файл (сохраняется на сервере), из которого метод parse конвертирует нужны данные и добавляет в базу данных.
     * @param cityList
     * @return
     * @throws IOException
     */
    @PostMapping(path = "/cities/upload")
    public ResponseEntity uploadCities(@RequestParam("file") MultipartFile cityList) throws IOException {
        try {
            cityService.upload(cityList);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.example.testdistancecalculator.services;

import com.example.testdistancecalculator.enums.CalcType;
import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.entity.Distance;
import com.example.testdistancecalculator.entity.models.DistanceModel;
import com.example.testdistancecalculator.repositories.CityRepository;
import com.example.testdistancecalculator.repositories.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DistanceService {

    @Autowired
    private DistanceRepository distanceRepository;

    private final double earthRadius = 6372.795;

    /**
     * Расчет прямого расстояния между двумя городами
     * @param city1
     * @param city2
     * @return результат расчета прямого расстояния
     */
    private String сrowflight(City city1, City city2){
        double longtitude1 = city1.getLongtitude()*Math.PI/180;
        double longtitude2 = city2.getLongtitude()*Math.PI/180;
        double latitude1 = city1.getLatitude()*Math.PI/180;
        double latitude2 = city2.getLatitude()*Math.PI/180;

        double d = earthRadius * (Math.acos(Math.sin(latitude1)*Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longtitude2 - longtitude1)));

       return CalcType.CROWFLIGHT.name() + " Расстояние между городами " + city1.getName() + " и " + city2.getName() + " равно " + d + "\n";
    }

    /**
     * Берем дистанцию из базы данных
     * @param city1
     * @param city2
     * @return возвращает дистанцию между городами
     */
    private String distanceMatrix(City city1, City city2){
        Optional<Distance> distanceR = distanceRepository.findByFromCityAndAndToCity(city1,city2);
        Optional<Distance> distance = distanceRepository.findByFromCityAndAndToCity(city2,city1);
        if (distanceR.isPresent()){
            double d = distanceR.get().getDistance();
            return  CalcType.DISTANCE_MATRIX.name() + " Расстояние между городами " + city1.getName() + " и " + city2.getName() + " равно " + d + "\n";
        } else if (distance.isPresent()){
            double d = distance.get().getDistance();
            return  CalcType.DISTANCE_MATRIX.name() + " Расстояние между городами " + city1.getName() + " и " + city2.getName() + " равно " + d + "\n";
        } else {
            return CalcType.DISTANCE_MATRIX.name() +  " Расстояние между городами " + city1.getName() + " и " + city2.getName() + " не существует в базе \n";
        }
    }

    /**
     * Возвращает расчет дистанции на выбор Crowflight, Distance Matrix
     * @param city1
     * @param city2
     * @param calcType Enum - выбор между Crowflihgt и Distance Matrix для пользователя
     * @return Возвращает выбранную калькуляцию
     */
    private String calculateDistance (City city1, City city2, CalcType calcType){
        String result = "";
        if (calcType == CalcType.CROWFLIGHT){
            result = сrowflight(city1,city2);
            return result;
        }
        else if (calcType == CalcType.DISTANCE_MATRIX) {
            result = distanceMatrix(city1,city2);
            return result;
        }
        return result;
    }

    /**
     * Расчитывает дистанцию между городами любого типа
     * @param firstCityGroup
     * @param secondCityGroup
     * @param calcType
     * @return Возвращает список результатов
     */
    public String calculateDistances(List<City> firstCityGroup, List<City> secondCityGroup, CalcType calcType){
        StringBuilder builder = new StringBuilder();
        if (calcType == CalcType.ALL){
            for (City city: firstCityGroup) {
                for (City city1: secondCityGroup) {
                    builder.append(distanceMatrix(city,city1));
                    builder.append(сrowflight(city, city1));
                }
            }
        }else {
            for (City city: firstCityGroup) {
                for (City city1: secondCityGroup) {
                    builder.append(calculateDistance(city, city1, calcType));
                }
            }
        }
        return builder.toString();
    }
}
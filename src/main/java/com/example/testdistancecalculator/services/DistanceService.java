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
    private Double сrowflight(City city1, City city2){
        double longtitude1 = city1.getLongtitude()*Math.PI/180;
        double longtitude2 = city2.getLongtitude()*Math.PI/180;
        double latitude1 = city1.getLatitude()*Math.PI/180;
        double latitude2 = city2.getLatitude()*Math.PI/180;

       return earthRadius * (Math.acos(Math.sin(latitude1)*Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longtitude2 - longtitude1)));
    }

    /**
     * Берем дистанцию из базы данных
     * @param city1
     * @param city2
     * @return возвращает дистанцию между городами
     */
    private Double distanceMatrix(City city1, City city2){
        Optional<Distance> distanceR = distanceRepository.findByFromCityAndAndToCity(city1,city2);
        return distanceR.isPresent() ?
                distanceR.get().getDistance()
                : null;
    }

    /**
     * Возвращает расчет дистанции Crowflight, Distance Matrix
     * @param city1
     * @param city2
     * @return Возвращает список из двух дистанций
     */
    private List<Double> allDistance(City city1, City city2){
        double сrowflight = сrowflight(city1,city2);
        double distanceMatrix = distanceMatrix(city1,city2);
        List<Double> all = new ArrayList<>();
        all.add(сrowflight);
        all.add(distanceMatrix);
        return all;
    }

    /**
     * Возвращает расчет дистанции на выбор Crowflight, Distance Matrix
     * @param city1
     * @param city2
     * @param calcType Enum - выбор между Crowflihgt и Distance Matrix для пользователя
     * @return Возвращает выбранную калькуляцию
     */
    private Double calculateDistance (City city1, City city2, CalcType calcType){
        double result = 0;
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
     * Расчитывает дистанцию между городами на выбор <Crowflight, Distance Matrix, All>
     * @param firstCityGroup
     * @param secondCityGroup
     * @param calcType
     * @return Возвращает список результатов
     */
    public List<Double> calculateDistances(List<City> firstCityGroup, List<City> secondCityGroup, CalcType calcType){
        List<Double> calculateResult = new ArrayList<>();
        for (int i = 0; i < firstCityGroup.size(); i ++) {
            City city1 = firstCityGroup.get(i);
            City city2 = secondCityGroup.get(i);
            switch (calcType){
                case CROWFLIGHT:
                case DISTANCE_MATRIX:
                    Double result = calculateDistance(city1,city2,calcType);
                    calculateResult.add(result);
                    break;
                case ALL:
                    calculateResult = allDistance(city1,city2);
                    break;
            }
        }
        return calculateResult;
    }
}
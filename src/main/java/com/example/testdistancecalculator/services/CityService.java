package com.example.testdistancecalculator.services;

import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.entity.models.CityModel;
import com.example.testdistancecalculator.entity.models.CityParseModel;
import com.example.testdistancecalculator.repositories.CityRepository;
import com.example.testdistancecalculator.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private XmlParser xmlParser;

//    private XmlParser xmlParser = new XmlParser();

    /**
     * Метод возращает список городов с ID и Name.
     * @return список городов.
     */
    public List<CityModel> getAllCities() {
        List<City> city = cityRepository.findAll();
        ArrayList<CityModel> cityModel = new ArrayList<>();

        for (City city1: city){
            CityModel currentCityModel = new CityModel(city1.getId(),city1.getName());
            cityModel.add(currentCityModel);
        }
        return cityModel;
    }

    /**
     * Метод конвертирует получаемый файл в хмл файл в локальной папке на пк. Методом parse получает список значений и сохраняет их в базе данных
     * @param cityList получаемый файл со списком городов
     * @throws IOException
     */
    public void upload(MultipartFile cityList) throws IOException {
        ArrayList<CityParseModel> cityParseModelArrayList = xmlParser.parse(cityList);

        ArrayList<City> cities = new ArrayList<>();
        cityParseModelArrayList.forEach(model -> {
            City city = new City();
            city.setName(model.getName());
            city.setLatitude(model.getLatitude());
            city.setLongtitude(model.getLongtitude());
            cities.add(city);
        });
        cityRepository.saveAll(cities);
    }
}
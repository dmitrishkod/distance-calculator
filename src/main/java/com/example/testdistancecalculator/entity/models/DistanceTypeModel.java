package com.example.testdistancecalculator.entity.models;

import com.example.testdistancecalculator.entity.City;
import com.example.testdistancecalculator.enums.CalcType;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DistanceTypeModel {
    List<City> firstCityGroup;
    List<City> secondCityGroup;
    CalcType calcType;
}

package com.example.testdistancecalculator.entity.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CityParseModel {
    private String name;
    private Double latitude;
    private Double longtitude;
}

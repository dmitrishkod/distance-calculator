package com.example.testdistancecalculator.entity.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class DistanceModel {
    private Long fromCity;
    private Long toCity;
    private Double distance;
}

package com.example.testdistancecalculator.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Entity
@Table(name ="city")
public class City {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longtitude")
    private Double longtitude;
}

package com.example.testdistancecalculator.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode
@ToString
@Table(name ="distance")
public class Distance {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city", referencedColumnName = "id")
    private City fromCity;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_city", referencedColumnName = "id")
    private City toCity;

    @Column(name = "distance")
    private Double distance;
}

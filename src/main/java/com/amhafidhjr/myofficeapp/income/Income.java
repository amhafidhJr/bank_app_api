package com.amhafidhjr.myofficeapp.income;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "income_tbl")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Long income_id;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date_collected")
    private String date_collected;

    @Column(name = "status", columnDefinition = "INT DEFAULT 1")
    private int status;

}

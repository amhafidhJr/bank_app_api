package com.amhafidhjr.myofficeapp.charity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "charity_tbl")
public class Charity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charity_id")
    private Long charity_id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status", columnDefinition = "INT DEFAULT 1")
    private int status;

    @Column(name = "date_recorded")
    private Timestamp dateRecorded;

    @PrePersist
    private void prePersist() {
        dateRecorded = new Timestamp(System.currentTimeMillis());
    }

}

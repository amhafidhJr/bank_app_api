package com.amhafidhjr.myofficeapp.saving;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "saving_tbl")
public class Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saving_id")
    private Long saving_id;

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

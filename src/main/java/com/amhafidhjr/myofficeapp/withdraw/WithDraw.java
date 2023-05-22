package com.amhafidhjr.myofficeapp.withdraw;

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
@Table(name = "withdraw_tbl")
public class WithDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdraw_id")
    private Long withdraw_id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "amount")
    private int amount;

    @Column(name = "status",  columnDefinition = "INT DEFAULT 1")
    private int status;

    @Column(name = "date_recorded")
    private Timestamp dateRecorded;

    @PrePersist
    private void prePersist() {
        dateRecorded = new Timestamp(System.currentTimeMillis());
    }
}

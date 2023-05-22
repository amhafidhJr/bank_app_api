package com.amhafidhjr.myofficeapp.saving;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SavingRepository extends JpaRepository<Saving, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE saving_tbl  SET status = 1 WHERE saving_id = ?1", nativeQuery = true)
    void updateStatus(@Param("savingId") Long savingId);
}

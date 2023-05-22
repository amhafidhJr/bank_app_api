package com.amhafidhjr.myofficeapp.income;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE income_tbl  SET status = 1 WHERE income_id = ?1", nativeQuery = true)
    void updateStatus(@Param("incomeId") Long incomeId);


}

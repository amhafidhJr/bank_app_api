package com.amhafidhjr.myofficeapp.withdraw;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WithDrawRepository extends JpaRepository<WithDraw, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE withdraw_tbl  SET status = 1 WHERE withdraw_id = ?1", nativeQuery = true)
    void deleteWithdraw(@Param("withdraw_id") Long withdrawId);
}

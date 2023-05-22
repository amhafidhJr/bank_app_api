package com.amhafidhjr.myofficeapp.charity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE charity_tbl  SET status = 1 WHERE charity_id = ?1", nativeQuery = true)
    void deleteData(@Param("charityId") Long charityId);

}

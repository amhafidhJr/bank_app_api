package com.amhafidhjr.myofficeapp.expenses;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE expenses_tbl SET status = 1 WHERE expense_id = ?1",
            nativeQuery = true
    )
    void updateStatus(@Param("expenseId") Long expenseId);

    List<Expense> findAllByStatus(int status);
}

package com.amhafidhjr.myofficeapp.expenses;

import com.amhafidhjr.myofficeapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ResponseEntity<String> createExpense(Expense expense) {
        expenseRepository.save(expense);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Expense created successfully");
    }

//    public Expense createExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }

//    public List<Expense> getExpenses() {
//        return expenseRepository.findAll();
//    }

    public List<Expense> getExpenses() {
        return expenseRepository.findAllByStatus(0);
    }

    Expense getExpense(Long id) {
        return getExpenses()
                .stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException(
                                "expense with id " + id + " not found"
                        ));
    }

    public Expense UpdateExpense(Long id, Expense expenseData) {
        return expenseRepository.findById(id)
                .map(expense-> {
                    expense.setTitle(expenseData.getTitle());
                    expense.setDescription(expenseData.getDescription());
                    expense.setAmount(expenseData.getAmount());
                    expense.setDate_issued(expenseData.getDate_issued());

                    return expenseRepository.save(expense);
                }).orElseThrow(
                        () -> new NotFoundException(
                                "expense with id " + id + " not found"
                        )
                );
    }

    public ResponseEntity<?> deleteExpense(Long id, Expense status) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    expenseRepository.delete(expense);
                    return ResponseEntity.ok().build();
                }).orElseThrow(
                        () -> new NotFoundException(
                                "expense with id " + id + " not found"
                        )
                );
    }

    public void updateStatus(Long id) {
        expenseRepository.updateStatus(id);
    }


}

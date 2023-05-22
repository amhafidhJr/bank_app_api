package com.amhafidhjr.myofficeapp.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;


    @Autowired
    public ExpenseController(ExpenseService expenseService,
                             ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
    }

    @PostMapping
    void createNewExpense(@RequestBody Expense expense) {
        expenseService.createExpense(expense);
    }

    @GetMapping(path = "all")
    List<Expense> getExpenses() {
        return expenseService.getExpenses();
    }

    @GetMapping(path = "/{id}")
    Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpense(id);
    }

    @PutMapping(path = "/{id}")
    Expense updateCustomer(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.UpdateExpense(id, expense);
    }

    @PutMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.updateStatus(id);
        return ResponseEntity.noContent().build();
    }







}

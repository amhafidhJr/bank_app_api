package com.amhafidhjr.myofficeapp.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/income")
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    void createIncome(@RequestBody Income income) {
        incomeService.createIncome(income);
    }

    @GetMapping(path = "all")
    List<Income> getIncomes() {
        return incomeService.getIncomes();
    }

    @GetMapping(path = "/{id}")
    Income getIncome(@PathVariable Long id) {
        return incomeService.getIncome(id);
    }

    @PutMapping(path = "/{id}")
    Income updateIncome(@PathVariable Long id, Income income){
        return incomeService.updateIncome(id, income);
    }

    @PutMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }


}

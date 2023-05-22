package com.amhafidhjr.myofficeapp.income;

import com.amhafidhjr.myofficeapp.charity.Charity;
import com.amhafidhjr.myofficeapp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    public ResponseEntity<String> createIncome(Income income) {
        incomeRepository.save(income);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Income Created Successfully");
    }

    public List<Income> getIncomes() {
        return incomeRepository.findAll();
    }

    public Income getIncome(Long id) {

            return getIncomes()
                    .stream()
                    .filter(income -> income.getIncome_id().equals(id))
                    .findFirst()
                    .orElseThrow(
                            () -> new NotFoundException(
                                    "income with id " + id + " not found"
                            ));

    }

    public Income updateIncome(Long id, Income income) {
        return incomeRepository.findById(id)
                .map(incomeData -> {
                    incomeData.setTitle(income.getTitle());
                    incomeData.setAmount(income.getAmount());
                    incomeData.setDate_collected(income.getDate_collected());

                    return incomeRepository.save(incomeData);
                }).orElseThrow(
                        () -> new NotFoundException("Income with id " + id + " is not found")
                );
    }

    public void deleteIncome(Long id) {
        incomeRepository.updateStatus(id);
    }
}

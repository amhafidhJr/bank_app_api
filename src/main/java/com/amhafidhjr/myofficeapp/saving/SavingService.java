package com.amhafidhjr.myofficeapp.saving;

import com.amhafidhjr.myofficeapp.exception.NotFoundException;
import com.amhafidhjr.myofficeapp.income.Income;
import com.amhafidhjr.myofficeapp.income.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingService {

    private final SavingRepository savingRepository;

    @Autowired
    public SavingService(SavingRepository savingRepository) {
        this.savingRepository = savingRepository;

    }

    public ResponseEntity<String> createSaving(Saving saving) {
        savingRepository.save(saving);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Income Created Successfully");
    }

    public List<Saving> getSavings() {
        return savingRepository.findAll();
    }

    public Saving getSaving(Long id) {

            return getSavings()
                    .stream()
                    .filter(saving -> saving.getSaving_id().equals(id))
                    .findFirst()
                    .orElseThrow(
                            () -> new NotFoundException(
                                    "saving with id " + id + " not found"
                            ));

    }

    public Saving updateSaving(Long id, Saving saving) {
        return savingRepository.findById(id)
                .map(savingData -> {
                    savingData.setAmount(saving.getAmount());

                    return savingRepository.save(savingData);
                }).orElseThrow(
                        () -> new NotFoundException("Saving with that id " + id + " is not found")
                );
    }

    public void deleteSaving(Long id) {
        savingRepository.updateStatus(id);
    }
}

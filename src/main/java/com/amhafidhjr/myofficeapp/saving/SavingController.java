package com.amhafidhjr.myofficeapp.saving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/saving")
public class SavingController {

    private final SavingService savingService;

    @Autowired
    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }

    @PostMapping
    public void createSaving(@RequestBody Saving saving) {
        savingService.createSaving(saving);
    }

    @GetMapping(path = "all")
    List<Saving> getSavings(){
        return savingService.getSavings();
    }

    @GetMapping(path = "/{id}")
    Saving getSaving(@PathVariable Long id) {
        return  savingService.getSaving(id);
    }

    @PutMapping(path = "/{id}")
        Saving updateSaving(@PathVariable Long id, @RequestBody Saving saving) {
        return savingService.updateSaving(id,saving);
    }

    @PutMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteSaving(@PathVariable Long id) {
        savingService.deleteSaving(id);
        return ResponseEntity.noContent().build();
    }
}

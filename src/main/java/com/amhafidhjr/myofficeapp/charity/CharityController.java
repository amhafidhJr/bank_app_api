package com.amhafidhjr.myofficeapp.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/charity")
public class CharityController {

    private final CharityService charityService;

    @Autowired
    public CharityController(CharityService charityService) {
        this.charityService = charityService;
    }

    @PostMapping
    void createCharity(@RequestBody Charity charity) {
        charityService.createCharity(charity);
    }

    @GetMapping(path = "all")
    public List<Charity> getCharities() {
        return charityService.getCharities();
    }

    @GetMapping(path = "/{id}")
    public Charity getCharity(@PathVariable Long id) {
        return charityService.getCharity(id);
    }

    @PutMapping(path = "/{id}")
    public Charity updateCharity(@PathVariable Long id, Charity charity) {
        return  charityService.updateCharity(id, charity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharity(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            charityService.deleteCharity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Handle any exception that may occur during the delete operation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

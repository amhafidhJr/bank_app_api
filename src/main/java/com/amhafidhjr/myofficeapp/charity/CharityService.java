package com.amhafidhjr.myofficeapp.charity;

import com.amhafidhjr.myofficeapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharityService {

   private final CharityRepository charityRepository;

    @Autowired
    public CharityService(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    public ResponseEntity<String> createCharity(Charity charity) {
        charityRepository.save(charity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Charity Created Successfully");
    }

    List<Charity> getCharities(){
        return charityRepository.findAll();
    }

    public Charity getCharity(Long id) {
        return getCharities()
                .stream()
                .filter(charity -> charity.getCharity_id().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new NotFoundException(
                                "charity with id " + id + " not found"
                        ));
    }

    public Charity updateCharity(Long id, Charity charity) {
        return charityRepository.findById(id)
                .map(charityData -> {
                    charityData.setDescription(charity.getDescription());
                    charityData.setAmount(charity.getAmount());

                    return charityRepository.save(charityData);
                }).orElseThrow(
                        () -> new NotFoundException("No data found with that id")
                );
    }

    public void deleteCharity (Long id) {
        charityRepository.deleteById(id);
    }

//    public void checkIfIdExist(Long id) {
//        Optional<Charity> checkId = charityRepository.findById(id);
//        if(checkId.isEmpty()) {
//            throw new NotFoundException("No data found with that id");
//        }
//    }
}

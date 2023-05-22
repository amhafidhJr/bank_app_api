package com.amhafidhjr.myofficeapp.withdraw;

import com.amhafidhjr.myofficeapp.charity.Charity;
import com.amhafidhjr.myofficeapp.exception.NotFoundException;
import com.amhafidhjr.myofficeapp.income.IncomeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithDrawService {

    private final WithDrawRepository withDrawRepository;


    public WithDrawService(WithDrawRepository withDrawRepository) {
        this.withDrawRepository = withDrawRepository;

    }

    public ResponseEntity<String> createWithdraw(WithDraw withDraw) {
        withDrawRepository.save(withDraw);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully Recorded");
    }

    public List<WithDraw> getWithdraws(){
        return withDrawRepository.findAll();
    }

    public WithDraw getWithdraw(Long id) {

            return getWithdraws()
                    .stream()
                    .filter(charity -> charity.getWithdraw_id().equals(id))
                    .findFirst()
                    .orElseThrow(
                            () -> new NotFoundException(
                                    "withdraw with id " + id + " not found"
                            ));

    }

    public WithDraw updateWithdraw(Long id, WithDraw withDraw) {
       return withDrawRepository.findById(id)
                .map(withDrawData ->  {
                    withDrawData.setAmount(withDraw.getAmount());
                    withDrawData.setReason(withDraw.getReason());

                    return withDrawRepository.save(withDrawData);
                }).orElseThrow(
                        () -> new NotFoundException("Invalid id")
                );
    }

    public void deleteWithDraw(Long id) {
         Optional<WithDraw> checkId = withDrawRepository.findById(id);

         if(checkId.isEmpty()) {
             throw new NotFoundException("Invalid Id");
         }
        withDrawRepository.deleteWithdraw(id);
    }
}

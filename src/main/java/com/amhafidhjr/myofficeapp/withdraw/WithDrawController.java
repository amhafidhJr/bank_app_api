package com.amhafidhjr.myofficeapp.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(path = "api/v1/withdraw")
public class WithDrawController {

    private final WithDrawService withDrawService;

    @Autowired
    public WithDrawController(WithDrawService withDrawService) {
        this.withDrawService = withDrawService;
    }

    @PostMapping
    void createWithdraw(@RequestBody WithDraw withDraw) {
        withDrawService.createWithdraw(withDraw);
    }

    @GetMapping(path = "all")
    public List<WithDraw> getWithDraws(){
        return withDrawService.getWithdraws();
    }

    @GetMapping(path = "/{id}")
    public WithDraw getWithDraw(@PathVariable Long id) {
        return withDrawService.getWithdraw(id);
    }

    @PutMapping(path = "/{id}")
    public WithDraw updateWithdraw(@PathVariable Long id, @RequestBody WithDraw withDraw) {
        return withDrawService.updateWithdraw(id, withDraw);
    }

    @PutMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteWithdraw(@PathVariable Long id) {
        withDrawService.deleteWithDraw(id);
        return ResponseEntity.noContent().build();
    }
}

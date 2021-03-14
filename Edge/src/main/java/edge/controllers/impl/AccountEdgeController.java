package edge.controllers.impl;

import edge.controllers.interfaces.IAccountEdgeController;
import edge.dto.AccountGetDTO;
import edge.dto.BalanceDTO;
import edge.models.Money;
import edge.security.services.impl.AccountEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class AccountEdgeController implements IAccountEdgeController {

    @Autowired
    private AccountEdgeService accountEdgeService;

    @GetMapping("/accounts/by-userId/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @CrossOrigin
    public List<AccountGetDTO> findAccountsByUserId(@PathVariable Long id) {
        return accountEdgeService.getAllAccountsByUserId(id);
    }

    @PatchMapping("/updateBalance/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Long id, @RequestBody Money balance) {
        accountEdgeService.updateBalance(id, balance);
    }


}

package edge.controllers.interfaces;

import edge.dto.AccountGetDTO;
import edge.dto.BalanceDTO;
import edge.models.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IAccountEdgeController {

    public List<AccountGetDTO> findAccountsByUserId(Long id);
    public void updateBalance (Long id, Money balance);
}

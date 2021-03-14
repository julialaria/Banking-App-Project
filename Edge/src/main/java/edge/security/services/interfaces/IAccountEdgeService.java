package edge.security.services.interfaces;

import edge.dto.AccountGetDTO;
import edge.dto.BalanceDTO;
import edge.models.Money;

import java.util.List;

public interface IAccountEdgeService {

    public List<AccountGetDTO> getAllAccountsByUserId(Long id);
    public void updateBalance (Long id, Money balance);
}

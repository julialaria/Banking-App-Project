package edge.security.services.impl;

import edge.client.AccountClient;
import edge.dto.AccountGetDTO;
import edge.dto.BalanceDTO;
import edge.dto.CheckingGetDTO;
import edge.models.Money;
import edge.security.services.interfaces.IAccountEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountEdgeService implements IAccountEdgeService {

    @Autowired
    AccountClient accountClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<AccountGetDTO> getAllAccountsByUserId(Long id) {
        CircuitBreaker getAllAccountsByUserIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return getAllAccountsByUserIdCircuitBreaker.run(() -> accountClient.findAccountsByUserId(id), throwable -> getAllAccountsByUserIdFallback());
    }

    private List<AccountGetDTO> getAllAccountsByUserIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public void updateBalance (Long id, Money balance) {
        accountClient.updateBalance(id, balance);
    }
}

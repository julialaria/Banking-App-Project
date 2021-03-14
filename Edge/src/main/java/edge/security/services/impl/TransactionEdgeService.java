package edge.security.services.impl;

import edge.client.TransactionClient;
import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;
import edge.dto.TransactionDTO;
import edge.security.services.interfaces.ITransactionEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionEdgeService implements ITransactionEdgeService {

    @Autowired
    TransactionClient transactionClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<TransactionDTO> getAll() {
        CircuitBreaker findAllCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findAllCircuitBreaker.run(() -> transactionClient.getAll(), throwable -> getAllFallback());
    }

    private List<TransactionDTO> getAllFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public void create(TransactionDTO transactionDTO) {
        transactionClient.create(transactionDTO);
    }
}

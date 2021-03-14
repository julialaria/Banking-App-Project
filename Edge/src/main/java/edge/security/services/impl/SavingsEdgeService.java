package edge.security.services.impl;

import edge.client.SavingsClient;
import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;
import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;
import edge.security.services.interfaces.ISavingsEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SavingsEdgeService implements ISavingsEdgeService {

    @Autowired
    SavingsClient savingsClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<SavingsGetDTO> findAll() {
        CircuitBreaker findAllCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findAllCircuitBreaker.run(() -> savingsClient.findAllSavings(), throwable -> findAllFallback());
    }

    private List<SavingsGetDTO> findAllFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public SavingsGetDTO findById(Long id) {
        CircuitBreaker findByIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findByIdCircuitBreaker.run(() -> savingsClient.findById(id), throwable -> findByIdFallback());
    }

    private SavingsGetDTO findByIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public List <SavingsGetDTO> findSavingsByUserId(Long id) {
        CircuitBreaker findSavingsByUserIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findSavingsByUserIdCircuitBreaker.run(() -> savingsClient.findSavingsByUserId(id), throwable -> findSavingsByUserIdFallback());
    }

    private List<SavingsGetDTO> findSavingsByUserIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public void create(SavingsDTO savingsDTO) {
        savingsClient.create(savingsDTO);
    }
}

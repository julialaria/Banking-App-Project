package edge.security.services.impl;

import edge.client.CreditCardClient;
import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;
import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;
import edge.security.services.interfaces.ICreditCardEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CreditCardEdgeService implements ICreditCardEdgeService {

    @Autowired
    CreditCardClient creditCardClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<CreditCardGetDTO> findAll() {
        CircuitBreaker findAllCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findAllCircuitBreaker.run(() -> creditCardClient.findAllCreditCard(), throwable -> findAllFallback());
    }

    private List<CreditCardGetDTO> findAllFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public CreditCardGetDTO findById(Long id) {
        CircuitBreaker findByIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findByIdCircuitBreaker.run(() -> creditCardClient.findById(id), throwable -> findByIdFallback());
    }

    private CreditCardGetDTO findByIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public List <CreditCardGetDTO> findCreditCardByUserId(Long id) {
        CircuitBreaker findCreditCardByUserIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findCreditCardByUserIdCircuitBreaker.run(() -> creditCardClient.findCreditCardByUserId(id), throwable -> findCreditCardByUserIdFallback());
    }

    private List<CreditCardGetDTO> findCreditCardByUserIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public void create(CreditCardDTO creditCardDTO) {
        creditCardClient.create(creditCardDTO);
    }
}

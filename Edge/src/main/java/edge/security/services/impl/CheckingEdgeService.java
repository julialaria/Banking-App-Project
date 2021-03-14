package edge.security.services.impl;

import edge.client.CheckingClient;
import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;
import edge.security.services.interfaces.ICheckingEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class CheckingEdgeService implements ICheckingEdgeService {

    @Autowired
    private CheckingClient checkingClient;

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public List<CheckingGetDTO> findAll() {
        CircuitBreaker findAllCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findAllCircuitBreaker.run(() -> checkingClient.findAllChecking(), throwable -> findAllFallback());
    }

    private List<CheckingGetDTO> findAllFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public CheckingGetDTO findById(Long id) {
        CircuitBreaker findByIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findByIdCircuitBreaker.run(() -> checkingClient.findById(id), throwable -> findByIdFallback());
    }

    private CheckingGetDTO findByIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public List <CheckingGetDTO> findCheckingByUserId(Long id) {
        CircuitBreaker findCheckingByUserIdCircuitBreaker = circuitBreakerFactory.create("micro-service");
        return findCheckingByUserIdCircuitBreaker.run(() -> checkingClient.findCheckingByUserId(id), throwable -> findCheckingByUserIdFallback());
    }

    private List<CheckingGetDTO> findCheckingByUserIdFallback() {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
    }


    public void create(CheckingDTO checkingDTO) {
      checkingClient.create(checkingDTO);
    }

}

package edge.client;

import edge.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@FeignClient("micro-service")
public interface TransactionClient {


    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAll();

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TransactionDTO transactionDTO);

}

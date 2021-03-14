package edge.client;

import edge.dto.*;
import edge.models.Money;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("micro-service")
public interface AccountClient {

    @GetMapping("/accounts/by-userId/{id}")
    @CrossOrigin
    public List<AccountGetDTO> findAccountsByUserId(@PathVariable Long id);

    @PatchMapping("/updateBalance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Long id, @RequestBody Money balance);

}



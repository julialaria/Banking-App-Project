package edge.client;

import edge.dto.CreditCardGetDTO;
import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("micro-service")
public interface SavingsClient {

    @GetMapping("/savings")
    @CrossOrigin
    public List<SavingsGetDTO> findAllSavings();

    @GetMapping("/savings/by-id/{id}")
    @CrossOrigin
    public SavingsGetDTO findById(@PathVariable Long id);

    @GetMapping("/savings/by-userid/{id}")
    @CrossOrigin
    public List <SavingsGetDTO> findSavingsByUserId(@PathVariable Long id);

    @PostMapping("/create/savings")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid SavingsDTO savingsDTO);

}

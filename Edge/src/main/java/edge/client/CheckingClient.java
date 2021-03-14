package edge.client;

import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("micro-service")
public interface CheckingClient {

    @GetMapping("/checking")
    @CrossOrigin
    public List<CheckingGetDTO> findAllChecking();

    @GetMapping("/checking/by-id/{id}")
    @CrossOrigin
    public CheckingGetDTO findById(@PathVariable Long id);

    @GetMapping("/checking/by-userid/{id}")
    @CrossOrigin
    public List <CheckingGetDTO> findCheckingByUserId(@PathVariable Long id);

    @PostMapping("/create/checking")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CheckingDTO checkingDTO);
}

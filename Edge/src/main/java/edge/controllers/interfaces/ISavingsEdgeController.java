package edge.controllers.interfaces;

import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ISavingsEdgeController {

    public List<SavingsGetDTO> findAllSavings();
    public SavingsGetDTO findById(@PathVariable Long id);
    public List <SavingsGetDTO> findSavingsByUserId(Long id);
    public void create(@RequestBody @Valid SavingsDTO savingsDTO);
}

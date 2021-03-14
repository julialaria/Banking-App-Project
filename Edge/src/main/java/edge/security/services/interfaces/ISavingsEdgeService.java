package edge.security.services.interfaces;

import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;

import java.util.List;

public interface ISavingsEdgeService {

    public List<SavingsGetDTO> findAll();

    public SavingsGetDTO findById(Long id);

    public List <SavingsGetDTO> findSavingsByUserId(Long id);

    public void create(SavingsDTO savingsDTO);
}

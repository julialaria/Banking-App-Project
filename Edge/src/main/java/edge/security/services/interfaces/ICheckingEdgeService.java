package edge.security.services.interfaces;

import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;

import java.util.List;

public interface ICheckingEdgeService {

    public List<CheckingGetDTO> findAll();
    public CheckingGetDTO findById(Long id);
    public List <CheckingGetDTO> findCheckingByUserId (Long id);
    public void create(CheckingDTO checkingDTO);


}

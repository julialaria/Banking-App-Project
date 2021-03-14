package edge.security.services.interfaces;

import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;

import java.util.List;

public interface ICreditCardEdgeService {

    public List<CreditCardGetDTO> findAll();
    public CreditCardGetDTO findById(Long id);
    public List <CreditCardGetDTO> findCreditCardByUserId(Long id);
    public void create(CreditCardDTO creditCardDTO);
}

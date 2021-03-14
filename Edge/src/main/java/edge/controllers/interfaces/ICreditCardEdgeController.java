package edge.controllers.interfaces;

import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ICreditCardEdgeController {

    public List<CreditCardGetDTO> findAllCreditCard();
    public CreditCardGetDTO findById(Long id);

    public List <CreditCardGetDTO> findCreditCardByUserId(Long id);

    public void create(CreditCardDTO creditCardDTO);
}

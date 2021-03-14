package edge.controllers.interfaces;

import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ICheckingEdgeController {


    public List<CheckingGetDTO> findAllChecking();

    public CheckingGetDTO findById(Long id);

    public List <CheckingGetDTO> findCheckingByUserId (Long id);

    public void create(CheckingDTO checkingDTO);
}

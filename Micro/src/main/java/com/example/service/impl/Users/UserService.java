package com.example.service.impl.Users;


import com.example.model.others.User;
import com.example.repository.others.UserRepository;
import com.example.service.interfaces.Users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found");
        }
        return userRepository.findById(id).get();
    }

    /*@Override
    public AccountHolder store (UserDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setPrimaryAddress(accountHolderDTO.getPrimaryAddress());
       if(accountHolderDTO.getMailingAddress()==null){

       }else{
           accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
       }
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setUsername(accountHolderDTO.getUsername());
        accountHolder.setPassword(accountHolderDTO.getPassword());
        userRepository.save(accountHolder);
        Role role = new Role("ACCOUNTHOLDER", accountHolder);
        roleRepository.save(role);
        return accountHolderRepository.save(accountHolder);
    }*/
}

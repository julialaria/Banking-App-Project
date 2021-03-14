package com.example.service.interfaces.Users;



import com.example.model.others.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    public User findById(Long id);

    //AccountHolder store(UserDTO accountHolderDTO);
}

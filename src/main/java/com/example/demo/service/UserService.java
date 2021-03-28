package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponce;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public ApiResponce addUser(User user){


        boolean usernameAndEmail = userRepository.existsByUsernameAndEmail(user.getUsername(), user.getEmail());


        if (usernameAndEmail){

            return new ApiResponce("Such User already exist",false);

        }

       userRepository.save(user);

        return new ApiResponce("User added",true);

    }

    public ApiResponce editUser(Integer id, User user){

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){

            return new ApiResponce("Such User not found",false);

        }


        boolean usernameAndEmailAndIdNot = userRepository.existsByUsernameAndEmailAndIdNot(user.getUsername(), user.getEmail(), id);

        if (usernameAndEmailAndIdNot){
            return new ApiResponce("Such User already exist",false);
        }

        User user1 = optionalUser.get();

        user1.setLastName(user.getLastName());
        user1.setFirstName(user.getFirstName());
        user1.setEmail(user.getEmail());
        user1.setUsername(user.getUsername());

        userRepository.save(user1);

        return new ApiResponce("User edited",true);
    }


    public List<User> getUsers(){

        List<User> userList = userRepository.findAll();

        return userList;

    }

    public User getUser(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){

            return optionalUser.get();

        }else {

            return new User();

        }


    }

    public ApiResponce deleteUser(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){

            userRepository.deleteById(id);

            return new ApiResponce("User deleted",true);

        }else {

            return new ApiResponce("Such User not found",false);

        }

    }

}

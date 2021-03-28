package com.example.demo.service;

import com.example.demo.entity.Problem;
import com.example.demo.entity.User;
import com.example.demo.entity.UserPractice;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.UserPracticeDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.UserPracticeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPracticeService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    ProblemRepository problemRepository;
    @Autowired
    UserPracticeRepository userPracticeRepository;



    public ApiResponce addUserPractice(UserPracticeDto userPracticeDto){


        Optional<User> optionalUser = userRepository.findById(userPracticeDto.getUserId());

        if (!optionalUser.isPresent()){

            return new ApiResponce("Such user not found",false);

        }

        Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDto.getProblemId());

        if (!optionalProblem.isPresent()){

            return new ApiResponce("Such Problem not found",false);

        }


        UserPractice userPractice = new UserPractice();

        userPractice.setDate(userPracticeDto.getDate());
        userPractice.setUserSolution(userPracticeDto.getUserSolution());
        userPractice.setScore(userPracticeDto.getScore());
        userPractice.setProblem(optionalProblem.get());
        userPractice.setUser(optionalUser.get());

        userPracticeRepository.save(userPractice);

        return new ApiResponce("User Practice added",true);


    }

    public ApiResponce editUserPractice(Integer id, UserPracticeDto userPracticeDto){


        Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);

        if (!optionalUserPractice.isPresent()){

            return new ApiResponce("UserPractice not found",false);

        }


        Optional<User> optionalUser = userRepository.findById(userPracticeDto.getUserId());

        if (!optionalUser.isPresent()){

            return new ApiResponce("Such user not found",false);

        }

        Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDto.getProblemId());

        if (!optionalProblem.isPresent()){

            return new ApiResponce("Such Problem not found",false);

        }

        UserPractice userPractice = optionalUserPractice.get();

        userPractice.setDate(userPracticeDto.getDate());
        userPractice.setUserSolution(userPracticeDto.getUserSolution());
        userPractice.setScore(userPracticeDto.getScore());
        userPractice.setProblem(optionalProblem.get());
        userPractice.setUser(optionalUser.get());

        userPracticeRepository.save(userPractice);

        return new ApiResponce("User Practice edited",true);


    }

    public List<UserPractice> getUserPractices(){

        List<UserPractice> practices = userPracticeRepository.findAll();

        return practices;
    }

    public UserPractice getUserPractice(Integer id){

        Optional<UserPractice> practice = userPracticeRepository.findById(id);

        if (practice.isPresent()){

            return practice.get();

        }else {

            return new UserPractice();

        }

    }

    public ApiResponce deleteUserPractice(Integer id){

        Optional<UserPractice> userPracticeOptional = userPracticeRepository.findById(id);

        if (userPracticeOptional.isPresent()){

            userPracticeRepository.deleteById(id);
            return new ApiResponce("User Practice deleted ",true);
        }else {

            return new ApiResponce("Such UserPractice not found",false);
        }

    }










}

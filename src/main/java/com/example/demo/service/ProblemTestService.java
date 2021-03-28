package com.example.demo.service;


import com.example.demo.entity.Problem;
import com.example.demo.entity.ProblemTest;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.ProblemDto;
import com.example.demo.payload.ProblemTestDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.ProblemTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTestService {

    @Autowired
    ProblemTestRepository problemTestRepository;
    @Autowired
    ProblemRepository problemRepository;


    public ApiResponce addProblemTest(ProblemTestDto problemTestDto){


        Optional<Problem> optionalProblem = problemRepository.findById(problemTestDto.getProblemId());

        if (!optionalProblem.isPresent()){

            return new ApiResponce("Such problem not found",false);

        }

        ProblemTest problemTest = new ProblemTest();

        problemTest.setProblem(optionalProblem.get());
        problemTest.setArguments(problemTestDto.getArguments());
        problemTest.setResult(problemTestDto.getResult());

        problemTestRepository.save(problemTest);

        return new ApiResponce("Problem test added",true);

    }

    public ApiResponce editProductTest(Integer id, ProblemTestDto problemTestDto){

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if (!optionalProblemTest.isPresent()){

            return new ApiResponce("Such Problem test not found",false);

        }

        Optional<Problem> optionalProblem = problemRepository.findById(problemTestDto.getProblemId());

        if (!optionalProblem.isPresent()){

            return new ApiResponce("Such problem not found",false);

        }

        ProblemTest problemTest = optionalProblemTest.get();

        problemTest.setProblem(optionalProblem.get());
        problemTest.setArguments(problemTestDto.getArguments());
        problemTest.setResult(problemTestDto.getResult());

        problemTestRepository.save(problemTest);

        return new ApiResponce("Problem test edited",true);



    }


    public List<ProblemTest> getProblemTests(){

        List<ProblemTest> problemTestList = problemTestRepository.findAll();

        return problemTestList;

    }

    public ProblemTest getProblemTest(Integer id){

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if (optionalProblemTest.isPresent()){

            return optionalProblemTest.get();

        }else {

            return new ProblemTest();
        }

    }

    public ApiResponce deleteProblemTest(Integer id){

        Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);

        if (optionalProblemTest.isPresent()){

            problemTestRepository.deleteById(id);

            return new ApiResponce("Problem test deleted",true);

        }else {

            return new ApiResponce("Such problem test not found",false);

        }


    }

}

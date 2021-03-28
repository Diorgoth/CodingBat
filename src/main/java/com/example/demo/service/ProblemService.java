package com.example.demo.service;

import com.example.demo.entity.Problem;
import com.example.demo.entity.Section;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.ProblemDto;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    ProblemRepository problemRepository;
    @Autowired
    SectionRepository sectionRepository;

    public ApiResponce addProblem(ProblemDto problemDto){

        boolean existsByTitle = problemRepository.existsByTitle(problemDto.getTitle());

        if (existsByTitle){

            return new ApiResponce("Such problem already exist",false);

        }

        Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());

        if (!optionalSection.isPresent()){

            return new ApiResponce("Such Section not found",false);

        }

        Problem problem = new Problem();

        problem.setProblem(problemDto.getProblem());
        problem.setBody(problemDto.getBody());
        problem.setSection(optionalSection.get());
        problem.setTitle(problemDto.getTitle());
        problem.setSolution(problemDto.getSolution());

        problemRepository.save(problem);

        return new ApiResponce("Problem added",true);


    }

    public ApiResponce editProblem(Integer id, ProblemDto problemDto){


        Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (!optionalProblem.isPresent()){

            return new ApiResponce("Such problem not found",false);

        }

        boolean titleAndIdNot = problemRepository.existsByTitleAndIdNot(problemDto.getTitle(), id);

        if (titleAndIdNot){

            return new ApiResponce("Such Problem already exist",false);

        }

        Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());

        if (!optionalSection.isPresent()){

            return new ApiResponce("Such Section not found",false);

        }

        Problem problem =  optionalProblem.get();

        problem.setProblem(problemDto.getProblem());
        problem.setBody(problemDto.getBody());
        problem.setSection(optionalSection.get());
        problem.setTitle(problemDto.getTitle());
        problem.setSolution(problemDto.getSolution());

        problemRepository.save(problem);

        return new ApiResponce("Problem edited",true);


    }

    public List<Problem> getProblems(){

        List<Problem> problemList = problemRepository.findAll();

        return problemList;

    }

    public Problem getProblem(Integer id){


        Optional<Problem> optionalProblem = problemRepository.findById(id);

        if (optionalProblem.isPresent()){

            return optionalProblem.get();

        }else {

            return new Problem();
        }


    }

    public ApiResponce deleteProblem(Integer id){


        Optional<Problem> optionalProblem = problemRepository.findById(id);

        if (optionalProblem.isPresent()){

            problemRepository.deleteById(id);

            return new ApiResponce("Problem deleted",true);

        }else {

            return new ApiResponce("Such problem not found",false);

        }

    }

}

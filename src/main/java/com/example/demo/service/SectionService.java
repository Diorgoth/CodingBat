package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Section;
import com.example.demo.payload.ApiResponce;
import com.example.demo.payload.SectionDto;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CourseRepository courseRepository;

    public ApiResponce addSection(SectionDto sectionDto){

        boolean existsByName = sectionRepository.existsByName(sectionDto.getName());

        if (existsByName){

            return new ApiResponce("Such Section already exist",false);
        }

        Optional<Course> optionalCourse = courseRepository.findById(sectionDto.getCourseId());

        if (!optionalCourse.isPresent()){

            return new ApiResponce("Course not found",false);

        }

        Section section = new Section();

        section.setCourse(optionalCourse.get());
        section.setDescription(sectionDto.getDescription());
        section.setName(sectionDto.getName());

        sectionRepository.save(section);

        return new ApiResponce("Section added",true);


    }

    public ApiResponce editSection(Integer id, SectionDto sectionDto){


        Optional<Section> optionalSection = sectionRepository.findById(id);

        if (!optionalSection.isPresent()){

            return new ApiResponce("Such Section not found",false);

        }

        boolean nameAndIdNot = sectionRepository.existsByNameAndIdNot(sectionDto.getName(), id);

        if (nameAndIdNot){

            return new ApiResponce("Such Section already exist",false);

        }

        Optional<Course> optionalCourse = courseRepository.findById(sectionDto.getCourseId());

        if (!optionalCourse.isPresent()){

            return new ApiResponce("Course not found",false);

        }

        Section section = optionalSection.get();

        section.setCourse(optionalCourse.get());
        section.setDescription(sectionDto.getDescription());
        section.setName(sectionDto.getName());

        sectionRepository.save(section);

        return new ApiResponce("Section edited",true);



    }


    public List<Section> getSections(){

        List<Section> sectionList = sectionRepository.findAll();

        return sectionList;

    }

    public Section getSection(Integer id){

        Optional<Section> optionalSection = sectionRepository.findById(id);

        if (optionalSection.isPresent()){

            return optionalSection.get();

        }else {
            return new Section();
        }
    }

    public ApiResponce deleteSection(Integer id){

        Optional<Section> optionalSection = sectionRepository.findById(id);

        if (optionalSection.isPresent()){

            sectionRepository.deleteById(id);
            return new ApiResponce("Section deleted",true);

        }else {

            return new ApiResponce("Such section not found or already deleted",false);

        }

    }
}

package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.payload.ApiResponce;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;


    public ApiResponce addCourse(Course course){

        boolean existsByName = courseRepository.existsByName(course.getName());

        if (existsByName){

            return new ApiResponce("Such Course already exist",false);

        }

        courseRepository.save(course);

        return new ApiResponce("Course added",true);

    }


    public ApiResponce editCourse(Integer id, Course course){


        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (!optionalCourse.isPresent()){

            return new ApiResponce("Such Course not found",false);

        }




        boolean nameAndIdNot = courseRepository.existsByNameAndIdNot(course.getName(), id);
        if (nameAndIdNot){

            return new ApiResponce("Such Course already exist",false);

        }

        Course course1 = optionalCourse.get();
        course1.setDescription(course.getDescription());
        course1.setName(course.getName());

        courseRepository.save(course1);

        return new ApiResponce("Course edited",true);



    }


   public List<Course> getCourses(){

       List<Course> courseList = courseRepository.findAll();

       return courseList;

   }

   public Course getCourse(Integer id){

       Optional<Course> optionalCourse = courseRepository.findById(id);

       if (optionalCourse.isPresent()){

           return optionalCourse.get();

       }else {

           return new Course();
       }

   }

   public ApiResponce deleteCourse(Integer id){

       Optional<Course> optionalCourse = courseRepository.findById(id);

       if (optionalCourse.isPresent()){

           courseRepository.deleteById(id);

           return new ApiResponce("Course deleted",true);

       }else {

           return new ApiResponce("Such Course not found or already deleted",false);
       }

   }

}

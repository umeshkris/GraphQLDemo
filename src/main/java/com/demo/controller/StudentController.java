package com.demo.controller;

import com.demo.model.Branch;
import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

   private final  StudentService studentService;

   StudentController(StudentService studentService)
   {
       this.studentService =studentService;
   }

@QueryMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }
    @QueryMapping
    public Optional<Student> findOne(Integer id)
    {
        return studentService.findOne(id);
    }
    @QueryMapping
    public List<Student> searchStudent(@Argument Integer id, @Argument String name)
    {
        return studentService.searchStudent(id,name);
    }

    @MutationMapping
    public Student addStudent(@Argument String name, @Argument Branch branch)
    {
        return studentService.addStudent(name,branch);

    }

    @MutationMapping
    public Student update(@Argument  Integer id , @Argument String name, @Argument Branch branch)
    {
        return studentService.update(id ,name,branch);

    }

    @MutationMapping
    public Student delete(@Argument  Integer id )
    {
        return studentService.delete(id );

    }


}

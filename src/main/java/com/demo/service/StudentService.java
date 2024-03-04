package com.demo.service;

import com.demo.model.Branch;
import com.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {


    List<Student> students = new ArrayList<>();

    AtomicInteger atomicInteger = new AtomicInteger();

    @PostConstruct
    private void init(){

        students.add(new Student(atomicInteger.getAndIncrement(),"Tom" , Branch.CSC));
        students.add(new Student(atomicInteger.getAndIncrement(),"Allan" , Branch.ECE));
        students.add(new Student(atomicInteger.getAndIncrement(),"Alex" , Branch.EEE));
        students.add(new Student(atomicInteger.getAndIncrement(),"Chi" , Branch.AI));
        students.add(new Student(atomicInteger.getAndIncrement(),"Kyle" , Branch.ML));

    }


    public List<Student> findAll ()
    {
        return students;
    }

    public Optional<Student> findOne(Integer id){

        return students.stream().filter(student ->  student.id().equals(id)).findFirst();
    }

    public List<Student> searchStudent(Integer id ,String name)
    {
        return students.stream().filter(student ->
                                   student.id().equals(id)
                                || student.name().equalsIgnoreCase(name)
                                || student.name().startsWith(name))
                        .collect(Collectors.toList());
    }

    public Student addStudent(String name, Branch branch){
    Student st = new Student(atomicInteger.getAndIncrement(),name , branch);
    students.add(st);
    return  st;
    }

    public Student update(Integer id ,String name, Branch branch)
    {

       Student updatedStudent = new Student(id,name, branch);

        Optional<Student> optional = students.stream().filter(student -> student.id().
                            equals(updatedStudent.id())).findFirst();

        if(optional.isPresent()) {
            Student stu = optional.get();

            int index = students.indexOf(stu);
            students.set(index,updatedStudent);

        }else{
            throw new RuntimeException("No student found with id :" + id );
        }

        return updatedStudent;
    }

    public Student delete (Integer id) {

         Student student = students.stream().filter(student1 ->
                student1.id().equals(id)).findFirst().
                orElseThrow(()->new IllegalArgumentException("Student not found"));
         students.remove(student);


        return student;
    }


}

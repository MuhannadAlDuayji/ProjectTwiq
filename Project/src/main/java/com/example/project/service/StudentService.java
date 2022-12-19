package com.example.project.service;


import com.example.project.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentService {
    ArrayList<Student> students= new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void add(Student student) {
        students.add(student);
    }

    public boolean isUpdated(Integer id, Student student) {
        int index = idHere(id);
        if( index == -1)
            return false;
        students.set(index,student);
        return true;
    }

    public int idHere(Integer id) {
        for(int i=0; i<students.size();i++)
            if(students.get(i).getId()==id)
                return i;
        return -1;
    }

    public boolean isDeleted(Integer id) {
        int index= idHere(id);
        if(checkIndex(index))
            return false;
        students.remove(index);
        return true;
    }

    public Student whoStudent(int index) {
        return students.get(index);
    }

    public int nameHere(String name) {

        return IntStream.range(0, students.size())
                .filter(userInd-> students.get(userInd).getName().equals(name))
                .findFirst()
                .getAsInt();
    }

    public ArrayList<Student> getMajorStudents(String major) {

        return students.stream()
                .filter(student -> student.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> getAgeStudents(Integer age) {

        return students.stream()
                .filter(student -> student.getAge().intValue() >= age.intValue())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean checkIndex(int index){

        if( index <= -1 || index >= students.size())
            return false;

        return true;
    }
}
package com.example.project.service;

import com.example.project.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers= new ArrayList<>();
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }
    public boolean isUpdated(Integer id, Teacher teacher) {
        int index = idHere(id);

        teachers.set(index,teacher);
        return true;
    }

    public int idHere(Integer id) {

        return IntStream.range(0, teachers.size())
                .filter(userInd-> teachers.get(userInd).getId().intValue() == id.intValue())
                .findFirst()
                .getAsInt();
    }

    public boolean isDeleted(Integer id) {
        int index= idHere(id);
        if(checkIndex(index))
            return false;
        teachers.remove(index);
        return true;
    }
    public Teacher whoTeacher(int index) {
        return teachers.get(index);
    }
    public ArrayList<Teacher> getSalaryTeachers(Double salary) {

        ArrayList<Teacher> salaries = new ArrayList<>();

        salaries = teachers.stream()
                .filter(e -> e.getSalary() >= salary)
                .collect(Collectors.toCollection(ArrayList::new));

        return salaries;
    }

    private boolean checkIndex(int index){

        if( index <= -1 || index >= teachers.size())
            return false;

        return true;
    }

}
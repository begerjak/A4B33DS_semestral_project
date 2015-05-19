package controller.dao;


import model.db_schema.StudentsEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface StudentDAO {
    public int insertStudent(StudentsEntity studentsEntity);

    public boolean deleteStudent(StudentsEntity studentsEntity);

    public StudentsEntity getStudent(int id);

    public boolean updateStudent(StudentsEntity studentsEntity);

    public StudentsEntity findStudent(String email);

    public ArrayList<StudentsEntity> listAllStudents();
}

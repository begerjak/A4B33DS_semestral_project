package controller;

import controller.dao.SchoolDAO;
import controller.dao.impl.SchoolDAOImpl;
import model.db_schema.SchoolsEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class Main {
    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAOImpl();
//        UsersEntity usr = new UsersEntity("2jakub.begera3@gmail.com", "heslo2", "Jakub2");
//        int id = userDAO.insertUser(usr);
//        userDAO.insertUser(new UsersEntity("tomas.marny@fit.cvut.cz", "heslo", "Tomas Marny"));
//        userDAO.insertUser(new UsersEntity("2nejakyEmail2", "heslo", "jmeno2"));
//        userDAO.insertUser(new UsersEntity("2nejakyEmail3", "heslo", "jmeno3"));
//        UsersEntity user = userDAO.getUser(0);
//   userDO     System.out.println(user.toString());

//        userDAO.deleteUser(userDAO.getUser(0));
//        UsersEntity kuba = userDAO.getUser(160);
//        System.out.println(kuba.toString());
//        kuba.setEmail("moravja88888@fel.cvut.cz");
//        userDAO.updateUser(kuba);

//        ArrayList<UsersEntity> users = userDAO.listAllUsers();
//        for (UsersEntity user : users){
//            System.out.println(user.toString());
//        }

//        Timestamp ts1 = new Timestamp(new Long("4653343564"));
//        Timestamp ts2 = new Timestamp(new Long("4653343564423"));
//        StudentsEntity student = new StudentsEntity(206, ts1, ts2, 27);
//        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.deleteStudent(student);
//
//        ArrayList<StudentsEntity> students = studentDAO.listAllStudents();
//        for (StudentsEntity s : students){
//            System.out.println(s.toString());
//        }


//        CountryDAO countryDAO = new CountryDAOImpl();
//
//        countryDAO.deleteCountry(countryDAO.getCountry(51));
//        countryDAO.deleteCountry(countryDAO.getCountry(52));
//
//        ArrayList<CountriesEntity> students = countryDAO.listAllCountries();
//        for (CountriesEntity ce : students){
//            System.out.println(ce.toString());
//        }

//        GroupDAO groupDAO = new GroupDAOImpl();
//        GroupsEntity ge = groupDAO.getGroup(65);
//        ge.getSchoolsBySchoolId().getCountriesByCountryId().setCountryName("horni dolni");
//        groupDAO.updateGroup(ge);
//        System.out.println(ge);

        SchoolDAO schoolDAO = new SchoolDAOImpl();
        ArrayList<SchoolsEntity> schoolsEntities = schoolDAO.listAllSchools();
        for (SchoolsEntity schoolsEntity : schoolsEntities){
            System.out.println(schoolsEntity.toString());
        }

    }
}

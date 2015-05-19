package controller;

import controller.dao.StudentDAO;
import controller.dao.UserDAO;
import controller.dao.impl.StudentDAOImpl;
import controller.dao.impl.UserDAOImpl;
import model.db_schema.Students;
import model.db_schema.User;

import java.sql.Timestamp;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
//        User usr = new User("2jakub.begera3@gmail.com", "heslo2", "Jakub2");
//        int id = userDAO.insertUser(usr);
//        userDAO.insertUser(new User("2nejakyEmail", "heslo", "jmeno"));
//        userDAO.insertUser(new User("2nejakyEmail2", "heslo", "jmeno2"));
//        userDAO.insertUser(new User("2nejakyEmail3", "heslo", "jmeno3"));
//        User user = userDAO.getUser(0);
//        System.out.println(user.toString());

        User kuba = userDAO.findUser("moravja8@fel.cvut.cz");
        System.out.println(kuba.toString());
//        kuba.setEmail("moravja88888@fel.cvut.cz");
//        userDAO.updateUser(kuba);

//        ArrayList<User> users = userDAO.listAllUsers();
//        for (User user : users){
//            System.out.println(user.toString());
//        }

        Timestamp ts1 = new Timestamp(new Long("4653343564"));
        Timestamp ts2 = new Timestamp(new Long("4653343564423"));
        Students student = new Students(ts1, ts2, kuba);
        StudentDAO studentDAO = new StudentDAOImpl();
        studentDAO.insertStudent(student);

        HibernateUtil.getOpenSession().close();
    }
}

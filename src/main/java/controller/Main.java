package controller;

import controller.dao.UserDAO;
import controller.dao.impl.UserDAOImpl;
import model.db_schema.UsersEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
//        UsersEntity usr = new UsersEntity("2jakub.begera3@gmail.com", "heslo2", "Jakub2");
//        int id = userDAO.insertUser(usr);
//        userDAO.insertUser(new UsersEntity("tomas.marny@fit.cvut.cz", "heslo", "Tomas Marny"));
//        userDAO.insertUser(new UsersEntity("2nejakyEmail2", "heslo", "jmeno2"));
//        userDAO.insertUser(new UsersEntity("2nejakyEmail3", "heslo", "jmeno3"));
//        UsersEntity user = userDAO.getUser(0);
//   userDO     System.out.println(user.toString());

//        userDAO.deleteUser(userDAO.getUser(0));
//        UsersEntity kuba = userDAO.findUser("2nejakyEmail2");
//        System.out.println(kuba.toString());
//        kuba.setEmail("moravja88888@fel.cvut.cz");
//        userDAO.updateUser(kuba);

        ArrayList<UsersEntity> users = userDAO.listAllUsers();
        for (UsersEntity user : users){
            System.out.println(user.toString());
        }

//        Timestamp ts1 = new Timestamp(new Long("4653343564"));
//        Timestamp ts2 = new Timestamp(new Long("4653343564423"));
//        Students student = new Students(ts1, ts2, kuba);
//        StudentDAO studentDAO = new StudentDAOImpl();
//        studentDAO.insertStudent(student);

//        HibernateUtil.getOpenSession().close();
    }
}

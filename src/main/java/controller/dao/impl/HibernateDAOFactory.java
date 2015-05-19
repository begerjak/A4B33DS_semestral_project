package controller.dao.impl;

import controller.HibernateUtil;
import controller.dao.*;
import org.hibernate.Session;

import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class HibernateDAOFactory extends DAOFactory{
    private final static Logger LOGGER = Logger.getLogger(HibernateDAOFactory.class.getName());

    public static Session createSession() {
        return HibernateUtil.getOpenSession();
    }


    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new StudentDAOImpl();
    }

    @Override
    public SchoolDAO getSchoolDAO() {
        return new SchoolDAOImpl();
    }

    @Override
    public GroupDAO getGroupDAO() {
        return new GroupDAOImpl();
    }

    @Override
    public CountryDAO getCountryDAO() {
        return new CountryDAOImpl();
    }
}

package controller.dao;

import controller.HibernateUtil;
import org.hibernate.Session;

import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class HibernateDAOFactory {
    private final static Logger LOGGER = Logger.getLogger(HibernateDAOFactory.class.getName());

    public static Session createSession() {
        return HibernateUtil.getOpenSession();
    }


}

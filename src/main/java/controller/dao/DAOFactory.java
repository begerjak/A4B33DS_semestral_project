package controller.dao;

import controller.dao.impl.HibernateDAOFactory;

import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public abstract class DAOFactory {
    private final static Logger LOGGER = Logger.getLogger(DAOFactory.class.getName());

    public static final int HIBERNATE = 1;

    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case DAOFactory.HIBERNATE:
                return new HibernateDAOFactory();
            default:
                return null;
        }
    }
}

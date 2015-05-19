package controller.dao;

import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public abstract class BaseDAO {
    protected Logger LOGGER;

    public BaseDAO() {
        LOGGER = Logger.getLogger(BaseDAO.class.getName());
    }
}

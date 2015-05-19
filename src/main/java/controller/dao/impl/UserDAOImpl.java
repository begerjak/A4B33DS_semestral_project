package controller.dao.impl;

import controller.dao.BaseDAO;
import controller.dao.UserDAO;
import model.db_schema.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    public UserDAOImpl() {
        LOGGER = Logger.getLogger(UserDAOImpl.class.getName());
    }

    public int insertUser(UsersEntity usersEntity) {
        if (usersEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");

        try {
            LOGGER.info(String.format("Inserting new UsersEntity '%s'", usersEntity.getName()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.save(usersEntity);
            tx.commit();
            return usersEntity.getUserId();
        } catch (Exception ex) {
            throw new PersistenceException("insertPerson failed", ex);
        }
    }

    public boolean deleteUser(UsersEntity usersEntity) {
        if (usersEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Deleting UsersEntity with Id %d", usersEntity.getUserId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.delete(usersEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("deleteUser failed", ex);
        }
    }

    public UsersEntity getUser(int id) {
        try {
            LOGGER.info(String.format("Getting UsersEntity with Id %d", id));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            UsersEntity person = (UsersEntity) session.get(UsersEntity.class, id);
            tx.commit();
            return person;
        } catch (Exception ex) {
            throw new PersistenceException("getUser failed", ex);
        }
    }



    public boolean updateUser(UsersEntity usersEntity) {
        if (usersEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Updating UsersEntity with Id %d", usersEntity.getUserId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.update(usersEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("updateUser failed", ex);
        }
    }

    public UsersEntity findUser(String email) {
        try {
            LOGGER.info(String.format("Finding UsersEntity with email '%s'", email));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(UsersEntity.class);
            crit.add(Restrictions.eq("email", email));
            List users = crit.list();
            UsersEntity foundUsers = null;
            if (!users.isEmpty())
                foundUsers = (UsersEntity) users.get(0);
            return foundUsers;
        } catch (Exception ex) {
            throw new PersistenceException("findUser failed", ex);
        }
    }

    public ArrayList<UsersEntity> listAllUsers() {
        try {
            LOGGER.info(String.format("List all usersEntities"));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(UsersEntity.class);
            ArrayList<UsersEntity> usersEntities = (ArrayList<UsersEntity>)crit.list();
            return usersEntities;
        } catch (Exception ex) {
            throw new PersistenceException("findUser failed", ex);
        }
    }
}

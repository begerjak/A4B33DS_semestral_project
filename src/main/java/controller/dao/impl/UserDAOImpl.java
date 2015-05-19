package controller.dao.impl;

import controller.dao.BaseDAO;
import controller.dao.UserDAO;
import model.db_schema.User;
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

    public int insertUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("Input parameter cannot be null");

        try {
            LOGGER.info(String.format("Inserting new User '%s'", user.getName()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return user.getUserId();
        } catch (Exception ex) {
            throw new PersistenceException("insertPerson failed", ex);
        }
    }

    public boolean deleteUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Deleting User with Id %d", user.getUserId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("deleteUser failed", ex);
        }
    }

    public User getUser(int id) {
        try {
            LOGGER.info(String.format("Getting User with Id %d", id));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            User person = (User) session.get(User.class, id);
            tx.commit();
            return person;
        } catch (Exception ex) {
            throw new PersistenceException("getUser failed", ex);
        }
    }



    public boolean updateUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Updating User with Id %d", user.getUserId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("updateUser failed", ex);
        }
    }

    public User findUser(String email) {
        try {
            LOGGER.info(String.format("Finding User with email '%s'", email));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.eq("email", email));
            List users = crit.list();
            User foundUsers = null;
            if (!users.isEmpty())
                foundUsers = (User) users.get(0);
            return foundUsers;
        } catch (Exception ex) {
            throw new PersistenceException("findUser failed", ex);
        }
    }

    public ArrayList<User> listAllUsers() {
        try {
            LOGGER.info(String.format("List all users"));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(User.class);
            ArrayList<User> users = (ArrayList<User>)crit.list();
            return users;
        } catch (Exception ex) {
            throw new PersistenceException("findUser failed", ex);
        }
    }
}

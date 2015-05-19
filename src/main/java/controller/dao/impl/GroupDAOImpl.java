package controller.dao.impl;

import controller.dao.BaseDAO;
import controller.dao.GroupDAO;
import model.db_schema.GroupsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class GroupDAOImpl extends BaseDAO implements GroupDAO {

    public GroupDAOImpl() {
        LOGGER = Logger.getLogger(GroupDAOImpl.class.getName());
    }

    public int insertGroup(GroupsEntity groupsEntity) {
        if (groupsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");

        try {
            LOGGER.info(String.format("Inserting new groupEntity '%d'", groupsEntity.getNumber()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.save(groupsEntity);
            tx.commit();
            return groupsEntity.getGroupId();
        } catch (Exception ex) {
            throw new PersistenceException("insertGroup failed", ex);
        }
    }

    public boolean deleteGroup(GroupsEntity groupsEntity) {
        if (groupsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Deleting groupsEntity with Id %d", groupsEntity.getGroupId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.delete(groupsEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("deleteGroup failed", ex);
        }
    }

    public GroupsEntity getGroup(int id) {
        try {
            LOGGER.info(String.format("Getting GroupsEntity with Id %d", id));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            GroupsEntity group = (GroupsEntity) session.get(GroupsEntity.class, id);
            tx.commit();
            return group;
        } catch (Exception ex) {
            throw new PersistenceException("getGroup failed", ex);
        }
    }



    public boolean updateGroup(GroupsEntity groupsEntity) {
        if (groupsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Updating groupsEntity with Id %d", groupsEntity.getGroupId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.update(groupsEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("updateGroup failed", ex);
        }
    }


    public ArrayList<GroupsEntity> listAllGroups() {
        try {
            LOGGER.info(String.format("List all usersEntities"));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(GroupsEntity.class);
            ArrayList<GroupsEntity> groupsEntities = (ArrayList<GroupsEntity>)crit.list();
            return groupsEntities;
        } catch (Exception ex) {
            throw new PersistenceException("findUser failed", ex);
        }
    }
}

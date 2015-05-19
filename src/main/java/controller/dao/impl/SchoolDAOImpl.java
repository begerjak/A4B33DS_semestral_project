package controller.dao.impl;

import controller.dao.BaseDAO;
import controller.dao.SchoolDAO;
import model.db_schema.SchoolsEntity;
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
public class SchoolDAOImpl extends BaseDAO implements SchoolDAO {

    public SchoolDAOImpl() {
        LOGGER = Logger.getLogger(SchoolDAOImpl.class.getName());
    }

    public int insertSchool(SchoolsEntity schoolsEntity) {
        if (schoolsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");

        try {
            LOGGER.info(String.format("Inserting new schoolsEntity '%s'", schoolsEntity.getSchoolName()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.save(schoolsEntity);
            tx.commit();
            return schoolsEntity.getSchoolId();
        } catch (Exception ex) {
            throw new PersistenceException("insertSchool failed", ex);
        }
    }

    public boolean deleteSchool(SchoolsEntity schoolsEntity) {
        if (schoolsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Deleting schoolsEntity with Id %d", schoolsEntity.getSchoolId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.delete(schoolsEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("deleteSchool failed", ex);
        }
    }

    public SchoolsEntity getSchool(int id) {
        try {
            LOGGER.info(String.format("Getting schoolsEntity with Id %d", id));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            SchoolsEntity schoolsEntity = (SchoolsEntity) session.get(SchoolsEntity.class, id);
            tx.commit();
            return schoolsEntity;
        } catch (Exception ex) {
            throw new PersistenceException("getSchool failed", ex);
        }
    }



    public boolean updateSchool(SchoolsEntity schoolsEntity) {
        if (schoolsEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Updating schoolsEntity with Id %d", schoolsEntity.getSchoolId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.update(schoolsEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("updateSchool failed", ex);
        }
    }


    public ArrayList<SchoolsEntity> listAllSchools() {
        try {
            LOGGER.info(String.format("List all schoolsEntities"));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(SchoolsEntity.class);
            ArrayList<SchoolsEntity> usersEntities = (ArrayList<SchoolsEntity>)crit.list();
            return usersEntities;
        } catch (Exception ex) {
            throw new PersistenceException("listAllSchools failed", ex);
        }
    }
}

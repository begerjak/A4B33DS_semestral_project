package controller.dao.impl;

import controller.dao.BaseDAO;
import controller.dao.CountryDAO;
import model.db_schema.CountriesEntity;
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
public class CountryDAOImpl extends BaseDAO implements CountryDAO {

    public CountryDAOImpl() {
        LOGGER = Logger.getLogger(CountryDAOImpl.class.getName());
    }

    public int insertCountry(CountriesEntity countriesEntity) {
        if (countriesEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");

        try {
            LOGGER.info(String.format("Inserting new countriesEntity '%s'", countriesEntity.getCountryName()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.save(countriesEntity);
            tx.commit();
            return countriesEntity.getCountryId();
        } catch (Exception ex) {
            throw new PersistenceException("insertCountry failed", ex);
        }
    }

    public boolean deleteCountry(CountriesEntity countriesEntity) {
        if (countriesEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Deleting countriesEntity with Id %d", countriesEntity.getCountryId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.delete(countriesEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("deleteCountry failed", ex);
        }
    }

    public CountriesEntity getCountry(int id) {
        try {
            LOGGER.info(String.format("Getting CountryEntity with Id %d", id));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            CountriesEntity countriesEntity = (CountriesEntity) session.get(CountriesEntity.class, id);
            tx.commit();
            return countriesEntity;
        } catch (Exception ex) {
            throw new PersistenceException("getCountry failed", ex);
        }
    }



    public boolean updateCountry(CountriesEntity countriesEntity) {
        if (countriesEntity == null)
            throw new IllegalArgumentException("Input parameter cannot be null");
        try {
            LOGGER.info(String.format("Updating updateCountry with Id %d", countriesEntity.getCountryId()));
            Session session = HibernateDAOFactory.createSession();
            Transaction tx = session.beginTransaction();
            session.update(countriesEntity);
            tx.commit();
            return true;
        } catch (Exception ex) {
            throw new PersistenceException("updateCountry failed", ex);
        }
    }


    public ArrayList<CountriesEntity> listAllCountries() {
        try {
            LOGGER.info(String.format("List all countries"));
            Session session = HibernateDAOFactory.createSession();

            Criteria crit = session.createCriteria(CountriesEntity.class);
            ArrayList<CountriesEntity> countriesEntity = (ArrayList<CountriesEntity>)crit.list();
            return countriesEntity;
        } catch (Exception ex) {
            throw new PersistenceException("listAllCountries failed", ex);
        }
    }
}

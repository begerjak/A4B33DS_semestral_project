package controller.dao;

import model.db_schema.CountriesEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface CountryDAO {
    public int insertCountry(CountriesEntity countriesEntity);

    public boolean deleteCountry(CountriesEntity countriesEntity);

    public CountriesEntity getCountry(int id);

    public boolean updateCountry(CountriesEntity countriesEntity);

    public ArrayList<CountriesEntity> listAllCountries();
}

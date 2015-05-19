package controller.dao;

import model.db_schema.SchoolsEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface SchoolDAO {
    public int insertSchool(SchoolsEntity schoolsEntity);

    public boolean deleteSchool(SchoolsEntity schoolsEntity);

    public SchoolsEntity getSchool(int id);

    public boolean updateSchool(SchoolsEntity schoolsEntity);

    public ArrayList<SchoolsEntity> listAllSchools();
}

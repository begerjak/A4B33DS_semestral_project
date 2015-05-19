package controller.dao;

import model.db_schema.UsersEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface UserDAO {
    public int insertUser(UsersEntity usersEntity);

    public boolean deleteUser(UsersEntity usersEntity);

    public UsersEntity getUser(int id);

    public boolean updateUser(UsersEntity usersEntity);

    public UsersEntity findUser(String email);

    public ArrayList<UsersEntity> listAllUsers();
}

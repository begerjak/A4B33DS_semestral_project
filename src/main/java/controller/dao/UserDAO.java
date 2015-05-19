package controller.dao;

import model.db_schema.User;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface UserDAO {
    public int insertUser(User user);

    public boolean deleteUser(User user);

    public User getUser(int id);

    public boolean updateUser(User user);

    public User findUser(String email);

    public ArrayList<User> listAllUsers();
}

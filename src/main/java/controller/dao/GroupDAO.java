package controller.dao;

import model.db_schema.GroupsEntity;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public interface GroupDAO {
    public int insertGroup(GroupsEntity groupsEntity);

    public boolean deleteGroup(GroupsEntity groupsEntity);

    public GroupsEntity getGroup(int id);

    public boolean updateGroup(GroupsEntity groupsEntity);

    public ArrayList<GroupsEntity> listAllGroups();
}

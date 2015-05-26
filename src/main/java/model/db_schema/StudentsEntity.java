package model.db_schema;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "students", schema = "semestralka", catalog = "student_db15_25")
public class StudentsEntity {
    private int userId;
    private Timestamp studentFrom;
    private Timestamp studentTo;
    private int groupId;
    private GroupsEntity groupsByGroupId;
    private UsersEntity userByUsersEntityId;

    public StudentsEntity() {
    }

    public StudentsEntity(int userId, Timestamp studentFrom, Timestamp studentTo, int groupId) {
        this.userId = userId;
        this.studentFrom = studentFrom;
        this.studentTo = studentTo;
        this.groupId = groupId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "student_from")
    public Timestamp getStudentFrom() {
        return studentFrom;
    }

    public void setStudentFrom(Timestamp studentFrom) {
        this.studentFrom = studentFrom;
    }

    @Basic
    @Column(name = "student_to")
    public Timestamp getStudentTo() {
        return studentTo;
    }

    public void setStudentTo(Timestamp studentTo) {
        this.studentTo = studentTo;
    }

    @Basic
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (userId != that.userId) return false;
        if (groupId != that.groupId) return false;
        if (studentFrom != null ? !studentFrom.equals(that.studentFrom) : that.studentFrom != null) return false;
        if (studentTo != null ? !studentTo.equals(that.studentTo) : that.studentTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (studentFrom != null ? studentFrom.hashCode() : 0);
        result = 31 * result + (studentTo != null ? studentTo.hashCode() : 0);
        result = 31 * result + groupId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, insertable = false, updatable = false)
    public GroupsEntity getGroupsByGroupId() {
        return groupsByGroupId;
    }

    public void setGroupsByGroupId(GroupsEntity groupsByGroupId) {
        this.groupsByGroupId = groupsByGroupId;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UsersEntity getUserByUsersEntityId() {
        return userByUsersEntityId;
    }

    public void setUserByUsersEntityId(UsersEntity userByUsersEntityId) {
        this.userByUsersEntityId = userByUsersEntityId;
    }

    @Override
    public String toString() {
        return String.format("User id = %s, timeFrom = %s, timeTo = %s, groupId = %d", userId, studentFrom.toString(), studentTo.toString(), groupId);
    }
}

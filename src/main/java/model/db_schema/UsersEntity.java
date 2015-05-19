package model.db_schema;

import javax.persistence.*;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "users", schema = "semestralka", catalog = "student_db15_25")
public class UsersEntity {
    private int userId;
    private String email;
    private String password;
    private String name;
//    private StudentsEntity studentsByUserId;

    public UsersEntity() {
    }

    public UsersEntity(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

//    @Id
//    @Column(name = "user_id")
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("user: id = %d, name = %s, email = %s", userId, name, email);
    }

//        @OneToOne//(mappedBy = "usersByUserId")
//    public StudentsEntity getStudentsByUserId() {
//        return studentsByUserId;
//    }
//
//    public void setStudentsByUserId(StudentsEntity studentsByUserId) {
//        this.studentsByUserId = studentsByUserId;
//    }
}

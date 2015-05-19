package model.db_schema;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "students", schema = "public", catalog = "student_db15_25")
public class StudentsEntity {
    private int userId;
    private Timestamp studentFrom;
    private String studentTo;
    private User usersByUserId;

    @Id
    @Column(name = "user_id")
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
    public String getStudentTo() {
        return studentTo;
    }

    public void setStudentTo(String studentTo) {
        this.studentTo = studentTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (userId != that.userId) return false;
        if (studentFrom != null ? !studentFrom.equals(that.studentFrom) : that.studentFrom != null) return false;
        if (studentTo != null ? !studentTo.equals(that.studentTo) : that.studentTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (studentFrom != null ? studentFrom.hashCode() : 0);
        result = 31 * result + (studentTo != null ? studentTo.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}

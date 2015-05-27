package model.db_schema;

import javax.persistence.*;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "groups", schema = "semestralka", catalog = "student_db15_25")
public class GroupsEntity {
    private int groupId;
    private int number;
    private int schoolId;
    private SchoolsEntity schoolsBySchoolId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", unique = true, nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "school_id")
    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (groupId != that.groupId) return false;
        if (number != that.number) return false;
        if (schoolId != that.schoolId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + number;
        result = 31 * result + schoolId;
        result = 31 * result + (schoolsBySchoolId != null ? schoolsBySchoolId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "school_id", nullable = false, insertable = false, updatable = false)
    public SchoolsEntity getSchoolsBySchoolId() {
        return schoolsBySchoolId;
    }

    public void setSchoolsBySchoolId(SchoolsEntity schoolsBySchoolId) {
        this.schoolsBySchoolId = schoolsBySchoolId;
    }
}

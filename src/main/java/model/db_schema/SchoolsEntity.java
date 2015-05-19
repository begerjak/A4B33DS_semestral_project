package model.db_schema;

import javax.persistence.*;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "schools", schema = "semestralka", catalog = "student_db15_25")
public class SchoolsEntity {
    private int schoolId;
    private String schoolName;
    private String schoolDesc;

    @Id
    @Column(name = "school_id")
    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Basic
    @Column(name = "school_name")
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Basic
    @Column(name = "school_desc")
    public String getSchoolDesc() {
        return schoolDesc;
    }

    public void setSchoolDesc(String schoolDesc) {
        this.schoolDesc = schoolDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolsEntity that = (SchoolsEntity) o;

        if (schoolId != that.schoolId) return false;
        if (schoolName != null ? !schoolName.equals(that.schoolName) : that.schoolName != null) return false;
        if (schoolDesc != null ? !schoolDesc.equals(that.schoolDesc) : that.schoolDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schoolId;
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (schoolDesc != null ? schoolDesc.hashCode() : 0);
        return result;
    }
}

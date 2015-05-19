package model.db_schema;

import javax.persistence.*;
import java.util.Collection;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "schools", schema = "semestralka", catalog = "student_db15_25")
public class SchoolsEntity {
    private int schoolId;
    private int countryId;
    private String schoolName;
    private String schoolDesc;
    private Collection<GroupsEntity> groupsesBySchoolId;
    private CountriesEntity countriesByCountryId;

    @Id
    @Column(name = "school_id")
    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Basic
    @Column(name = "country_id")
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
        if (countryId != that.countryId) return false;
        if (schoolName != null ? !schoolName.equals(that.schoolName) : that.schoolName != null) return false;
        if (schoolDesc != null ? !schoolDesc.equals(that.schoolDesc) : that.schoolDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schoolId;
        result = 31 * result + countryId;
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (schoolDesc != null ? schoolDesc.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "schoolsBySchoolId")
    public Collection<GroupsEntity> getGroupsesBySchoolId() {
        return groupsesBySchoolId;
    }

    public void setGroupsesBySchoolId(Collection<GroupsEntity> groupsesBySchoolId) {
        this.groupsesBySchoolId = groupsesBySchoolId;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = false, updatable = false)
    public CountriesEntity getCountriesByCountryId() {
        return countriesByCountryId;
    }

    public void setCountriesByCountryId(CountriesEntity countriesByCountryId) {
        this.countriesByCountryId = countriesByCountryId;
    }
}

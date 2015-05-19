package model.db_schema;

import javax.persistence.*;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
@Entity
@Table(name = "countries", schema = "semestralka", catalog = "student_db15_25")
public class CountriesEntity {
    private int countryId;
    private String countryName;
//    private Collection<SchoolsEntity> schoolsesByCountryId;

    @Id
    @Column(name = "country_id")
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountriesEntity that = (CountriesEntity) o;

        if (countryId != that.countryId) return false;
        if (countryName != null ? !countryName.equals(that.countryName) : that.countryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryId;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("country: id = %d, name = %s", countryId, countryName);
    }

    //    @OneToMany(mappedBy = "countriesByCountryId")
//    public Collection<SchoolsEntity> getSchoolsesByCountryId() {
//        return schoolsesByCountryId;
//    }
//
//    public void setSchoolsesByCountryId(Collection<SchoolsEntity> schoolsesByCountryId) {
//        this.schoolsesByCountryId = schoolsesByCountryId;
//    }
}

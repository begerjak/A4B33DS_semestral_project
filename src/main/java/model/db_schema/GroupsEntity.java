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
    private SchoolsEntity schoolsBySchoolId;

    @Id
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

        GroupsEntity that = (GroupsEntity) o;

        if (groupId != that.groupId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return groupId;
    }

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "school_id", nullable = false)
    public SchoolsEntity getSchoolsBySchoolId() {
        return schoolsBySchoolId;
    }

    public void setSchoolsBySchoolId(SchoolsEntity schoolsBySchoolId) {
        this.schoolsBySchoolId = schoolsBySchoolId;
    }
}

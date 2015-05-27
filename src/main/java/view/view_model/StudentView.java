package view.view_model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class StudentView {

    private IntegerProperty id;
    private  StringProperty name, email, pass, studFrom, studTo;
    private Date studentFrom, studentTo;


    public StudentView() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.pass = new SimpleStringProperty();
        this.studFrom = new SimpleStringProperty();
        this.studTo = new SimpleStringProperty();
    }

    public StudentView(int id, String name, String email, String pass, String studFrom, String studTo) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.pass = new SimpleStringProperty(pass);
        this.studFrom = new SimpleStringProperty(studFrom);
        this.studTo = new SimpleStringProperty(studTo);

    }

    public IntegerProperty getIdProp() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPass() {
        return pass.get();
    }

    public StringProperty passProperty() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass.set(pass);
    }

    public String getStudFrom() {
        return studFrom.get();
    }

    public StringProperty studFromProperty() {
        return studFrom;
    }

    public void setStudFrom(String studFrom) {
        this.studFrom.set(studFrom);
    }

    public String getStudTo() {
        return studTo.get();
    }

    public StringProperty studToProperty() {
        return studTo;
    }

    public void setStudTo(String studTo) {
        this.studTo.set(studTo);
    }

    public Date getStudentFrom() {
        return studentFrom;
    }

    public void setStudentFrom(Date studentFrom) {
        this.studentFrom = studentFrom;
    }

    public Date getStudentTo() {
        return studentTo;
    }

    public void setStudentTo(Date studentTo) {
        this.studentTo = studentTo;
    }
}

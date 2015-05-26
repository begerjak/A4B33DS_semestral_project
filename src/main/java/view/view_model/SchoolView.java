package view.view_model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.db_schema.CountriesEntity;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class SchoolView {

    private IntegerProperty id;
    private  StringProperty name;
    private  StringProperty desc;
    private  StringProperty country;
    private CountriesEntity countriesEntity;
    boolean init = false;

    public SchoolView() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.desc = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
    }

    public SchoolView(int id, String name, String desc, String country) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.desc = new SimpleStringProperty(desc);;
        this.country = new SimpleStringProperty(country);
        init = true;
    }

    public SchoolView(int id, String name, String desc, CountriesEntity countriesEntity) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.desc = new SimpleStringProperty(desc);
        this.countriesEntity = countriesEntity;
        this.country = new SimpleStringProperty(countriesEntity.getCountryName());
        init = true;
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

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public CountriesEntity getCountriesEntity() {
        return countriesEntity;
    }

    public void setCountriesEntity(CountriesEntity countriesEntity) {
        this.countriesEntity = countriesEntity;
        setCountry(countriesEntity.getCountryName());
    }

    public boolean isInit() {
        return init;
    }
}

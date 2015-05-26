package view;

import controller.dao.impl.CountryDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.db_schema.CountriesEntity;
import view.view_model.SchoolView;

import java.util.ArrayList;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class SchoolDialogController {
    @FXML
    private TextField tflName;
    @FXML
    private TextField tflDesc;
    @FXML
    private ChoiceBox chbCountry;

    private Stage dialogStage;
    private SchoolView schoolView;
    private boolean okClicked;

    @FXML
    private void initialize() {
        ObservableList<CountriesEntity> countryesObservableList = FXCollections.observableArrayList();
        ArrayList<CountriesEntity> countriesEntities = new CountryDAOImpl().listAllCountries();
        for (CountriesEntity countriesEntity : countriesEntities){
            countryesObservableList.addAll(countriesEntity);
        }
        chbCountry.setItems(countryesObservableList);
        if (!chbCountry.getSelectionModel().isEmpty()){
            chbCountry.getSelectionModel().select(0);
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSchoolView(SchoolView schoolView){
        this.schoolView = schoolView;
            tflName.setText(schoolView.getName());
            tflDesc.setText(schoolView.getDesc());
            chbCountry.getSelectionModel().select(schoolView.getCountriesEntity());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        okClicked = true;
        schoolView.setName(tflName.getText());
        schoolView.setDesc(tflDesc.getText());
        schoolView.setCountriesEntity((CountriesEntity) chbCountry.getSelectionModel().getSelectedItem());

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        okClicked = false;
        dialogStage.close();
    }


}

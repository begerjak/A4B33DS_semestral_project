package controller;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import view.VIewController;
import view.view_model.GroupView;
import view.view_model.SchoolView;
import view.view_model.StudentView;

import java.io.IOException;

public class MainView extends Application {

    private Stage primaryStage;
    private ScrollPane rootLayout;
    private VIewController viewController;


    ObservableList<SchoolView> schoolsViewsList = FXCollections.observableArrayList();
    ObservableList<GroupView> groupsViewsList = FXCollections.observableArrayList();
    ObservableList<StudentView> studentsViewsList = FXCollections.observableArrayList();

    public MainView() {
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DS semestral project");

        initRootLayout();



    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VIewController.class.getResource("MainView.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();




            viewController = loader.getController();
            viewController.setMainApp(this);
            viewController.getTbvSchools().setItems(schoolsViewsList);
            viewController.getTbvGroup().setItems(groupsViewsList);
            viewController.getTbvStudent().setItems(studentsViewsList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);


    }

    public ObservableList<SchoolView> getSchoolsViewsList() {
        return schoolsViewsList;
    }

    public ObservableList<GroupView> getGroupsViewsList() {
        return groupsViewsList;
    }

    public ObservableList<StudentView> getStudentsViewsList() {
        return studentsViewsList;
    }
}

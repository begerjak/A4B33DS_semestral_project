package controller;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.GroupDialogController;
import view.SchoolDialogController;
import view.StudentDialogController;
import view.ViewController;
import view.view_model.GroupView;
import view.view_model.SchoolView;
import view.view_model.StudentView;

import java.io.IOException;

public class MainView extends Application {

    ObservableList<SchoolView> schoolsViewsList = FXCollections.observableArrayList();
    ObservableList<GroupView> groupsViewsList = FXCollections.observableArrayList();
    ObservableList<StudentView> studentsViewsList = FXCollections.observableArrayList();
    private Stage primaryStage;
    private ScrollPane rootLayout;
    private ViewController viewController;

    public MainView() {
    }

    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DS semestral project");

        initRootLayout();
        viewController.onClickBtnSchoolsUpdate();


    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewController.class.getResource("MainView.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


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

    public ObservableList<SchoolView> getSchoolsViewsList() {
        return schoolsViewsList;
    }

    public ObservableList<GroupView> getGroupsViewsList() {
        return groupsViewsList;
    }

    public ObservableList<StudentView> getStudentsViewsList() {
        return studentsViewsList;
    }


    public boolean showSchoolDialog(SchoolView schoolView, String title){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewController.class.getResource("SchoolDialogView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the schoolView into the controller.
            SchoolDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSchoolView(schoolView);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean showGroupDialog(GroupView groupView, String title){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewController.class.getResource("GroupDialogView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the schoolView into the controller.
            GroupDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setGroupView(groupView);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showStudentDialog(StudentView studentView, String title){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewController.class.getResource("StudentDialogView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the schoolView into the controller.
            StudentDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStudentView(studentView);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

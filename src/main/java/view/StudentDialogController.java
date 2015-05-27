package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.view_model.StudentView;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class StudentDialogController {
    @FXML
    private TextField tflName;
    @FXML
    private TextField tflEmail;
//    @FXML
//    private TextField tflPass;

    @FXML
    private PasswordField tflPass;

    @FXML
    private DatePicker tflStudentFrom;
    @FXML
    private DatePicker tflStudentTo;

    private Stage dialogStage;
    private StudentView studentView;
    private boolean okClicked;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStudentView(StudentView studentView){
        this.studentView = studentView;
        tflName.setText(studentView.getName());
        tflEmail.setText(studentView.getEmail());
        tflPass.setText(decodeBase64ToPass(studentView.getPass()));
        if (studentView.getStudentFrom() != null) {
            tflStudentFrom.setValue(LocalDate.ofEpochDay(studentView.getStudentFrom().getTime() / (1000 * 3600 * 24)));
        }
        if (studentView.getStudentTo() != null) {
            tflStudentTo.setValue(LocalDate.ofEpochDay(studentView.getStudentTo().getTime() / (1000 * 3600 * 24)));
        }
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        okClicked = true;
        studentView.setName(tflName.getText());
        studentView.setEmail(tflEmail.getText());
        studentView.setPass(encodePassToBase64(tflPass.getText()));
        if (tflStudentFrom.getValue() != null) {
            studentView.setStudentFrom(new Date(tflStudentFrom.getValue().toEpochDay() * 1000 * 3600 * 24));
        }
        if (tflStudentTo.getValue() != null) {
            studentView.setStudentTo(new Date(tflStudentTo.getValue().toEpochDay() * 1000 * 3600 * 24));
        }
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        okClicked = false;
        dialogStage.close();
    }

    public static String encodePassToBase64(String in){
        byte[] enc = Base64.getEncoder().encode(in.getBytes());
        return new String(enc);
    }

    public static String decodeBase64ToPass(String in){
        byte[] enc = Base64.getDecoder().decode(in.getBytes());
        return new String(enc);
    }

}

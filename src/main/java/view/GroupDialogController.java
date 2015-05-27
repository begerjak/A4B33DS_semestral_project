package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.view_model.GroupView;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class GroupDialogController {
    @FXML
    private TextField tflName;

    private Stage dialogStage;
    private GroupView groupView;
    private boolean okClicked;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGroupView(GroupView groupView){
        this.groupView = groupView;
        tflName.setText(groupView.getName());
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        okClicked = true;
        groupView.setName(tflName.getText());
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        okClicked = false;
        dialogStage.close();
    }


}

//package view;
//
//import controller.MainView;
//import controller.dao.impl.SchoolDAOImpl;
//import controller.dao.impl.StudentDAOImpl;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import model.db_schema.GroupsEntity;
//import model.db_schema.SchoolsEntity;
//import model.db_schema.StudentsEntity;
//import view.view_model.GroupView;
//import view.view_model.SchoolView;
//import view.view_model.StudentView;
//
//import java.util.ArrayList;
//
//
///**
// * Copyright 2015 IEAP CTU
// * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
// */
//public class VIewController {
//    MainView mainApp;
//    //**********************************************************************************************************************
////  Table school
////**********************************************************************************************************************
//    SchoolView selectedSchoolView;
//    //**********************************************************************************************************************
////  Table group
////**********************************************************************************************************************
//    GroupView selectedGroupView;
//    //**********************************************************************************************************************
////  Table students
////**********************************************************************************************************************
//    StudentView selestedStudentView;
//    @FXML
//    private TableView<SchoolView> tbvSchools;
//    @FXML
//    private TableColumn<SchoolView, Integer> tbvSchoolsID;
//    @FXML
//    private TableColumn<SchoolView, String> tbvSchoolsName;
//    @FXML
//    private TableColumn<SchoolView, String> tbvSchoolsDecs;
//    @FXML
//    private TableColumn<SchoolView, String> tbvSchoolsCounty;
//    @FXML
//    private Button btnSchoolsUpdate;
//    @FXML
//    private TableView<GroupView> tbvGroup;
//    @FXML
//    private TableColumn<GroupView, Integer> tbvGroupId;
//    @FXML
//    private TableColumn<GroupView, String> tbvGroupName;
//    @FXML
//    private TableView<StudentView> tbvStudent;
//    @FXML
//    private TableColumn<StudentView, Integer> tbvStudentId;
//    @FXML
//    private TableColumn<StudentView, String> tbvStudentName;
//    @FXML
//    private TableColumn<StudentView, String> tbvStudentEmail;
//    @FXML
//    private TableColumn<StudentView, String> tbvStudentPass;
//    @FXML
//    private TableColumn<StudentView, String> tbvStudentFrom;
//    @FXML
//    private TableColumn<StudentView, String> tbvStudentTo;
//
//    public VIewController() {
//    }
//
//    public MainView getMainApp() {
//        return mainApp;
//    }
//
//    /**
//     * Is called by the main application to give a reference back to itself.
//     *
//     * @param mainApp
//     */
//    public void setMainApp(MainView mainApp) {
//        this.mainApp = mainApp;
//    }
//
//    @FXML
//    public void initialize() {
//        initTableSchool();
//        initTableGroup();
//        initTableStudents();
//    }
//
//    private void initTableSchool() {
//        tbvSchoolsID.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
//        tbvSchoolsName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        tbvSchoolsDecs.setCellValueFactory(cellData -> cellData.getValue().descProperty());
//        tbvSchoolsCounty.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
//        tbvSchools.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> updateSelectedSchool(newValue));
//    }
//
//    @FXML
//    public void onClickBtnSchoolsUpdate() {
//        ArrayList<SchoolsEntity> schoolsEntities = new SchoolDAOImpl().listAllSchools();
//        mainApp.getSchoolsViewsList().clear();
//        for (SchoolsEntity schoolsEntity : schoolsEntities) {
//            SchoolView schoolView = new SchoolView(
//                    schoolsEntity.getSchoolId(),
//                    schoolsEntity.getSchoolName(),
//                    schoolsEntity.getSchoolDesc(),
//                    schoolsEntity.getCountriesByCountryId().getCountryName()
//            );
//            mainApp.getSchoolsViewsList().addAll(schoolView);
//        }
//    }
//
//    private void updateSelectedSchool(SchoolView schoolView) {
//        selectedSchoolView = schoolView;
//        onClickBtnGroupsUpdate();
//    }
//
//    public TableView getTbvSchools() {
//        return tbvSchools;
//    }
//
//    private void initTableGroup() {
//        tbvGroupId.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
//        tbvGroupName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        tbvGroup.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> updateSelectedGrpup(newValue)
//        );
//    }
//
//    @FXML
//    public void onClickBtnGroupsUpdate() {
//        SchoolsEntity schoolsEntity = new SchoolDAOImpl().getSchool(selectedSchoolView.getId());
//        ArrayList<GroupsEntity> groupsEntities = new ArrayList<>(schoolsEntity.getGroupsesBySchoolId());
//        mainApp.getGroupsViewsList().clear();
//        for (GroupsEntity groupsEntity : groupsEntities) {
//            GroupView groupView = new GroupView(
//                    groupsEntity.getGroupId(),
//                    String.valueOf(groupsEntity.getNumber())
//            );
//            mainApp.getGroupsViewsList().addAll(groupView);
//        }
//    }
//
//    private void updateSelectedGrpup(
//            GroupView groupView) {
//        selectedGroupView = groupView;
//        onClickBtnStudentsUpdate();
//    }
//
//    public TableView<GroupView> getTbvGroup() {
//        return tbvGroup;
//    }
//
//    private void initTableStudents() {
//        tbvStudentId.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
//        tbvStudentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        tbvStudentEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
//        tbvStudentPass.setCellValueFactory(cellData -> cellData.getValue().passProperty());
//        tbvStudentFrom.setCellValueFactory(cellData -> cellData.getValue().studFromProperty());
//        tbvStudentTo.setCellValueFactory(cellData -> cellData.getValue().studToProperty());
//        tbvStudent.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> updateSelectedSrudent(newValue)
//        );
//    }
//
//    @FXML
//    public void onClickBtnStudentsUpdate() {
//        ArrayList<StudentsEntity> studentsEntities = new StudentDAOImpl().listAllStudents();
//        mainApp.getStudentsViewsList().clear();
//        for (StudentsEntity studentsEntity : studentsEntities) {
//            if (studentsEntity.getGroupId() != selectedGroupView.getId()) continue;
//            StudentView studentView = new StudentView(
//                    studentsEntity.getUserId(),
//                    studentsEntity.getUserByUsersEntityId().getName(),
//                    studentsEntity.getUserByUsersEntityId().getEmail(),
//                    studentsEntity.getUserByUsersEntityId().getPassword(),
//                    "",//studentsEntity.getStudentFrom().toString(),
//                    ""//studentsEntity.getStudentTo().toString()
//            );
//            mainApp.getStudentsViewsList().addAll(studentView);
//        }
//    }
//
//    private void updateSelectedSrudent(StudentView studentView) {
//        selestedStudentView = studentView;
//    }
//
//    public TableView<StudentView> getTbvStudent() {
//        return tbvStudent;
//    }
//}

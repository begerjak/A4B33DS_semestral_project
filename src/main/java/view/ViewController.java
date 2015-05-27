package view;

import controller.MainView;
import controller.dao.GroupDAO;
import controller.dao.SchoolDAO;
import controller.dao.StudentDAO;
import controller.dao.UserDAO;
import controller.dao.impl.HibernateDAOFactory;
import controller.dao.impl.SchoolDAOImpl;
import controller.dao.impl.StudentDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.db_schema.GroupsEntity;
import model.db_schema.SchoolsEntity;
import model.db_schema.StudentsEntity;
import model.db_schema.UsersEntity;
import view.view_model.GroupView;
import view.view_model.SchoolView;
import view.view_model.StudentView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class ViewController {
    MainView mainApp;

    public ViewController() {
    }

    public MainView getMainApp() {
        return mainApp;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainView mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        initTableSchool();
        initTableGroup();
        initTableStudents();
    }


//**********************************************************************************************************************
//  Table school
//**********************************************************************************************************************
    SchoolView selectedSchoolView;
    @FXML
    private TableView<SchoolView> tbvSchools;
    @FXML
    private TableColumn<SchoolView, Integer> tbvSchoolsID;
    @FXML
    private TableColumn<SchoolView, String> tbvSchoolsName;
    @FXML
    private TableColumn<SchoolView, String> tbvSchoolsDecs;
    @FXML
    private TableColumn<SchoolView, String> tbvSchoolsCounty;

    private void initTableSchool() {
        tbvSchoolsID.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
        tbvSchoolsName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tbvSchoolsDecs.setCellValueFactory(cellData -> cellData.getValue().descProperty());
        tbvSchoolsCounty.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        tbvSchools.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateSelectedSchool(newValue));
    }

    @FXML
    public void onClickBtnSchoolsUpdate() {
        ArrayList<SchoolsEntity> schoolsEntities = new SchoolDAOImpl().listAllSchools();
        mainApp.getSchoolsViewsList().clear();
        for (SchoolsEntity schoolsEntity : schoolsEntities) {
            SchoolView schoolView = new SchoolView(
                    schoolsEntity.getSchoolId(),
                    schoolsEntity.getSchoolName(),
                    schoolsEntity.getSchoolDesc(),
                    schoolsEntity.getCountriesByCountryId()
            );
            mainApp.getSchoolsViewsList().addAll(schoolView);
        }
    }

    private void updateSelectedSchool(SchoolView schoolView) {
        selectedSchoolView = schoolView;
        onClickBtnGroupsUpdate();
    }

    public TableView getTbvSchools() {
        return tbvSchools;
    }

    @FXML
    public void onClickSchoolsEdit(){
        SchoolView schoolView = tbvSchools.getSelectionModel().getSelectedItem();
        if (schoolView != null){
            boolean okClicked = mainApp.showSchoolDialog(schoolView, "Edit School");
            if (okClicked == false) return;

            SchoolDAO schoolDAO = new HibernateDAOFactory().getSchoolDAO();
            SchoolsEntity schoolsEntity = schoolDAO.getSchool(schoolView.getId());
            schoolsEntity.setSchoolName(schoolView.getName());
            schoolsEntity.setSchoolDesc(schoolView.getDesc());
            schoolsEntity.setCountryId(schoolView.getCountriesEntity().getCountryId());
            schoolsEntity.setCountriesByCountryId(schoolView.getCountriesEntity());
            schoolDAO.updateSchool(schoolsEntity);

            onClickBtnSchoolsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No school selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a school in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    public void onClickSchoolsNew(){
        SchoolView schoolView = new SchoolView();
        boolean okClicked = mainApp.showSchoolDialog(schoolView, "New School");
        if (!okClicked) return;
        SchoolsEntity schoolsEntity = new SchoolsEntity();
        schoolsEntity.setSchoolName(schoolView.getName());
        schoolsEntity.setSchoolDesc(schoolView.getDesc());
        schoolsEntity.setCountriesByCountryId(schoolView.getCountriesEntity());
        schoolsEntity.setCountryId(schoolView.getCountriesEntity().getCountryId());
        new HibernateDAOFactory().getSchoolDAO().insertSchool(schoolsEntity);

        onClickBtnSchoolsUpdate();
    }

    @FXML
    public void onClickSchoolsDetele(){
        SchoolView schoolView = tbvSchools.getSelectionModel().getSelectedItem();
        if (schoolView != null){
            SchoolsEntity schoolsEntity = new HibernateDAOFactory().getSchoolDAO().getSchool(schoolView.getId());
            new HibernateDAOFactory().getSchoolDAO().deleteSchool(schoolsEntity);
            onClickBtnSchoolsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No school selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a school in the table.");

            alert.showAndWait();
        }
    }


//**********************************************************************************************************************
//  Table group
//**********************************************************************************************************************
    GroupView selectedGroupView;
    @FXML
    private TableView<GroupView> tbvGroup;
    @FXML
    private TableColumn<GroupView, Integer> tbvGroupId;
    @FXML
    private TableColumn<GroupView, String> tbvGroupName;

    private void initTableGroup() {
        tbvGroupId.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
        tbvGroupName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tbvGroup.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateSelectedGrpup(newValue)
        );
    }

    @FXML
    public void onClickBtnGroupsUpdate() {
        if (selectedSchoolView == null) return;
        ArrayList<GroupsEntity> groupsEntities = new HibernateDAOFactory().getGroupDAO().listAllGroups();
        mainApp.getGroupsViewsList().clear();
        for (GroupsEntity groupsEntity : groupsEntities) {
            if (groupsEntity.getSchoolId() != selectedSchoolView.getId()) continue;
            GroupView groupView = new GroupView(
                    groupsEntity.getGroupId(),
                    String.valueOf(groupsEntity.getNumber())
            );
            mainApp.getGroupsViewsList().addAll(groupView);
        }
    }


    private void updateSelectedGrpup(
            GroupView groupView) {
        selectedGroupView = groupView;
        onClickBtnStudentsUpdate();
    }

    public TableView<GroupView> getTbvGroup() {
        return tbvGroup;
    }

    @FXML
    public void onClickGroupsEdit(){
        GroupView groupView = tbvGroup.getSelectionModel().getSelectedItem();
        if (groupView != null){
            boolean okClicked = mainApp.showGroupDialog(groupView, "Edit Group");
            if (okClicked == false) return;

            GroupDAO groupDAO = new HibernateDAOFactory().getGroupDAO();
            GroupsEntity groupsEntity = groupDAO.getGroup(groupView.getId());
            groupsEntity.setNumber(Integer.parseInt(groupView.getName()));
            groupsEntity.setSchoolsBySchoolId(new HibernateDAOFactory().getSchoolDAO().getSchool(selectedSchoolView.getId()));
            groupDAO.updateGroup(groupsEntity);

            onClickBtnGroupsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No group selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a group in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    public void onClickGroupsNew(){
        GroupView groupView = new GroupView();
        boolean okClicked = mainApp.showGroupDialog(groupView, "New Group");
        if (!okClicked) return;
        GroupsEntity groupsEntity = new GroupsEntity();
        groupsEntity.setNumber(Integer.parseInt(groupView.getName()));
        SchoolsEntity schoolsEntity = new HibernateDAOFactory().getSchoolDAO().getSchool(selectedSchoolView.getId());
        groupsEntity.setSchoolsBySchoolId(schoolsEntity);
        groupsEntity.setSchoolId(schoolsEntity.getSchoolId());

        new HibernateDAOFactory().getGroupDAO().insertGroup(groupsEntity);

        onClickBtnGroupsUpdate();
    }

    @FXML
    public void onClickGroupsDelete(){
        GroupView groupView = tbvGroup.getSelectionModel().getSelectedItem();
        if (groupView != null){
            GroupsEntity groupsEntity = new HibernateDAOFactory().getGroupDAO().getGroup(groupView.getId());
            new HibernateDAOFactory().getGroupDAO().deleteGroup(groupsEntity);
            onClickBtnGroupsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No group selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a group in the table.");

            alert.showAndWait();
        }
    }

//**********************************************************************************************************************
//  Table students
//**********************************************************************************************************************
    StudentView selestedStudentView;
    @FXML
    private TableView<StudentView> tbvStudent;
    @FXML
    private TableColumn<StudentView, Integer> tbvStudentId;
    @FXML
    private TableColumn<StudentView, String> tbvStudentName;
    @FXML
    private TableColumn<StudentView, String> tbvStudentEmail;
    @FXML
    private TableColumn<StudentView, String> tbvStudentPass;
    @FXML
    private TableColumn<StudentView, String> tbvStudentFrom;
    @FXML
    private TableColumn<StudentView, String> tbvStudentTo;

    private void initTableStudents() {
        tbvStudentId.setCellValueFactory(cellData -> cellData.getValue().getIdProp().asObject());
        tbvStudentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tbvStudentEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tbvStudentPass.setCellValueFactory(cellData -> cellData.getValue().passProperty());
        tbvStudentFrom.setCellValueFactory(cellData -> cellData.getValue().studFromProperty());
        tbvStudentTo.setCellValueFactory(cellData -> cellData.getValue().studToProperty());
        tbvStudent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateSelectedStudent(newValue)
        );
    }

    @FXML
    public void onClickBtnStudentsUpdate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (selectedGroupView == null) return;
        ArrayList<StudentsEntity> studentsEntities = new StudentDAOImpl().listAllStudents();
        mainApp.getStudentsViewsList().clear();
        for (StudentsEntity studentsEntity : studentsEntities) {
            if (studentsEntity.getGroupId() != selectedGroupView.getId()) continue;
            String dateFromS = "null", dateToS = "null";
            Date dateFrom = null, dateTo = null;
            if (studentsEntity.getStudentFrom() != null){
                dateFrom = new Date(studentsEntity.getStudentFrom().getTime());
                dateFromS = df.format(dateFrom);
            }
            if (studentsEntity.getStudentTo() != null){
                dateTo = new Date(studentsEntity.getStudentTo().getTime());
                dateToS = df.format(dateTo);
            }
            StudentView studentView = new StudentView(
                    studentsEntity.getUserId(),
                    studentsEntity.getUserByUsersEntityId().getName(),
                    studentsEntity.getUserByUsersEntityId().getEmail(),
                    studentsEntity.getUserByUsersEntityId().getPassword(),
                    dateFromS,//studentsEntity.getStudentFrom().toString(),
                    dateToS//studentsEntity.getStudentTo().toString()
            );
            studentView.setStudentFrom(dateFrom);
            studentView.setStudentTo(dateTo);
            mainApp.getStudentsViewsList().addAll(studentView);
        }
    }

    private void updateSelectedStudent(StudentView studentView) {
        selestedStudentView = studentView;
    }

    public TableView<StudentView> getTbvStudent() {
        return tbvStudent;
    }

    @FXML
    public void onClickStudentEdit(){
        StudentView studentView = tbvStudent.getSelectionModel().getSelectedItem();
        if (studentView != null){
            boolean okClicked = mainApp.showStudentDialog(studentView, "Edit Student - " + studentView.getName());
            if (okClicked == false) return;

            StudentDAO studentDAO = new HibernateDAOFactory().getStudentDAO();
            UserDAO userDAO = new HibernateDAOFactory().getUserDAO();

            StudentsEntity studentsEntity = studentDAO.getStudent(studentView.getId());
            UsersEntity usersEntity = userDAO.getUser(studentView.getId());

            studentsEntity.setStudentFrom(new Timestamp(studentView.getStudentFrom().getTime()));
            studentsEntity.setStudentTo(new Timestamp(studentView.getStudentTo().getTime()));

            usersEntity.setName(studentView.getName());
            usersEntity.setEmail(studentView.getEmail());
            usersEntity.setPassword(studentView.getPass());

            studentDAO.updateStudent(studentsEntity);
            userDAO.updateUser(usersEntity);

            onClickBtnStudentsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No student selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    public void onClickStudentNew(){
        StudentView studentView = new StudentView();
        boolean okClicked = mainApp.showStudentDialog(studentView, "New Student");
        if (!okClicked) return;


        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setName(studentView.getName());
        usersEntity.setEmail(studentView.getEmail());
        usersEntity.setPassword(studentView.getPass());
        int insertedUserId = new HibernateDAOFactory().getUserDAO().insertUser(usersEntity);

        StudentsEntity studentsEntity = new StudentsEntity();
        studentsEntity.setStudentFrom(new Timestamp(studentView.getStudentFrom().getTime()));
        studentsEntity.setStudentTo(new Timestamp(studentView.getStudentTo().getTime()));
        studentsEntity.setUserId(insertedUserId);
        studentsEntity.setUserByUsersEntityId(usersEntity);
        studentsEntity.setGroupId(selectedGroupView.getId());
        studentsEntity.setGroupsByGroupId(new HibernateDAOFactory().getGroupDAO().getGroup(selectedGroupView.getId()));
        new HibernateDAOFactory().getStudentDAO().insertStudent(studentsEntity);

        onClickBtnStudentsUpdate();
    }

    @FXML
    public void onClickStudentDelete(){
        StudentView studentView = tbvStudent.getSelectionModel().getSelectedItem();
        if (studentView != null){
            StudentDAO studentDAO = new HibernateDAOFactory().getStudentDAO();
            UserDAO userDAO = new HibernateDAOFactory().getUserDAO();

            StudentsEntity studentsEntity = studentDAO.getStudent(studentView.getId());
            UsersEntity usersEntity = userDAO.getUser(studentView.getId());

            studentDAO.deleteStudent(studentsEntity);
            userDAO.deleteUser(usersEntity);

            onClickBtnStudentsUpdate();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No student selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student in the table.");

            alert.showAndWait();
        }
    }
}

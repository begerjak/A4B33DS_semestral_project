//package controller.dao.impl;
//
//import controller.dao.BaseDAO;
//import controller.dao.StudentDAO;
//import model.db_schema.UsersEntity;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javax.persistence.PersistenceException;
//import java.util.ArrayList;
//import java.util.logging.Logger;
//
///**
// * Copyright 2015 IEAP CTU
// * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
// */
//public class StudentDAOImpl extends BaseDAO implements StudentDAO {
//
//    public StudentDAOImpl() {
//        LOGGER = Logger.getLogger(StudentDAOImpl.class.getName());
//    }
//
//    public int insertStudent(Students student) {
//        if (student == null)
//            throw new IllegalArgumentException("Input parameter cannot be null");
//
//        try {
//            LOGGER.info(String.format("Inserting new student"));
//            Session session = HibernateDAOFactory.createSession();
//            Transaction tx = session.beginTransaction();
//            session.save(student);
//            tx.commit();
//            return student.getUserId();
//        } catch (Exception ex) {
//            throw new PersistenceException("insertStudent failed", ex);
//        }
//    }
//
//    public boolean deleteStudent(Students student) {
//        if (student == null)
//            throw new IllegalArgumentException("Input parameter cannot be null");
//        try {
//            LOGGER.info(String.format("Deleting student with Id %d", student.getUserId()));
//            Session session = HibernateDAOFactory.createSession();
//            Transaction tx = session.beginTransaction();
//            session.delete(student);
//            tx.commit();
//            return true;
//        } catch (Exception ex) {
//            throw new PersistenceException("deleteStudent failed", ex);
//        }
//    }
//
//    public Students getStudent(int id) {
//        try {
//            LOGGER.info(String.format("Getting student with Id %d", id));
//            Session session = HibernateDAOFactory.createSession();
//            Transaction tx = session.beginTransaction();
//            Students student = (Students) session.get(Students.class, id);
//            tx.commit();
//            return student;
//        } catch (Exception ex) {
//            throw new PersistenceException("getStudent failed", ex);
//        }
//    }
//
//
//
//    public boolean updateStudent(Students student) {
//        if (student == null)
//            throw new IllegalArgumentException("Input parameter cannot be null");
//        try {
//            LOGGER.info(String.format("Updating student with Id %d", student.getUserId()));
//            Session session = HibernateDAOFactory.createSession();
//            Transaction tx = session.beginTransaction();
//            session.update(student);
//            tx.commit();
//            return true;
//        } catch (Exception ex) {
//            throw new PersistenceException("updateStudent failed", ex);
//        }
//    }
//
//    public Students findStudent(String email) {
//        UsersEntity usersEntity = new UserDAOImpl().findUser(email);
//        Students students = getStudent(usersEntity.getUserId());
//        return students;
//    }
//
//    public ArrayList<Students> listAllStudents() {
//        try {
//            LOGGER.info(String.format("List all students"));
//            Session session = HibernateDAOFactory.createSession();
//
//            Criteria crit = session.createCriteria(Students.class);
//            ArrayList<Students> users = (ArrayList<Students>)crit.list();
//            return users;
//        } catch (Exception ex) {
//            throw new PersistenceException("listAllStudents failed", ex);
//        }
//    }
//}

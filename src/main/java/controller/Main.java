package controller;

import org.hibernate.Session;

/**
 * Copyright 2015 IEAP CTU
 * Author: Jakub Begera (jakub.begera@utef.cvut.cz)
 */
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getOpenSession();
        System.out.println();
    }
}

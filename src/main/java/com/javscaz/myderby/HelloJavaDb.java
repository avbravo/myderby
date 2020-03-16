/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javscaz.myderby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author avbravo
 */
public class HelloJavaDb {

    /**
     * @param args the command line arguments
     */
    Connection conn;

    public static void main(String[] args) throws SQLException {

        HelloJavaDb app = new HelloJavaDb();

        app.connectionToDerby();
        ///-------------------
        app.createTable();
        //--------------------
        app.insert();
        app.query();
        app.drop();
    }

    public void connectionToDerby() throws SQLException {
        // -------------------------------------------
        // URL format is
        // jdbc:derby:<local directory to save data>
        // -------------------------------------------
        //jdbc:derby:memory:demo;create=true;

//    String dbUrl = "jdbc:derby:c:\\Users\\shengw\\MyDB\\demo;create=true";
        String dbUrl = "jdbc:derby:/home/avbravo/Descargas/demo;create=true";
        conn = DriverManager.getConnection(dbUrl);
    }

    public Boolean createTable() throws SQLException {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("Create table users (id int primary key, name varchar(30))");
            return true;
        } catch (Exception e) {
            System.out.println("createTable() " + e.getLocalizedMessage());
        }
        return false;
    }

    public Boolean insert() {
        try {

            System.out.println("Llego al query");
            Statement stmt = conn.createStatement();

////    // insert 2 rows
            stmt.executeUpdate("insert into users values (1,'tom')");
            stmt.executeUpdate("insert into users values (2,'peter')");
// 
            return true;

        } catch (Exception e) {
            System.out.println("insert() " + e.getLocalizedMessage());
        }
        return false;
    }

    public Boolean query() {
        try {

            Statement stmt = conn.createStatement();

            // query
         
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            if (rs == null) {
                System.out.println("No logro obtener la consulta....");
            } else {
                // print out query result
             
                while (rs.next()) {
                    System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
                }
          
                return true;
            }

        } catch (Exception e) {
            System.out.println("query() " + e.getLocalizedMessage());
        }
        return false;
    }

    public Boolean drop() {
        try {


            Statement stmt = conn.createStatement();

            // drop table
            stmt.executeUpdate("Drop Table users");

            return true;

        } catch (Exception e) {
            System.out.println("drop() " + e.getLocalizedMessage());
        }
        return false;
    }

}

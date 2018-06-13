package com.riccio.customtracker.jdbc_test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //setup connection variables
        String user = "springstudent";
        String pwd = "springstudent";

        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";

        String driver = "com.mysql.jdbc.Driver";

        //get connection to database
        Connection myConn = null;
        try {

            PrintWriter out = response.getWriter();
            out.println("connecting to database : " + jdbcUrl);
            Class.forName(driver);
            myConn = DriverManager.getConnection(jdbcUrl, user, pwd);
            System.out.println("success");

            myConn.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

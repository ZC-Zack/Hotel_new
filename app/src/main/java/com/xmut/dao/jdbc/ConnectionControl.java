package com.xmut.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionControl {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private String url="jdbc:mysql://118.24.221.92:3306/hotel";
    private String username = "root", password = "root";
    private String sql = "SELECT * FROM ";

    public ConnectionControl() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet getResultSet(String data){
        sql += data;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return  resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}

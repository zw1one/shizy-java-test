package com.shizy.utils.jdbc;

import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class JdbcService {

    private String driver;

    private String url;

    private String username;

    private String password;

    public JdbcService(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void releaseDB(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*******************************************************************/

    public List<Map<String, Object>> query(String sql, String... param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                preparedStatement.setObject(i + 1, param[i]);
            }
            resultSet = preparedStatement.executeQuery();

            List<Map<String, Object>> dataList = new ArrayList<>();
            while (resultSet.next()) {
                Map record = new HashMap();
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    String key = resultSet.getMetaData().getColumnName(i + 1);
                    Object value = resultSet.getObject(i + 1);
                    record.put(key, value);
                }
                dataList.add(record);
            }

            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.releaseDB(connection, preparedStatement, resultSet);
        }
        return null;
    }

//    public void insert() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            // 获取连接
//            connection = JdbcService.getConnection();
//            // 准备sql语句
//            String sql = "INSERT INTO user(id,name,age) VALUES(?,?,?)";
//            // 获取PrepareStatement对象
//            preparedStatement = connection.prepareStatement(sql);
//            // 填充占位符
//            preparedStatement.setInt(1, 6);
//            preparedStatement.setString(2, "小A");
//            preparedStatement.setInt(3, 25);
//            // 执行sql
//            int num = preparedStatement.executeUpdate();// 返回影响到的行数
//
//            System.out.println("一共影响到" + num + "行");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcService.releaseDB(connection, preparedStatement, null);
//        }
//    }
//
//    public void update() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            // 获取连接
//            connection = JdbcService.getConnection();
//            // 准备sql语句
//            String sql = "update user set age = ? where name = ?";
//            // 获取PrepareStatement对象
//            preparedStatement = connection.prepareStatement(sql);
//            // 填充占位符
//            preparedStatement.setInt(1, 30);
//            preparedStatement.setString(2, "小A");
//            // 执行sql
//            int num = preparedStatement.executeUpdate();// 返回影响到的行数
//
//            System.out.println("一共影响到" + num + "行");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JdbcService.releaseDB(connection, preparedStatement, null);
//        }
//    }
//
//    public void delete() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = JdbcService.getConnection();
//            String sql = "DELETE FROM user WHERE id = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, 6);
//            int num = preparedStatement.executeUpdate();
//
//            System.out.println("一共影响到" + num + "行");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcService.releaseDB(connection, preparedStatement, null);
//        }
//    }


}



















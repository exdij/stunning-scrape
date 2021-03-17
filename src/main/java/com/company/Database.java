package com.company;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    public Database(){}


    public ArrayList<ArrayList<String>> getData(String shop) {
        ArrayList<ArrayList<String>> items = new ArrayList<>();
        try {
            Connection con = connectToDB();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + shop);
            while(rs.next()) {
                ArrayList<String> item = new ArrayList<>();
                item.add(rs.getString(1));
                item.add(rs.getString(2));
                item.add(rs.getString(3));
                item.add(rs.getString(4));
                items.add(item);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return items;
    }

    public void writeData(ArrayList<ArrayList<String>> list, String shop){
                try {
                    Connection con = connectToDB();
                    Statement stmt = con.createStatement();
                    String date;
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long time = System.currentTimeMillis();
                    time += 2*60*60*1000;
                    date = formatter.format(time);
                    con.setAutoCommit(false);
                    stmt.executeUpdate("TRUNCATE TABLE " + shop);
                    stmt.close();
                    String query = "insert into " + shop + " (product_name, product_price, product_category, product_link, last_update) values (?, ? ,? ,? ,?)";
                    PreparedStatement preparedStmt = con.prepareStatement(query);
                    for (ArrayList<String> child:list){
                        preparedStmt.setString(1,child.get(0));
                        preparedStmt.setString(2,child.get(1));
                        preparedStmt.setString(3,child.get(2));
                        preparedStmt.setString( 4,child.get(3));
                        preparedStmt.setTimestamp(5, Timestamp.valueOf(date));
                        preparedStmt.addBatch();
                    }
                    preparedStmt.executeBatch();
                    preparedStmt.close();
                    con.commit();
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
        }



    }

    public void writeMD5(String md5, String shop){
        try {
            Connection con = connectToDB();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update hash_val set " + shop + "=" + md5);
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    private Connection connectToDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = "jdbc:mysql://localhost:3306/shop_data";
        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "root");
        info.put("serverTimezone", "UTC");
        Connection con = DriverManager.getConnection(URL, info);
        return con;
    }
    private void disconnectFromDB(Connection con) throws SQLException {
        con.close();
    }

}

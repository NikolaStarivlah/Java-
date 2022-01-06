/*
   Course: PROG36859
  Assignment No.: 2
  Your Name: Nikola Starivlah
  Your Id: starivln
  Instructor’s name: Syed Tanbeer
  */

package com.javaassign;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Scanner in = new Scanner(System.in);
    static Scanner inD = new Scanner(System.in);
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        readFiles();
        updateCategories();
        getCategoryData();
        displayMaxPurchase();
        displayAbovePrice();
        displayItemCountPerCategory();
        inputSQL();
    }

    private static void readFiles() {

        System.out.println("Reading Items from file and Adding Items to Database");
        URL url = Main.class.getResource("data/items.txt");
	    readFile(url.getPath(),1);
        System.out.println("Reading Transactions from file and Adding transactions to Database");
        url = Main.class.getResource("data/transactions.txt");
        readFile(url.getPath(),2);
    }

    private static void inputSQL() throws SQLException, ClassNotFoundException {
        System.out.println("Enter an Sql String");
        String sqlSt=in.nextLine();
        PreparedStatement st = Main.getConn().prepareStatement(sqlSt);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
                System.out.print(String.valueOf(rs.getObject(i))+"\t");
            }
            System.out.println();
        }
    }

    private static void displayItemCountPerCategory() throws SQLException, ClassNotFoundException {
        String sel = "SELECT category, COUNT(id) as Count FROM Items GROUP BY category";
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("category") +"\t" + String.valueOf(rs.getInt("Count")));
        }
    }

    private static void displayAbovePrice() throws SQLException, ClassNotFoundException {
        System.out.println("Enter a Unit Price");
        Double price = inD.nextDouble();
        String sel = "SELECT * FROM Items WHERE unitPrice>?";
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        st.setDouble(1,price);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            System.out.println(String.valueOf(rs.getInt("id"))+"\t"+rs.getString("name")+"\t"+rs.getString("category") +"\t" + String.valueOf(rs.getDouble("unitPrice")));
        }
    }

    private static void displayMaxPurchase() throws SQLException, ClassNotFoundException {
        String se = "SELECT MAX(quantity) as max_val FROM Transactions";
        PreparedStatement stm = Main.getConn().prepareStatement(se);
        ResultSet rst = stm.executeQuery();
        rst.next();
        int max_q=rst.getInt("max_val");
        String sel = "SELECT * FROM Items JOIN Transactions ON Items.id=Transactions.itemId WHERE quantity=?";
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        st.setInt(1,max_q);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            System.out.println(String.valueOf( rs.getString("name"))+"\t"+rs.getString("category"));
        }
    }

    private static void getCategoryData() throws SQLException, ClassNotFoundException {
        System.out.println("Enter a Category:");
        String category=in.nextLine();
        String sel = "SELECT * FROM CategoryTotal_"+category;
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            System.out.println(String.valueOf( rs.getInt("itemId"))+"\t"+rs.getString("item")+"\t"+String.valueOf(rs.getDouble("totalAmount")));
        }
        String se = "SELECT sum(totalAmount) as tA FROM CategoryTotal_"+category;
        PreparedStatement stm = Main.getConn().prepareStatement(se);
        ResultSet rst = stm.executeQuery();
        rst.next();
        System.out.println("Total Category Amount: " + String.valueOf(rst.getDouble("tA")));
    }

    public static void readFile(String filename,int type){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine();
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] s_data = data.split("\\s+");
                if(type==1){
                    Item item = new Item(s_data[0],s_data[1],s_data[2],Double.parseDouble(s_data[3]));
//                    item.addToDB();
                }else{
                    Transaction transaction = new Transaction(Integer.parseInt(s_data[0]),Integer.parseInt(s_data[1]),Integer.parseInt(s_data[2]));
                    transaction.addToDB();
                }

            }
            myReader.close();
        } catch (FileNotFoundException | SQLException | ClassNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/MyStore_starivln");
        return conn;
    }
    public static void updateCategories() throws SQLException, ClassNotFoundException {
        String[] categories = new String[0];
        String sel = "SELECT DISTINCT(category) FROM Items";
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            categories = Arrays.copyOf(categories, categories.length + 1);
            categories[categories.length - 1]= rs.getString("category");
        }
        for (String category: categories) {
            System.out.println(category);
            updateCategory(category);
        }
    }

    private static void updateCategory(String category) throws SQLException, ClassNotFoundException {
        String sel = "SELECT id,name,unitPrice,sum(quantity) as quantity FROM Items JOIN Transactions on Items.id=Transactions.itemId Where category= ? GROUP BY id";
        PreparedStatement st = Main.getConn().prepareStatement(sel);
        st.setString(1,category);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            String ins = "INSERT INTO CategoryTotal_"+category+"(itemId,item,totalAmount) VALUES(?,?,?);";
            PreparedStatement stm = Main.getConn().prepareStatement(ins);
            stm.setInt(1, rs.getInt("id"));
            stm.setString(2, rs.getString("name"));
            Double tot= rs.getDouble("unitPrice") * rs.getInt("quantity");
            stm.setDouble(3,tot);
            stm.execute();

        }

    }
}

package com.javaassign;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Item {
    String id,name,category;
    Double unitPrice;

    public Item(String id,String name, String category, Double unitPrice){
        this.id=id;
        this.name=name;
        this.category=category;
        this.unitPrice=unitPrice;
    }
    public void addToDB() throws SQLException, ClassNotFoundException {
        String ins = "INSERT INTO Items(id,name,category,unitPrice) VALUES(?,?,?,?);";
        PreparedStatement st = Main.getConn().prepareStatement(ins);
        st.setInt(1,Integer.valueOf(this.id));
        st.setString(2,this.name);
        st.setString(3,this.category);
        st.setDouble(4,this.unitPrice);
        st.execute();
        System.out.println("Added item" + this.id +" Succesfully.\n");
    }
}

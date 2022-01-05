package com.javaassign;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {
    int transactionId,itemId,quantity;
    public Transaction(int transactionId,int itemId, int quantity){
        this.transactionId=transactionId;
        this.itemId=itemId;
        this.quantity=quantity;
    }
    public void addToDB() throws SQLException, ClassNotFoundException {
        String ins = "INSERT INTO Transactions(transactionId,itemId,quantity) VALUES(?,?,?);";
        PreparedStatement st = Main.getConn().prepareStatement(ins);
        st.setInt(1,this.transactionId);
        st.setInt(2,this.itemId);
        st.setInt(3,this.quantity);
        st.execute();
        System.out.println("Added Transaction " + this.transactionId +" Succesfully.\n");
    }
}

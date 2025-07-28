package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class bill_details extends JFrame {
    String meter;
    bill_details(String meter){
        this.meter= meter;
        setSize(730,650);
        setLocation(400,150);
        setLayout(null);
        getContentPane().setBackground(new Color(0xCEE0F3));


        JTable table = new JTable();
        table.setBackground(new Color(0xD6D6F6));

        try {
            database c = new database();
            String query_bill = "select * from bill where meter_no = '"+meter+"'";
            ResultSet resultSet = c.statement.executeQuery(query_bill);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane sp  = new JScrollPane(table);
        sp.setBounds(0,0,700,650);
        add(sp);

        setVisible(true);

    }

    public static void main(String[]arg){
        new bill_details("");
    }
}

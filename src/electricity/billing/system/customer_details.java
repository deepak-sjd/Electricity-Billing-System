package electricity.billing.system;



import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchNameCho;
    JTable table;
    JButton search,print,close;

    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(750,500);
        setLocation(500,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,200,20);
        searchMeter.setFont(new Font("serif",Font.BOLD,17));
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(230,20,150,20);
        searchMeterCho.setFont(new Font("serif",Font.BOLD,17));
        add(searchMeterCho);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400,20,130,20);
        searchName.setFont(new Font("serif",Font.BOLD,17));
        add(searchName);

        searchNameCho = new Choice();
        searchNameCho.setBounds(530,20,150,20);
        searchNameCho.setFont(new Font("serif",Font.BOLD,16));
        add(searchNameCho);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while (resultSet.next()){
                searchNameCho.add(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        table.setFont(new Font("serif",Font.PLAIN,15));
        table.setRowHeight(23);
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(3,120,730,500);
        scrollPane.setFont(new Font("serif",Font.BOLD,17));
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBounds(20,70,90,23);
        search.setFont(new Font("serif",Font.BOLD,17));
        search.setBackground(Color.white);
        search.addActionListener(this);
        add(search);


        print = new JButton("Print");
        print.setBounds(120,70,90,23);
        print.setFont(new Font("serif",Font.BOLD,17));
        print.setBackground(Color.white);
        print.addActionListener(this);
        add(print);


        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(590,70,90,23);
        close.setFont(new Font("serif",Font.BOLD,17));
        close.addActionListener(this);
        add(close);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==search){
            String query_search = "select * from new_customer where meter_no= '"+searchMeterCho.getSelectedItem()+"' and name = '"+searchNameCho.getSelectedItem()+"'";
            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print){
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[]arg){
        new customer_details();
    }
}

package electricity.billing.system;

import com.mysql.cj.xdevapi.Table;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class deposite_details extends JFrame implements ActionListener {
    Choice searchMeterCho, searchMonthCho;
    JTable table;
    JButton search,print,close;
    deposite_details(){

        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(750,500);
        setLocation(500,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,200,20);
        searchMeter.setFont(new Font("serif",Font.BOLD,17));
        add(searchMeter);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(220,20,150,20);
        searchMeterCho.setFont(new Font("serif",Font.BOLD,16));
        add(searchMeterCho);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
                searchMeterCho.setFont(new Font("serif",Font.BOLD,16));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        JLabel searchMonth = new JLabel("Search By Month");
        searchMonth.setBounds(400,20,130,20);
        searchMonth.setFont(new Font("serif",Font.BOLD,17));
        add(searchMonth);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(540,20,150,20);
        searchMonthCho.setFont(new Font("serif",Font.BOLD,16));
        add(searchMonthCho);


        table = new JTable();
        table.setFont(new Font("serif",Font.PLAIN,15));
        table.setRowHeight(23);
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(3,120,730,500);
        scrollPane.setFont(new Font("serif",Font.BOLD,16));
        scrollPane.setBackground(Color.white);
        add(scrollPane);


        search = new JButton("Search");
        search.setBounds(20,70,100,24);
        search.setFont(new Font("serif",Font.BOLD,17));
        search.setBackground(Color.white);
        search.addActionListener(this);
        add(search);


        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(140,70,90,24);
        print.setFont(new Font("serif",Font.BOLD,17));
        print.addActionListener(this);
        add(print);


        close = new JButton("Close");
        close.setBounds(550,70,90,24);
        close.setFont(new Font("serif",Font.BOLD,17));
        close.setBackground(Color.white);
        close.addActionListener(this);
        add(close);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==search){
            String query_search = "select * from bill where meter_no= '"+searchMeterCho.getSelectedItem()+"'and month = '"+searchMonthCho.getSelectedItem()+"'";
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
        new deposite_details();
    }
}

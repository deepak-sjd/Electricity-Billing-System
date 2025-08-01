package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    Choice searchmonthcho;
    String meter;
    JButton pay, back;

    pay_bill(String meter) {
        this.meter = meter;
        setSize(900, 600);
        setLocation(300, 150);
        setLayout(null);

        getContentPane().setBackground(new Color(0xA9C0E1));


        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("serif", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);

        JLabel meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(35, 80, 200, 22);
        meterNumber.setFont(new Font("monospaced",Font.BOLD,20));
        add(meterNumber);

        JLabel meterNumberText = new JLabel("");
        meterNumberText.setBounds(300, 80, 200, 22);
        meterNumberText.setFont(new Font("serif",Font.BOLD,18));
        add(meterNumberText);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 140, 200, 22);
        name.setFont(new Font("monospaced",Font.BOLD,20));
        add(name);


        JLabel nameText = new JLabel("");
        nameText.setBounds(300, 140, 200, 22);
        nameText.setFont(new Font("serif",Font.BOLD,18));
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35, 200, 200, 22);
        month.setFont(new Font("monospaced",Font.BOLD,20));
        add(month);


        searchmonthcho = new Choice();
        searchmonthcho.add("January");
        searchmonthcho.add("February");
        searchmonthcho.add("March");
        searchmonthcho.add("April");
        searchmonthcho.add("May");
        searchmonthcho.add("June");
        searchmonthcho.add("July");
        searchmonthcho.add("August");
        searchmonthcho.add("September");
        searchmonthcho.add("October");
        searchmonthcho.add("November");
        searchmonthcho.add("December");
        searchmonthcho.setBounds(300, 200, 150, 28);
        searchmonthcho.setFont(new Font("serif",Font.BOLD,18));
        add(searchmonthcho);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35, 260, 200, 22);
        unit.setFont(new Font("monospaced",Font.BOLD,20));
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300, 260, 200, 22);
        unitText.setFont(new Font("serif",Font.BOLD,18));
        add(unitText);

        JLabel totalBill = new JLabel("Total Bill");
        totalBill.setBounds(35, 320, 200, 22);
        totalBill.setFont(new Font("monospaced",Font.BOLD,20));
        add(totalBill);

        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(300, 320, 200, 22);
        totalBillText.setFont(new Font("serif",Font.BOLD,18));
        add(totalBillText);

        JLabel status = new JLabel("Status");
        status.setBounds(35, 380, 200, 22);
        status.setFont(new Font("monospaced",Font.BOLD,20));
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(300, 380, 200, 22);
        statusText.setFont(new Font("serif",Font.BOLD,18));
        statusText.setForeground(Color.red);
        add(statusText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            while (resultSet.next()) {
                meterNumberText.setText(meter);
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        searchmonthcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchmonthcho.getSelectedItem()+"'");
                    while (resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.black);
        pay.setForeground(Color.white);
        pay.setBounds(90,460,100,25);
        pay.setFont(new Font("serif",Font.BOLD,18));
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(240,460,100,25);
        back.setFont(new Font("serif",Font.BOLD,18));
        back.addActionListener(this);
        add(back);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==pay){
            try {
                database c = new database();
                c.statement .executeUpdate("update bill set status = 'Paid' where meter_no='"+meter+"' and month = '"+searchmonthcho.getSelectedItem()+"'");

            }catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        }else
            setVisible(false);
    }
    public static void main(String[]arg){
        new pay_bill("");
    }
}

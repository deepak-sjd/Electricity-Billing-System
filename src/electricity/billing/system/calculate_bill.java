package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class calculate_bill extends JFrame implements ActionListener {
    JLabel nameText,addressText;
    TextField unitText;
    Choice meternumCho,monthCho;
    JButton submit, cancel;

    calculate_bill(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(166, 189, 221, 255));
        add(panel);

        getContentPane().setBackground(new Color(129, 170, 109, 213));

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70,10,300,25);
        heading.setFont(new Font("serif",Font.BOLD,22));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(50,60,120,22);
        meternum.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meternum);

        meternumCho = new Choice();
        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while (resultSet.next()){
                meternumCho.add(resultSet.getString("meter_no"));
                meternumCho.setFont(new Font("serif",Font.PLAIN,17));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        meternumCho.setBounds(180,60,150,20);
        panel.add(meternumCho);

        JLabel name = new JLabel("Name");
        name.setBounds(50,110,100,20);
        name.setFont(new Font("serif",Font.BOLD,17));
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180,110,150,22);
        nameText.setFont(new Font("serif",Font.PLAIN,17));
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50,150,100,20);
        address.setFont(new Font("serif",Font.BOLD,17));
        panel.add(address);

        addressText = new JLabel("");
        addressText.setBounds(180,150,150,22);
        addressText.setFont(new Font("serif",Font.PLAIN,17));
        panel.add(addressText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no='"+meternumCho.getSelectedItem()+"' ");
            while (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        meternumCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meternumCho.getSelectedItem()+"' ");
                    while (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel unitConsumed = new JLabel("Unit Consumed");
        unitConsumed.setBounds(50,200,120,20);
        unitConsumed.setFont(new Font("serif",Font.BOLD,17));
        panel.add(unitConsumed);

        unitText = new TextField();
        unitText.setBounds(180,200,150,24);
        unitText.setFont(new Font("serif",Font.BOLD,17));
        panel.add(unitText);

        JLabel month = new JLabel("Month");
        month.setFont(new Font("serif",Font.BOLD,17));
        month.setBounds(50,250,100,20);
        panel.add(month);

        monthCho = new Choice();
        monthCho.add("January");
        monthCho.add("February");
        monthCho.add("March");
        monthCho.add("April");
        monthCho.add("May");
        monthCho.add("June");
        monthCho.add("July");
        monthCho.add("August");
        monthCho.add("September");
        monthCho.add("October");
        monthCho.add("November");
        monthCho.add("December");
        monthCho.setBounds(180,250,150,22);
        monthCho.setFont(new Font("serfi",Font.PLAIN,17));
        panel.add(monthCho);


        submit = new JButton("Submit");
        submit.setBounds(65,310,100,25);
        submit.setFont(new Font("serif",Font.BOLD,18));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setBounds(210,310,100,25);
        cancel.setFont(new Font("serif",Font.BOLD,18));
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        // here divide panel two parts
        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(310,250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel, "East");

        setSize(710,400);
        setLocation(500,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==submit){
            String smeterNo = meternumCho.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthCho.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(sunit);
            String query_tax = "select * from tax";
            try {
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_tax);
                while (resultSet.next()){
                    totalBill += units*Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("service_tax"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            }catch (Exception E){
                E.printStackTrace();
            }
            String query_total_bill = "insert into bill values('"+smeterNo+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";
            try {
                database c = new database();
                c.statement.executeUpdate(query_total_bill);

                JOptionPane.showMessageDialog(null,"Customer Bill Updated Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }
    public static void main(String[]arg){
        new calculate_bill();
    }
}

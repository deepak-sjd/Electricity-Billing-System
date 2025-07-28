package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class  update_information extends JFrame implements ActionListener {
    JLabel nameText;
    JTextField addressText,cityText,stateText,emailText,phoneText;
    String meter;
    JButton update,cancel;
    update_information(String meter){
        this.meter =meter;

        setSize(730,450);
        setLocation(500,200);
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("monospaced",Font.BOLD,23));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        name.setFont(new Font("serif",Font.BOLD,17));
        add(name);

        nameText = new JLabel("");
        nameText.setBounds(150,70,200,23);
        nameText.setFont(new Font("serif",Font.BOLD,17));
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,110,120,20);
        meterNo.setFont(new Font("serif",Font.BOLD,17));
        add(meterNo);

        JLabel meterText = new JLabel("");
        meterText.setBounds(150,110,100,20);
        meterText.setFont(new Font("serif",Font.BOLD,17));
        add(meterText);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,20);
        address.setFont(new Font("serif",Font.BOLD,17));
        add(address);

        addressText = new JTextField();
        addressText.setBounds(150,150,200,23);
        addressText.setFont(new Font("serif",Font.BOLD,17));
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        city.setFont(new Font("serif",Font.BOLD,17));
        add(city);

        cityText =new JTextField();
        cityText.setBounds(150,190,200,23);
        cityText.setFont(new Font("serif",Font.BOLD,17));
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        state.setFont(new Font("serif",Font.BOLD,17));
        add(state);

        stateText = new JTextField();
        stateText.setBounds(150,230,200,23);
        stateText.setFont(new Font("serif",Font.BOLD,17));
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,20);
        email.setFont(new Font("serif",Font.BOLD,17));
        add(email);

        emailText = new JTextField();
        emailText.setBounds(150,270,200,23);
        emailText.setFont(new Font("serif",Font.BOLD,17));
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,20);
        phone.setFont(new Font("serif",Font.BOLD,17));
        add(phone);

        phoneText = new JTextField();
        phoneText.setBounds(150,310,200,23);
        phoneText.setFont(new Font("serif",Font.BOLD,17));
        add(phoneText);

        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            if (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                meterText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        update= new JButton("Update");
        update.setBounds(50,360,120,25);
        update.setBackground(new Color(33,106,145));
        update.setFont(new Font("serif",Font.BOLD,18));
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,360,120,25);
        cancel.setBackground(new Color(33,106,145));
        cancel.setFont(new Font("serif",Font.BOLD,18));
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update_info.png"));
        Image image = imageIcon.getImage().getScaledInstance(350,350,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imgLabel = new JLabel(imageIcon1);
        imgLabel.setBounds(370,15,350,350);
        add(imgLabel);


        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==update){
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try {
                database c = new database();
                c.statement.executeUpdate("update new_customer set address = '"+saddress+"', city = '"+scity+"', state = '"+sstate+"', email = '"+semail+"',phone_no = '"+sphone+"' where meter_no = '"+meter+"'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[]arg){
        new update_information("");

    }
}

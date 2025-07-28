package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.Extension;
import java.sql.ResultSet;

public class view_information extends JFrame implements ActionListener {
    String view;
    JButton cancel;

    view_information(String view){
        this.view = view;
        setBounds(350,150,800,650);
        getContentPane().setBackground(new Color(0xF7AEBAE3, true));
        setLayout(null);


        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,23));
        add(heading);

        JLabel nameLabel1 = new JLabel("Name");
        nameLabel1.setBounds(70,80,100,25);
        nameLabel1.setFont(new Font("serif",Font.BOLD,18));
        add(nameLabel1);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBounds(200,80,150,25);
        nameLabelText.setFont(new Font("serif",Font.BOLD,17));
        add(nameLabelText);


        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(70,140,150,25);
        meterno.setFont(new Font("serif",Font.BOLD,18));
        add(meterno);

        JLabel meternoText = new JLabel("");
        meternoText.setBounds(200,140,150,25);
        meternoText.setFont(new Font("serif",Font.BOLD,17));
        add(meternoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,150,25);
        address.setFont(new Font("serif",Font.BOLD,18));
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200,200,150,25);
        addressText.setFont(new Font("serif",Font.BOLD,17));
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,25);
        city.setFont(new Font("serif",Font.BOLD,18));
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200,260,150,25);
        cityText.setFont(new Font("serif",Font.BOLD,17));
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,25);
        state.setFont(new Font("serif",Font.BOLD,18));
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,150,25);
        stateText.setFont(new Font("serif",Font.BOLD,17));
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,25);
        email.setFont(new Font("serif",Font.BOLD,18));
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,140,150,25);
        emailText.setFont(new Font("serif",Font.BOLD,17));
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,25);
        phone.setFont(new Font("serif",Font.BOLD,18));
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,200,150,25);
        phoneText.setFont(new Font("serif",Font.BOLD,17));
        add(phoneText);


        try {
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select *from new_customer where meter_no = '"+view+"'");
            if (resultSet.next()){
                nameLabelText.setText(resultSet.getString("name"));
                meternoText.setText(resultSet.getString("meter_no"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        cancel = new JButton("Cancel");
        cancel.setBounds(250,350,120,27);
        cancel.setBackground(new Color(174, 32, 81));
        cancel.setFont(new Font("serif",Font.BOLD,20));
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);


        ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("icon/view_info.png"));
        Image a2 = a1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(a2);
        JLabel img = new JLabel(i3);
        img.setBounds(100,320,600,300);
        add(img);

        setVisible(true);

    }

@Override
public void actionPerformed(ActionEvent e){
        if (e.getSource()==cancel){
            setVisible(false);
        }
}
    public static void main(String[]arg){
        new view_information("");
    }
}

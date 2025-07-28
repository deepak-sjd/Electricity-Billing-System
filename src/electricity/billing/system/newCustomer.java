package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newCustomer extends JFrame implements ActionListener {

    JLabel heading,customerName,meterNum,meterNumText,address,city,state,email,phone;
    JButton next,cancel;
    TextField nameText,addressText,cityText,stateText,emailText,phoneText;

    newCustomer(){

        super("New Customer");
        setSize(750,450);
        setLocation(500,200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);
        panel.setBackground(new Color(137, 195, 184, 255));
        add(panel);

        getContentPane().setBackground(new Color(109, 148, 184));

        heading = new JLabel("New Customer");
        heading.setBounds(120,10,200,22);
        heading.setFont(new Font("serif",Font.BOLD,22));
        panel.add(heading);

        customerName = new JLabel("New Customer");
        customerName.setBounds(50,60,110,20);
        customerName.setFont(new Font("serif",Font.BOLD,17));
        panel.add(customerName);

        nameText = new TextField();
        nameText.setBounds(190,60,170,23);
        nameText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(nameText);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,100,110,20);
        meterNum.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterNum);

        meterNumText = new JLabel("");
        meterNumText.setBounds(190,100,170,23);
        meterNumText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(meterNumText);

        // here we generate randome number for meter number
        Random ran = new Random();
        Long number = ran.nextLong()%10000; // jitne zero denge utne number generate hogi
        meterNumText.setText(""+ Math.abs(number));

        address = new JLabel("Address ");
        address.setBounds(50,140,110,20);
        address.setFont(new Font("serif",Font.BOLD,17));
        panel.add(address);

        addressText = new TextField();
        addressText.setBounds(190,140,170,23);
        addressText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(addressText);

        city = new JLabel("City ");
        city.setBounds(50,180,110,20);
        city.setFont(new Font("serif",Font.BOLD,17));
        panel.add(city);

        cityText = new TextField();
        cityText.setBounds(190,180,170,23);
        cityText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(cityText);

        state = new JLabel("State ");
        state.setBounds(50,220,110,20);
        state.setFont(new Font("serif",Font.BOLD,17));
        panel.add(state);

        stateText = new TextField();
        stateText.setBounds(190,220,170,23);
        stateText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(stateText);

        email = new JLabel("Email ");
        email.setBounds(50,260,110,20);
        email.setFont(new Font("serif",Font.BOLD,17));
        panel.add(email);

        emailText = new TextField();
        emailText.setBounds(190,260,170,23);
        emailText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(emailText);

        phone = new JLabel("Phone ");
        phone.setBounds(50,300,110,20);
        phone.setFont(new Font("serif",Font.BOLD,17));
        panel.add(phone);

        phoneText = new TextField();
        phoneText.setBounds(190,300,170,23);
        phoneText.setFont(new Font("railway",Font.BOLD,16));
        panel.add(phoneText);

        next = new JButton("Next");
        next.setBounds(80,360,100,25);
        next.setFont(new Font("serif",Font.BOLD,18));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel = new JButton("Cancel");
        cancel.setBounds(210,360,100,25);
        cancel.setFont(new Font("serif",Font.BOLD,18));
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomer1.png"));
        Image i2 = i1.getImage().getScaledInstance(320,320,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLable = new JLabel(i3);
        add(imgLable, "West");

        setVisible(true);
    }

@Override
public void  actionPerformed(ActionEvent e){
        if (e.getSource()==next){
            String sname = nameText.getText();
            String smeter =meterNumText.getText();
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String eemail = emailText.getText();
            String sphone = phoneText.getText();


            String query_customer = "insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
            String query_signup = "insert into Signup values('"+smeter+"','','"+sname+"','','')";

            try {
                database c = new database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterInfo(smeter);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
}
 public static void main(String[]arg){
        new newCustomer();
    }
}

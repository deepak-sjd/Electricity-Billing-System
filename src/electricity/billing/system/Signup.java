package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {

    Choice loginASCho;
    TextField meterText,employerText,userNameText,nameText,passwordText;
    JButton create, back;

    Signup() {

        super("Signup Page"); // heading
        getContentPane().setBackground(new Color(154, 188, 236));

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(15, 25, 155, 25);
        createAs.setFont(new Font("railway", Font.BOLD, 17));
        add(createAs);

        loginASCho = new Choice();
        loginASCho.add("Admin");
        loginASCho.add("Customer");
        loginASCho.setFont(new Font("railway", Font.BOLD, 15));
        loginASCho.setBounds(170, 25, 180, 25);
        add(loginASCho);


        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 75, 140, 23);
        meterNo.setFont(new Font("railway", Font.BOLD, 16));
        meterNo.setVisible(false);
        add(meterNo);

        meterText = new TextField();
        meterText.setBounds(170, 75, 180, 23);
        meterText.setFont(new Font("railway", Font.BOLD, 15));
        meterText.setVisible(false);
        add(meterText);

        JLabel employer = new JLabel("Employer ID");
        employer.setBounds(30, 75, 140, 23);
        employer.setFont(new Font("railway", Font.BOLD, 17));
        employer.setVisible(true);
        add(employer);

        employerText = new TextField();
        employerText.setBounds(170, 75, 180, 23);
        employerText.setFont(new Font("railway", Font.BOLD, 15));
        employerText.setVisible(true);
        add(employerText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(30, 120, 125, 25);
        userName.setFont(new Font("railway", Font.BOLD, 17));
        add(userName);

        userNameText = new TextField();
        userNameText.setBounds(170, 120, 180, 23);
        userNameText.setFont(new Font("railway", Font.BOLD, 15));
        add(userNameText);


        JLabel name = new JLabel("Name");
        name.setBounds(30, 164, 130, 25);
        name.setFont(new Font("railway", Font.BOLD, 17));
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170, 165, 180, 23);
        nameText.setFont(new Font("railway", Font.BOLD, 15));
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){

            }
            @Override
            public void focusLost(FocusEvent e){
                try {
                    database c= new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup where meter_no ='"+meterText.getText()+"'");
                    if (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }

            }
        });

        JLabel password = new JLabel("Password");
        password.setBounds(30, 203, 125, 25);
        password.setFont(new Font("railway", Font.BOLD, 17));
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170, 204, 180, 23);
        passwordText.setFont(new Font("railway", Font.BOLD, 15));
        add(passwordText);


        loginASCho.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = loginASCho.getSelectedItem();
                if (user.equals("Customer")) {
                    employer.setVisible(false);
                    nameText.setEditable(false);
                  //  nameText.setEditable(false);
                    employerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);

                } else {
                    employer.setVisible(true);
                    employerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                   // nameText.setEditable(true);
                }

            }
        });


        create = new JButton("Create");
        create.setBackground(new Color(72, 129, 216));
        create.setFont(new Font("railway", Font.BOLD, 15));
        create.setForeground(Color.black);
        create.setBounds(50, 275, 100, 28);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(72, 129, 216));
        back.setFont(new Font("railway", Font.BOLD, 15));
        back.setForeground(Color.black);
        back.setBounds(180, 275, 100, 28);
        back.addActionListener(this);
        add(back);


        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(280, 280, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(380, 30, 280, 280);
        add(boyLabel);


        setSize(700, 400);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }
        // add action perform
    @Override
    public void actionPerformed (ActionEvent e){

        if (e.getSource()==create){
            String sloginAs= loginASCho.getSelectedItem();
            String suserName =userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try {
                database c = new database();
                String query = null;
                if (sloginAs.equals("Admin")){
                    query= "INSERT INTO Signup (meter_no, username, name, password, usertype) VALUES ('"+smeter+"','"+suserName+"','"+sname+"','"+spassword+"','"+sloginAs+"')";
                } else {
                    ResultSet rs = c.statement.executeQuery("SELECT * FROM Signup WHERE meter_no = '" + smeter + "'");
                    if (rs.next()) {
                        query = "UPDATE Signup SET username = '" + suserName + "', password = '" + spassword + "', usertype = '" + sloginAs + "' WHERE meter_no = '" + smeter + "'";
                    } else {
                        query = "INSERT INTO Signup (meter_no, username, name, password, usertype) VALUES ('"+smeter+"','"+suserName+"','"+sname+"','"+spassword+"','"+sloginAs+"')";
                    }
                }
                c.statement.executeUpdate(query);

                  JOptionPane.showMessageDialog(null,"Account Created Successfully");
                  setVisible(false);
                  new Login();

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[]arg){
        new Signup();
    }
}

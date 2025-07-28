package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    // globally declare  because we use outside class also
    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login(){
        super("Login");
        getContentPane().setBackground(Color.LIGHT_GRAY);


        JLabel heading = new JLabel("Welcome to Login Page");
        heading.setBounds(280,10,300,26);
        heading.setFont(new Font("serif",Font.BOLD,24));
        add(heading);


        JLabel username = new JLabel("UserName");
        username.setBounds(340,60,100,25);
        username.setFont(new Font("serif",Font.BOLD,17));
        add(username);

        userText = new JTextField();
        userText.setBounds(440,60,180,25);
        userText.setFont(new Font("monospaced",Font.PLAIN,17));
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(340,100,100,25);
        password.setFont(new Font("serif",Font.BOLD,17));
        add(password);

        passwordText = new JPasswordField();
        passwordText.setBounds(440,100,180,25);
        passwordText.setFont(new Font("monospaced",Font.PLAIN,17));
        add(passwordText);

        JLabel login = new JLabel("Login In As");
        login.setBounds(340,140,100,25);
        login.setFont(new Font("serif",Font.BOLD,17));
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.setFont(new Font("monospaced",Font.BOLD,17));
        loginChoice.add("Customer");

        loginChoice.setBounds(440,140,180,25);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(340,200,108,30);
         loginButton.setFont(new Font("serif",Font.BOLD,17));
         loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(465,200,108,30);
        cancelButton.setFont(new Font("serif",Font.BOLD,17));
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(395,245,108,30);
        signupButton.setFont(new Font("serif",Font.BOLD,17));
        signupButton.addActionListener(this);
        add(signupButton);


        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(285,285,Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileOne);
        profileLabel.setBounds(10,20,285,285);
        add(profileLabel);

        setSize(700,400);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource()==loginButton){
            String susername = userText.getText();
            String spassword = new String(passwordText.getText());  // Get password from JPasswordField
            String suser = loginChoice.getSelectedItem();

            System.out.println("Logging in with Username: " + susername + ", Password: " + spassword + ", UserType: " + suser); // Debugging

            try {
                database c = new database();
                String query = "SELECT * FROM Signup WHERE username = '" + susername + "' AND password = '" + spassword + "' AND usertype = '" + suser + "'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser,meter);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }
            } catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==cancelButton){
            setVisible(false);
        } else if (e.getSource()==signupButton){
            setVisible(false);
            new Signup();
        }
    }


    public static void main(String[]arg){
        new Login();

    }
}

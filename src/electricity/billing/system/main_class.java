package electricity.billing.system;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    String acctype; // to accept from login
    String meter_pass;
    // constructor class create
    main_class(String acctype,String meter_pass){
        this.acctype =acctype; // here we globally declared
        this.meter_pass=meter_pass;

        setExtendedState(JFrame.MAXIMIZED_BOTH); // here we not set layout size direct take full screen according to system screen size

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/main1.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1530,830,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLable = new JLabel(imageIcon2);
        add(imageLable);

        //*******************************create menu bar**************************************
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu(" Menu");
        menu.setFont(new Font("monospaced",Font.BOLD,24));
       // menuBar.add(menu);  // this one is add-bottom of the page to add

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("serif",Font.BOLD,22));
        menu.add(newCustomer);
        // add icons for new customer in JMenuItem
        ImageIcon customerImage = new ImageIcon(ClassLoader.getSystemResource("icon/newCustomer.png"));
        Image customerImg = customerImage.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newCustomer.addActionListener(this);
        newCustomer.setIcon(new ImageIcon(customerImg)) ;

        // customer details
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon customerDetailsImage = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image customerDetailsImg = customerDetailsImage.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(customerDetailsImg));
        customerDetails.addActionListener(this);
        menu.add(customerDetails);

        // Deposit Details
        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon depositImage = new ImageIcon(ClassLoader.getSystemResource("icon/deposit.png"));
        Image depositImg = depositImage.getImage().getScaledInstance(22,22,Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(depositImg));
        depositDetails.addActionListener(this);
        menu.add(depositDetails);

        // calculator for billing
        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon  calculateBillImage = new ImageIcon(ClassLoader.getSystemResource("icon/calculateBill.png"));
        Image calculateBillIma = calculateBillImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(calculateBillIma));
        calculateBill.addActionListener(this);
        menu.add(calculateBill);

         //***************************************create menu for information**************************************8
        JMenu info = new JMenu("  Information");
        info.setFont(new Font("monospaced",Font.BOLD,24));
      //  menuBar.add(info);

     // update information button
        JMenuItem updateInfo = new JMenuItem("Update Information");
        updateInfo.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon updateImage = new ImageIcon(ClassLoader.getSystemResource("icon/updateInfoImage.png"));
        Image updateIma = updateImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(updateIma));
        updateInfo.addActionListener(this);
        info.add(updateInfo);

     //View information
        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon viewInfoImage = new ImageIcon(ClassLoader.getSystemResource("icon/viewInformation.png"));
        Image viewInfoIma = viewInfoImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(viewInfoIma));
        viewInfo.addActionListener(this);
        info.add(viewInfo);


        //************************** create User menu**********************************************

        JMenu user = new JMenu("  User");
        user.setFont(new Font("monospaced",Font.BOLD,24));
       // menuBar.add(user);

        // create pay bill button
       JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon payBillImage = new ImageIcon(ClassLoader.getSystemResource("icon/payBill.png"));
        Image payBillImg = payBillImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(payBillImg));
        payBill.addActionListener(this);
        user.add(payBill);


        // create button for bill details
        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon billDetailsImage = new ImageIcon(ClassLoader.getSystemResource("icon/billDetails.png"));
        Image billDetailsImg = billDetailsImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(billDetailsImg));
        billDetails.addActionListener(this);
        user.add(billDetails);

        //****************************************Create Bill Menu System***************

        JMenu bill = new JMenu("  Bill");
        bill.setFont(new Font("monospaced",Font.BOLD,24));
     //   menuBar.add(bill);

        // create Generate Bill button
        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon genBillImage = new ImageIcon(ClassLoader.getSystemResource("icon/generateBill.png"));
        Image genBillImg = genBillImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        genBill.setIcon(new ImageIcon(genBillImg));
        genBill.addActionListener(this);
        bill.add(genBill);

        //********************************************Create Utility Menu Option *************************

        JMenu utility = new JMenu("  Utility");
        utility.setFont(new Font("monospaced",Font.BOLD,24));
       // menuBar.add(utility);

        // create notepad option button
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon notepadImage = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image notepadImg = notepadImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadImg));
        notepad.addActionListener(this);
        utility.add(notepad);

        // create Calculator menu
        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon calculatorImage = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image calculatorImg = calculatorImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatorImg));
        calculator.addActionListener(this);
        utility.add(calculator);

        //**************************** Create Exit Option *********************************

        JMenu exit = new JMenu("  Exit");
        exit.setFont(new Font("monospaced",Font.BOLD,24));
       // menuBar.add(exit);

        // create Exit option
        JMenuItem eexit = new JMenuItem("Exit..");
        eexit.setFont(new Font("serif",Font.BOLD,22));
        ImageIcon eexitImage = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image eexitImg = eexitImage.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        eexit.setIcon(new ImageIcon(eexitImg));
        eexit.addActionListener(this);
        exit.add(eexit);


     if (acctype.equals("Admin")){
         menuBar.add(menu);
     } else {
         menuBar.add(bill);
         menuBar.add(user);
         menuBar.add(info);
     }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String msg = e.getActionCommand();
          if (msg.equals("New Customer")){
           new newCustomer();
        }else if (msg.equals("Customer Details")){
            new customer_details();
        }else if (msg.equals("Deposit Details")){
            new deposite_details();
        }else if (msg.equals("Calculate Bill")){
            new calculate_bill();
        }else if (msg.equals("View Information")){
            new view_information(meter_pass);
        }else if (msg.equals("Update Information")){
              new update_information(meter_pass);
        }else if (msg.equals("Bill Details")){
              new bill_details(meter_pass);
          }else if (msg.equals("Calculator")){
              try {
                  Runtime.getRuntime().exec("calc.exe");
              }catch (Exception E){
                  E.printStackTrace();
              }
          }else if (msg.equals("Notepad")){
              try {
                  Runtime.getRuntime().exec("notepad.exe");
              }catch (Exception E){
                  E.printStackTrace();
              }
          }else if (msg.equals("Exit..")){
              setVisible(false);
              new Login();
          } else if (msg.equals("Pay Bill")) {
              new pay_bill(meter_pass);
          }else if (msg.equals("Generate Bill")){
              new generate_bill(meter_pass);
          }
    }
    public static void main(String[]arg){
        new main_class("","");

    }
}

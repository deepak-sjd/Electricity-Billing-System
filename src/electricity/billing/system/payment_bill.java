package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    payment_bill(String meter){
        this.meter = meter;

        setTitle("Pay Your Bill - Electricity Billing System");
        setLayout(null);

        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        getContentPane().setBackground(new Color(0x9C9CE8));

        try {
            j.setPage("https://www.paytm.com");
            j.setBounds(400,150,800,600);
        }catch (Exception E){
            E.printStackTrace();
            j.setContentType("text/html");
            j.setText("<html>h2 style = 'color:red; '> Error Loading Payment Page! </h2></html>");
        }

        JScrollPane pane = new JScrollPane(j);
        pane.setBounds(20,60,750,450);
        add(pane);

        back = new JButton("Back");
        back.setBounds(350,520,100,30);
        back.setFont(new Font("serif",Font.BOLD,18));
        back.addActionListener(this);
        add(back);

        setSize(800,600);
        //setLocation(400,150);
        setLocationRelativeTo(null); // centers the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setVisible(false);
        new pay_bill(meter);
    }
    public static void main(String[]arg){
        new payment_bill("");
    }

}

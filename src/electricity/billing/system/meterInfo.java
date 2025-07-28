package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocCho, meterTypeCho, phaseCodeCho, billTypCho;
    JButton submit;
    String meterNumber;
    // here create constructor class same as class name
    meterInfo(String meterNumber){ // here we get the meter number from new customer
        this.meterNumber=meterNumber; // globally declare
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(192, 161, 223, 255));
        add(panel);

        getContentPane().setBackground(new Color(73, 92, 94, 239));

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(150,10,200,22);
        heading.setFont(new Font("serif",Font.BOLD,24));
        panel.add(heading);

        JLabel meternumber = new JLabel("Meter Number");
        meternumber.setBounds(50,55,120,20);
        meternumber.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meternumber);

        JLabel meterNumberText = new JLabel(meterNumber);
        meterNumberText.setBounds(180,55,150,20);
        meterNumberText.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterNumberText);

        JLabel meterLoc = new JLabel("Meter Number");
        meterLoc.setBounds(50,95,120,20);
        meterLoc.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterLoc);

        meterLocCho = new Choice();
        meterLocCho.add("Outside");
        meterLocCho.add("Inside");
        meterLocCho.setBounds(180,95,150,20);
        meterLocCho.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterLocCho);

        JLabel meterTyp = new JLabel("Meter Type");
        meterTyp.setBounds(50,135,100,20);
        meterTyp.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterTyp);

        meterTypeCho = new Choice();
        meterTypeCho.add("Electric Meter");
        meterTypeCho.add("Solar Meter");
        meterTypeCho.add("Smart Meter");
        meterTypeCho.setBounds(180,135,150,20);
        meterTypeCho.setFont(new Font("serif",Font.BOLD,17));
        panel.add(meterTypeCho);


        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,175,100,20);
        phaseCode.setFont(new Font("serif",Font.BOLD,17));
        panel.add(phaseCode);

        phaseCodeCho = new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180,175,150,20);
        phaseCodeCho.setFont(new Font("serif",Font.BOLD,17));
        panel.add(phaseCodeCho);


        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50,215,100,20);
        billtyp.setFont(new Font("serif",Font.BOLD,17));
        panel.add(billtyp);

        billTypCho = new Choice();
        billTypCho.add("Normal");
        billTypCho.add("Industrial");
        billTypCho.setBounds(180,215,150,20);
        billTypCho.setFont(new Font("serif",Font.BOLD,17));
        panel.add(billTypCho);


        JLabel day = new JLabel("30 Days Billing Time...");
        day.setBounds(50,260,300,20);
        day.setFont(new Font("serif",Font.BOLD,17));
        panel.add(day);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,300,100,20);
        note.setFont(new Font("serif",Font.BOLD,18));
        panel.add(note);

        JLabel note1 = new JLabel("By default bill is calculated for 30 days only.");
        note1.setBounds(50,321,420,20);
        note1.setFont(new Font("serif",Font.BOLD,17));
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(180,360,100,25);
        submit.setFont(new Font("serif",Font.BOLD,17));
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
       submit.addActionListener(this);
        panel.add(submit);

        // this is for divide layout into two part with border
        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/meterInformation.png"));
        Image i2 = i1.getImage().getScaledInstance(270,270,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgLabel= new JLabel(i3);
        add(imgLabel, "East");


        setSize(730,450);
        setLocation(500,200);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==submit){
            String smeterNum = meterNumber;
            String smeterLoc = meterLocCho.getSelectedItem();
            String  smeterType =meterTypeCho.getSelectedItem();
            String sphaseCode = phaseCodeCho.getSelectedItem();
            String sbillType = billTypCho.getSelectedItem();
            String sday = "30";

            String query_meterInfo = "insert into meter_info values('"+smeterNum+"','"+smeterLoc+"','"+smeterType+"','"+sphaseCode+"','"+sbillType+"','"+sday+"')";
            try {
                database c = new database();
                c.statement.executeUpdate(query_meterInfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submitted Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[]arg){
        new meterInfo("");

    }
}

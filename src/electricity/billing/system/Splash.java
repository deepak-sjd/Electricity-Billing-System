package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Splash extends JFrame { // class extend with JFrame for swing package

    // make constructor same as class name
    Splash(){

        super("Welcome");
        getContentPane().setBackground(Color.black);

        // Adding a custom title label
        JLabel title = new JLabel(" Welcome to Electricity Billing System");
        title.setBounds(185, 3, 500, 30); // Set position and size
        title.setFont(new Font("Arial", Font.BOLD, 22)); // Set font style and size
        title.setForeground(Color.white); // Set text color
        add(title);


      // Load and scale the image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash3.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(800,400,Image.SCALE_DEFAULT);// this is for image size make perfect according to JFrame size


        // convert back to imageIcon and add to Jlabel
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel((imageIcon2));
        add(imageLabel);setSize(800,520);
    setLocation(500,200);
    setVisible(true); // without this not showing page as output and always every thing upper to set visibility otherwise not show on frame

        try {
           // Thread.sleep(3000);// screen close after 3 second
            Thread.sleep(5000);
            setVisible(false);

            // login page open from here after 3 second
            new Login();

        }catch (Exception e){
            e.printStackTrace();
           // System.out.println(e); //both are same exception print but e.print is more-better
        }
    }

    public  static void main(String[]arg){
        new Splash(); // create object
    }
}

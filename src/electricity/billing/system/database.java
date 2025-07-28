package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {

    Connection connection;
    Statement statement;
    database(){
        // always put database inside the try catch because the chance of exception is more when add database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Billing_System","root","@Deepak74795");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

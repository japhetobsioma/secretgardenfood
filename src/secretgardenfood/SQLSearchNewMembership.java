package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * SQL Search New Membership
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLSearchNewMembership {

    public static void toSQLSearch() {
        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT CardNumber "
                    + "FROM premium "
                    + "WHERE CardNumber = '" + NwMembership.getPremCus().getPremiumCardNumber() + "'");

            if (rs.next() == false) {
                NwMembership.toPrint();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("New Membership");
                alert.setHeaderText("ERROR: Card Number already taken");
                alert.setContentText("Enter a new unique card number");

                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                selectStmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

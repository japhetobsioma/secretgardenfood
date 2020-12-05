package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * SQL Search Renew Membership
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLSearchRenewMembership {

    public static void toSQLSearch() {
        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT cus.Name, cus.Phone, prem.CardNumber, prem.RegistrationDate, prem.ExpirationDate, prem.PremiumID, cus.CustomerID "
                    + "FROM customer cus, premium prem "
                    + "WHERE prem.CardNumber = '" + RnwMembership.getTxtSearch().getText() + "' "
                    + "AND cus.CustomerID = prem.CustomerID");

            if (rs.next() == false) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Renew Membership");
                alert.setHeaderText("No result found");
                alert.setContentText("Enter correct card number");

                alert.showAndWait();
            } else {
                do {
                    RnwMembership.getTxtName().setText(rs.getString(1));
                    RnwMembership.getPremCusRenew().setCustomerName(rs.getString(1));
                    RnwMembership.getTxtName().setDisable(false);
                    RnwMembership.getTxtName().setEditable(false);

                    RnwMembership.getTxtPhone().setText(rs.getString(2));
                    RnwMembership.getPremCusRenew().setCustomerPhone(rs.getString(2));
                    RnwMembership.getTxtPhone().setDisable(false);
                    RnwMembership.getTxtPhone().setEditable(false);

                    RnwMembership.getTxtCard().setText(rs.getString(3));
                    RnwMembership.getPremCusRenew().setPremiumCardNumber(rs.getString(3));
                    RnwMembership.getTxtCard().setDisable(false);
                    RnwMembership.getTxtCard().setEditable(false);

                    RnwMembership.getTxtReg().setText(rs.getString(4));
                    RnwMembership.getPremCusRenew().setPremiumRegDate(rs.getString(4));
                    RnwMembership.getTxtReg().setDisable(false);
                    RnwMembership.getTxtReg().setEditable(false);

                    RnwMembership.getTxtExp().setText(rs.getString(5));
                    RnwMembership.getPremCusRenew().setPremiumExpireDate(rs.getString(5));
                    RnwMembership.getTxtExp().setDisable(false);
                    RnwMembership.getTxtExp().setEditable(false);

                    RnwMembership.getPremCusRenew().setPremiumID(rs.getString(6));
                    RnwMembership.getPremCusRenew().setCustomerID(rs.getString(7));
                } while (rs.next());

                if (new SimpleDateFormat("MM/dd/yyyy").parse(RnwMembership.getTxtExp().getText()).after(new Date())) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Renew Membership");
                    alert.setHeaderText("Premium Membership Card Status: NOT EXPIRED");
                    alert.setContentText("You do not need to renew your membership card");

                    alert.showAndWait();
                    RnwMembership.getBtnRenew().setDisable(true);
                } else if (new SimpleDateFormat("MM/dd/yyyy").parse(RnwMembership.getTxtExp().getText()).before(new Date())) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Renew Membership");
                    alert.setHeaderText("Premium Membership Card Status: EXPIRED");
                    alert.setContentText("You need to renew your membership card");

                    alert.showAndWait();

                    RnwMembership.getBtnRenew().setDisable(false);
                }
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

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
 * SQL Search Premium Customer
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLSearchPremCustomer {

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
                    + "WHERE prem.CardNumber = '" + PremCustomer.getTxtSearch().getText() + "' "
                    + "AND cus.CustomerID = prem.CustomerID");

            if (rs.next() == false) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Reservation - Premium Customer");
                alert.setHeaderText("No result found");
                alert.setContentText("Enter correct card number");

                alert.showAndWait();

                PremCustomer.getBtnNext().setDisable(true);
                PremCustomer.getTxtResInfo().setDisable(true);
                PremCustomer.getTxtNumPer().setDisable(true);
            } else {
                do {
                    PremCustomer.getTxtName().setText(rs.getString(1));
                    PremCustomer.getNewCustomer().setCustomerName(rs.getString(1));
                    PremCustomer.getTxtName().setDisable(false);
                    PremCustomer.getTxtName().setEditable(false);

                    PremCustomer.getTxtPhone().setText(rs.getString(2));
                    PremCustomer.getNewCustomer().setCustomerPhone(rs.getString(2));
                    PremCustomer.getTxtPhone().setDisable(false);
                    PremCustomer.getTxtPhone().setEditable(false);

                    PremCustomer.getTxtCard().setText(rs.getString(3));
                    PremCustomer.getPremiumCus().setPremiumCardNumber(rs.getString(3));
                    PremCustomer.getTxtCard().setDisable(false);
                    PremCustomer.getTxtCard().setEditable(false);

                    PremCustomer.getTxtReg().setText(rs.getString(4));
                    PremCustomer.getPremiumCus().setPremiumRegDate(rs.getString(4));
                    PremCustomer.getTxtReg().setDisable(false);
                    PremCustomer.getTxtReg().setEditable(false);

                    PremCustomer.getTxtExp().setText(rs.getString(5));
                    PremCustomer.getPremiumCus().setPremiumExpireDate(rs.getString(5));
                    PremCustomer.getTxtExp().setDisable(false);
                    PremCustomer.getTxtExp().setEditable(false);

                    PremCustomer.getPremiumCus().setPremiumID(rs.getString(6));
                    PremCustomer.getNewCustomer().setCustomerID(rs.getString(7));
                    PremCustomer.getNewCustomer().setCustomerType("Premium");
                } while (rs.next());

                if (new SimpleDateFormat("MM/dd/yyyy").parse(PremCustomer.getTxtExp().getText()).after(new Date())) {
                    PremCustomer.getBtnNext().setDisable(false);
                    PremCustomer.getTxtResInfo().setDisable(false);
                    PremCustomer.getTxtNumPer().setDisable(false);
                } else if (new SimpleDateFormat("MM/dd/yyyy").parse(PremCustomer.getTxtExp().getText()).before(new Date())) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Reservation - Premium Customer");
                    alert.setHeaderText("Premium Membership Card Status: EXPIRED");
                    alert.setContentText("You need to renew your membership card first");

                    alert.showAndWait();

                    PremCustomer.getBtnNext().setDisable(true);
                    PremCustomer.getTxtResInfo().setDisable(true);
                    PremCustomer.getTxtNumPer().setDisable(true);
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

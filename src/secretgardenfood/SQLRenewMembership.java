package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQL Renew Membership
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLRenewMembership {

    public static void toSQL() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            stmt = connection.createStatement();

            stmt.execute("UPDATE premium "
                    + "SET ExpirationDate = '" + RnwMembership.getPremCusRenew().getPremiumExpireDate() + "' "
                    + "WHERE PremiumID = '" + RnwMembership.getPremCusRenew().getPremiumID() + "'");

            stmt.execute("INSERT INTO applyMembership VALUES ("
                    + "'" + RnwMembership.getNewApply().getApplyMembershipID() + "', "
                    + "'" + RnwMembership.getPremCusRenew().getPremiumID() + "', "
                    + "'" + RnwMembership.getNewApply().getType() + "', "
                    + "'" + RnwMembership.getNewApply().getDate() + "', "
                    + "'" + RnwMembership.getNewApply().getCost() + "')");

            stmt.execute("INSERT INTO receipt VALUES ("
                    + "'" + RnwMembership.getNewReceipt().getReceiptNo() + "', "
                    + "'" + RnwMembership.getNewReceipt().getReceiptDate() + "', "
                    + "'" + RnwMembership.getNewReceipt().getReceiptType() + "', "
                    + "'" + RnwMembership.getNewReceipt().getReceiptTotalCost() + "', "
                    + "'" + RnwMembership.getNewApply().getApplyMembershipID() + "', "
                    + "NULL)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

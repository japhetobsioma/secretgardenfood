package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQL New Membership
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLNewMembership {

    public static void toSQL() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            stmt = connection.createStatement();

            stmt.execute("INSERT INTO customer VALUES ("
                    + "'" + NwMembership.getPremCus().getCustomerID() + "',"
                    + "'" + NwMembership.getPremCus().getCustomerName() + "',"
                    + "'" + NwMembership.getPremCus().getCustomerPhone() + "',"
                    + "'" + NwMembership.getPremCus().getCustomerType() + "')");

            stmt.execute("INSERT INTO premium VALUES ("
                    + "'" + NwMembership.getPremCus().getPremiumID() + "', "
                    + "'" + NwMembership.getPremCus().getCustomerID() + "',"
                    + "'" + NwMembership.getPremCus().getPremiumCardNumber() + "', "
                    + "'" + NwMembership.getPremCus().getPremiumRegDate() + "', "
                    + "'" + NwMembership.getPremCus().getPremiumExpireDate() + "')");

            stmt.execute("INSERT INTO applymembership VALUES ("
                    + "'" + NwMembership.getNewApply().getApplyMembershipID() + "', "
                    + "'" + NwMembership.getPremCus().getPremiumID() + "',"
                    + "'" + NwMembership.getNewApply().getType() + "', "
                    + "'" + NwMembership.getNewApply().getDate() + "', "
                    + "'" + NwMembership.getNewApply().getCost() + "')");

            stmt.execute("INSERT INTO receipt VALUES ("
                    + "'" + NwMembership.getNewReceipt().getReceiptNo() + "', "
                    + "'" + NwMembership.getNewReceipt().getReceiptDate() + "', "
                    + "'" + NwMembership.getNewReceipt().getReceiptType() + "', "
                    + "'" + NwMembership.getNewReceipt().getReceiptTotalCost() + "', "
                    + "'" + NwMembership.getNewApply().getApplyMembershipID() + "', "
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

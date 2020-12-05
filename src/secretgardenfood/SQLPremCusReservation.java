package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQL Premium Customer Reservation
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLPremCusReservation {

    public static void toSQL() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            stmt = connection.createStatement();

            stmt.execute("INSERT INTO reservation VALUES ("
                    + "'" + PremCustomer.getPremReservation().getReservationID() + "', "
                    + "'" + PremCustomer.getPremReservation().getDate() + "', "
                    + "'" + PremCustomer.getPremReservation().getTime() + "', "
                    + "'" + PremCustomer.getPremReservation().getInfo() + "', "
                    + "'" + PremCustomer.getPremReservation().getNoPersons() + "', "
                    + "'" + PremCusReservation.getNewRoomTable().getRoomTableID() + "', "
                    + "'" + PremCustomer.getNewCustomer().getCustomerID() + "', "
                    + "'" + PremCustomer.getPremReservation().getCost() + "', "
                    + "NULL)");

            stmt.execute("INSERT INTO receipt VALUES ("
                    + "'" + PremCusReservation.getNewReceipt().getReceiptNo() + "', "
                    + "'" + PremCusReservation.getNewReceipt().getReceiptDate() + "',"
                    + "'" + PremCusReservation.getNewReceipt().getReceiptType() + "', "
                    + "'" + PremCusReservation.getNewReceipt().getReceiptTotalCost() + "', "
                    + "NULL, "
                    + "'" + PremCustomer.getPremReservation().getReservationID() + "')");

            stmt.execute("UPDATE roomtable SET Status = 'Not Available' WHERE RoomTableID = "
                    + "'" + PremCusReservation.getNewRoomTable().getRoomTableID() + "'");

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

package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * SQL Regular Customer Reservation
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLRegCusReservation {

    public static void toSQL() {
        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            stmt = connection.createStatement();

            stmt.execute("INSERT INTO customer VALUES ("
                    + "'" + RegCustomer.getNewCustomer().getCustomerID() + "', "
                    + "'" + RegCustomer.getNewCustomer().getCustomerName() + "', "
                    + "'" + RegCustomer.getNewCustomer().getCustomerPhone() + "', "
                    + "'" + RegCustomer.getNewCustomer().getCustomerType() + "')");

            stmt.execute("INSERT INTO reservation VALUES ("
                    + "'" + RegCustomer.getNewReservation().getReservationID() + "', "
                    + "'" + RegCustomer.getNewReservation().getDate() + "', "
                    + "'" + RegCustomer.getNewReservation().getTime() + "', "
                    + "'" + RegCustomer.getNewReservation().getInfo() + "', "
                    + "'" + RegCustomer.getNewReservation().getNoPersons() + "', "
                    + "'" + RegCusReservation.getNewRoomTable().getRoomTableID() + "', "
                    + "'" + RegCustomer.getNewCustomer().getCustomerID() + "', "
                    + "'" + RegCustomer.getNewReservation().getCost() + "', "
                    + "NULL)");

            stmt.execute("INSERT INTO receipt VALUES ("
                    + "'" + RegCusReservation.getNewReceipt().getReceiptNo() + "', "
                    + "'" + RegCusReservation.getNewReceipt().getReceiptDate() + "',"
                    + "'" + RegCusReservation.getNewReceipt().getReceiptType() + "', "
                    + "'" + RegCusReservation.getNewReceipt().getReceiptTotalCost() + "', "
                    + "NULL, "
                    + "'" + RegCustomer.getNewReservation().getReservationID() + "')");

            stmt.execute("UPDATE roomtable SET Status = 'Not Available' WHERE RoomTableID = "
                    + "'" + RegCusReservation.getNewRoomTable().getRoomTableID() + "'");

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

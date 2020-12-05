package secretgardenfood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * SQL Order Food Menu
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class SQLOrdFoodMenu {

    public static void toSQL() {
        List<String> foodOrderingFoodID = new LinkedList<>();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();

            for (int x = 0; x < OrdFoodMenu.getFoodOrderingFoodName().size(); x++) {
                ResultSet rs = selectStmt.executeQuery(
                        "SELECT FoodID "
                        + "FROM food "
                        + "WHERE FoodName = '" + OrdFoodMenu.getFoodOrderingFoodName().get(x) + "' ");

                while (rs.next()) {
                    foodOrderingFoodID.add(rs.getString(1));
                }
            }

            selectStmt.execute("INSERT INTO orderfood VALUES ("
                    + "'" + OrdFoodMenu.getNewFoodOrder().getFoodOrderingID() + "',"
                    + "'" + OrdFoodMenu.getNewFoodOrder().getDate() + "',"
                    + "'" + OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal() + "')");

            for (int x = 0; x < OrdFoodMenu.getFoodOrderingFoodName().size(); x++) {
                selectStmt.execute("INSERT INTO orderdetails VALUES ("
                        + "'" + OrdFoodMenu.getNewFoodOrder().getFoodOrderingID() + "',"
                        + "'" + foodOrderingFoodID.get(x) + "',"
                        + "'" + OrdFoodMenu.getFoodOrderingFoodQTY().get(x) + "',"
                        + "'" + OrdFoodMenu.getFoodOrderingFoodSubTotal().get(x) + "')");
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

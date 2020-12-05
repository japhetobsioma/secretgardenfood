package secretgardenfood;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Secret Garden Food Reservation System Main Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class Main extends Application {

    private static Stage stgMain;
    private static Scene scnMain;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Checking database driver...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found\n");
            
            System.out.println("Closing the program");
            System.exit(0);
            return;
        }
        System.out.println("Database driver registered\n");
        System.out.println("Checking database connection...");
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");
            System.out.println("Connection to database established\n");
            System.out.println("Launching Secret Garden Food Reservation System...");
            System.out.println("Version: 3.1, 10/30/2020\n");
            System.out.println("Author: Japhet Mert Catilo Obsioma (19043876)");
            System.out.println("Team Members: Shamsul Bin Majid (18063305)");
            System.out.println("Chong Wei Shen (19044429),");
            System.out.println("Jagaanathan A/L Kathiravelu (19069905)\n");
            
            stgMain = primaryStage;

            // Reservation Heading
            Text txtHeading = new Text("Reservation System");
            txtHeading.setFont(new Font(20));

            VBox paneTop = new VBox(20, txtHeading);
            paneTop.setPadding(new Insets(80, 20, 20, 20));
            paneTop.setAlignment(Pos.CENTER);

            // Buttons: Reservation, Premium Membership
            Button btnReservation = new Button("Reservation");
            btnReservation.setMinWidth(200);
            btnReservation.setMinHeight(40);
            btnReservation.setDefaultButton(true);
            btnReservation.setOnAction(e -> btnReservation_Click());

            Button btnPremium = new Button("Premium Membership");
            btnPremium.setMinWidth(200);
            btnPremium.setMinHeight(40);
            btnPremium.setOnAction(e -> btnPremium_Click());

            VBox paneCenter = new VBox(15, btnReservation, btnPremium);
            paneCenter.setPadding(new Insets(10, 10, 10, 10));
            paneCenter.setAlignment(Pos.CENTER);

            // Setup BorderPane
            BorderPane paneMain = new BorderPane();
            paneMain.setTop(paneTop);
            paneMain.setCenter(paneCenter);

            // Setup: Scenes, Stage
            Scene primaryScene = new Scene(paneMain, 580, 400);
            scnMain = primaryScene;
            primaryStage.setScene(primaryScene);
            primaryStage.setTitle("Secret Garden Food");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (SQLException e) {
            System.out.println("Connection failed\n");
            System.out.println("Closing the program...");
            System.exit(0);
            return;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static Stage getStgMain() {
        return stgMain;
    }

    public static Scene getScnMain() {
        return scnMain;
    }

    public static void btnPremium_Click() {
        List<String> choices = new ArrayList<>();
        choices.add("New Membership");
        choices.add("Renew Membership");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("New Membership",
                choices);
        dialog.setTitle("Secret Garden Food");
        dialog.setHeaderText("Premium Membership");
        dialog.setContentText("Choose the type of membership: ");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (result.get().equals("New Membership")) {
                NwMembership.chgScene();
            } else if (result.get().equals("Renew Membership")) {
                RnwMembership.chgScene();

            }
        }
    }

    public static void btnReservation_Click() {
        List<String> choices = new ArrayList<>();
        choices.add("Regular Customer");
        choices.add("Premium Membership");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Regular Customer",
                choices);
        dialog.setTitle("Secret Garden Food");
        dialog.setHeaderText("Reservation");
        dialog.setContentText("Choose the type of customer: ");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (result.get().equals("Regular Customer")) {
                RegCustomer.chgScene();
            } else if (result.get().equals("Premium Membership")) {
                PremCustomer.chgScene();
            }
        }
    }
}

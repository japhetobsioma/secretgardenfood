package secretgardenfood;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Regular Customer Details Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class RegCustomer {

    private static Scene scnRegCustomer;

    private static TextField txtName;
    private static TextField txtPhone;
    private static TextField txtResInfo;
    private static TextField txtNumPer;

    private static Customer newCustomer = new Customer();
    private static Reservation newReservation = new Reservation();

    public static void chgScene() {
        // init
        newCustomer = new Customer();
        newReservation = new Reservation();

        // Heading
        Text txtHeading = new Text("Enter customer details");
        txtHeading.setFont(new Font(20));

        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        // Name Field
        Label lblName = new Label("Name:");
        lblName.setMinWidth(50);

        txtName = new TextField();
        txtName.setPrefColumnCount(70);
        txtName.setMaxWidth(Double.MAX_VALUE);

        HBox paneName = new HBox(lblName, txtName);

        // Phone Field
        Label lblPhone = new Label("Phone:");
        lblPhone.setMinWidth(50);

        txtPhone = new TextField();
        txtPhone.setPrefColumnCount(70);
        txtPhone.setMaxWidth(Double.MAX_VALUE);

        HBox panePhone = new HBox(lblPhone, txtPhone);

        // Add Separator
        Separator sepNew = new Separator();
        sepNew.setMaxWidth(600);
        sepNew.setHalignment(HPos.CENTER);

        // Reservation Info Field
        Label lblResInfo = new Label("Reservation Info:");
        lblResInfo.setMinWidth(120);

        txtResInfo = new TextField();
        txtResInfo.setPrefColumnCount(70);
        txtResInfo.setMaxWidth(Double.MAX_VALUE);

        HBox paneResInfo = new HBox(lblResInfo, txtResInfo);

        // Number of Persons Field
        Label lblNumPer = new Label("Number of Persons:");
        lblNumPer.setMinWidth(120);

        txtNumPer = new TextField();
        txtNumPer.setPrefColumnCount(70);
        txtNumPer.setMaxWidth(Double.MAX_VALUE);

        HBox paneNumPer = new HBox(lblNumPer, txtNumPer);

        // Setup Panes: Name, Field, Separator, etc.
        VBox paneCenter = new VBox(20, paneName, panePhone, sepNew,
                paneResInfo, paneNumPer);
        paneCenter.setPadding(new Insets(0, 25, 0, 25));
        paneCenter.setAlignment(Pos.CENTER);

        // Buttons: Create, Back
        Button btnNext = new Button("Next");
        btnNext.setPrefWidth(80);
        btnNext.setDefaultButton(true);
        btnNext.setOnAction(e -> {
            if (txtName.getText().length() == 0 || txtPhone.getText().length() == 0
                    || txtResInfo.getText().length() == 0
                    || txtNumPer.getText().length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Reservation - Regular Customer");
                alert.setHeaderText("Error: Incomplete field");
                alert.setContentText("Please fill up the following");

                alert.showAndWait();
            } else {
                setData();
                goNext();
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(80);
        btnBack.setCancelButton(true);
        btnBack.setOnAction(e -> {
            Main.getStgMain().setScene(Main.getScnMain());
        });

        // Add Spacer
        Region spacer = new Region();

        // Setup Panes: Create, Back, Spacer
        HBox paneBottom = new HBox(10, spacer, btnNext, btnBack);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Setup BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        paneMain.setTop(paneTop);

        // Setup: Scene, Stage
        Scene primaryScene = new Scene(paneMain, 580, 450);
        scnRegCustomer = primaryScene;
        Main.getStgMain().setScene(scnRegCustomer);
        Main.getStgMain().setTitle("Reservation - Regular Customer");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static Customer getNewCustomer() {
        return newCustomer;
    }

    public static void setNewCustomer(Customer newCustomer) {
        RegCustomer.newCustomer = newCustomer;
    }

    public static Reservation getNewReservation() {
        return newReservation;
    }

    public static void setNewReservation(Reservation newReservation) {
        RegCustomer.newReservation = newReservation;
    }

    public static void setData() {
        newCustomer.setCustomerName(txtName.getText());
        newCustomer.setCustomerPhone(txtPhone.getText());
        newCustomer.setCustomerType("Regular");

        newReservation.setInfo(txtResInfo.getText());
        newReservation.setNoPersons(Integer.parseInt(txtNumPer.getText()));
        newReservation.setCost(10);
    }

    public static void goNext() {
        RegCusReservation.chgScene();
    }

    public static Scene getScnRegCustomer() {
        return scnRegCustomer;
    }

}

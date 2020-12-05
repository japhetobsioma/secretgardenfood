package secretgardenfood;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;

/**
 * Premium Customer Details Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class PremCustomer {

    private static Scene scnPremCustomer;

    private static TextField txtSearch;
    private static TextField txtName;
    private static TextField txtPhone;
    private static TextField txtCard;
    private static TextField txtReg;
    private static TextField txtExp;
    private static TextField txtResInfo;
    private static TextField txtNumPer;
    private static Button btnNext;

    private static PremiumMember PremiumCus = new PremiumMember();
    private static Reservation PremReservation = new Reservation();
    private static Customer newCustomer = new Customer();

    public static void chgScene() {
        // Init
        PremiumCus = new PremiumMember();
        PremReservation = new Reservation();
        newCustomer = new Customer();

        // Heading
        Text txtHeading = new Text("Enter customer details");
        txtHeading.setFont(new Font(20));

        // Search Field
        txtSearch = new TextField();
        txtSearch.setPromptText("Enter Card Number here");
        txtSearch.setPrefColumnCount(38);
        txtSearch.setMinWidth(38);

        Button btnSearch = new Button("Search");
        // btnSearch.setPrefWidth(80);
btnSearch.setMinWidth(80);
btnSearch.setMaxWidth(80);
        btnSearch.setOnAction(e -> {
            if (txtSearch.getText().length() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Reservation - Premium Customer");
                alert.setHeaderText("Error: Incomplete field");
                alert.setContentText("Please fill up the following");

                alert.showAndWait();
            } else {
                SQLSearchPremCustomer.toSQLSearch();
            }
        });

        HBox paneSearch = new HBox(10, txtSearch, btnSearch);
        paneSearch.setPadding(new Insets(20, 0, 20, 0));

        // Name Field
        Label lblName = new Label("Name:");
        lblName.setMinWidth(50);

        txtName = new TextField();
        txtName.setPrefColumnCount(70);
        txtName.setMaxWidth(Double.MAX_VALUE);
        txtName.setDisable(true);

        HBox paneName = new HBox(lblName, txtName);

        // Phone Field
        Label lblPhone = new Label("Phone:");
        lblPhone.setMinWidth(50);

        txtPhone = new TextField();
        txtPhone.setPrefColumnCount(70);
        txtPhone.setMaxWidth(Double.MAX_VALUE);
        txtPhone.setDisable(true);

        HBox panePhone = new HBox(lblPhone, txtPhone);

        // Add Separator
        Separator sepFirst = new Separator();
        sepFirst.setMaxWidth(600);
        sepFirst.setHalignment(HPos.CENTER);

        // Card Number Field
        Label lblCard = new Label("Card Number:");
        lblCard.setMinWidth(110);

        txtCard = new TextField();
        txtCard.setPrefColumnCount(70);
        txtCard.setMaxWidth(Double.MAX_VALUE);
        txtCard.setDisable(true);

        HBox paneCard = new HBox(lblCard, txtCard);

        // Registration Date Field
        Label lblReg = new Label("Registration Date:");
        lblReg.setMinWidth(110);

        txtReg = new TextField();
        txtReg.setPrefColumnCount(70);
        txtReg.setMaxWidth(Double.MAX_VALUE);
        txtReg.setDisable(true);

        HBox paneReg = new HBox(lblReg, txtReg);

        // Expiration Date
        Label lblExp = new Label("Expiration Date:");
        lblExp.setMinWidth(110);

        txtExp = new TextField();
        txtExp.setPrefColumnCount(70);
        txtExp.setMaxWidth(Double.MAX_VALUE);
        txtExp.setDisable(true);

        HBox paneExp = new HBox(lblExp, txtExp);

        // Add Separator
        Separator sepSecond = new Separator();
        sepSecond.setMaxWidth(600);
        sepSecond.setHalignment(HPos.CENTER);

        // Reservation Info Field
        Label lblResInfo = new Label("Reservation Info:");
        lblResInfo.setMinWidth(120);

        txtResInfo = new TextField();
        txtResInfo.setPrefColumnCount(70);
        txtResInfo.setMaxWidth(Double.MAX_VALUE);
        txtResInfo.setDisable(true);

        HBox paneResInfo = new HBox(lblResInfo, txtResInfo);

        // Number of Persons Field
        Label lblNumPer = new Label("Number of Persons:");
        lblNumPer.setMinWidth(120);

        txtNumPer = new TextField();
        txtNumPer.setPrefColumnCount(70);
        txtNumPer.setMaxWidth(Double.MAX_VALUE);
        txtNumPer.setDisable(true);

        HBox paneNumPer = new HBox(lblNumPer, txtNumPer);

        // Add Spacer
        Region spcrBot = new Region();

        // Buttons: Next, Back
        btnNext = new Button("Next");
        btnNext.setPrefWidth(80);
        btnNext.setDefaultButton(true);
        btnNext.setDisable(true);
        btnNext.setOnAction(e -> {
            if (txtResInfo.getText().length() == 0 || txtNumPer.getText().length() == 0) {
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
        btnBack.setOnAction(e -> btnBack_Click());

        // Setup Panes
        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        VBox paneCenter = new VBox(20, paneSearch, paneName, panePhone, sepFirst,
                paneCard, paneReg, paneExp, sepSecond, paneResInfo, paneNumPer);
        paneCenter.setPadding(new Insets(0, 25, 20, 25));
        paneCenter.setAlignment(Pos.CENTER);

        HBox paneBottom = new HBox(10, spcrBot, btnNext, btnBack);
        paneBottom.setHgrow(spcrBot, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Panes to BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // Setup: Scene, Stage
        Scene primaryScene = new Scene(paneMain, 580, 600);
        scnPremCustomer = primaryScene;
        Main.getStgMain().setScene(primaryScene);
        Main.getStgMain().setTitle("Reservation - Premium Customer");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static PremiumMember getPremiumCus() {
        return PremiumCus;
    }

    public static void setPremiumCus(PremiumMember PremiumCus) {
        PremCustomer.PremiumCus = PremiumCus;
    }

    public static Reservation getPremReservation() {
        return PremReservation;
    }

    public static void setPremReservation(Reservation PremReservation) {
        PremCustomer.PremReservation = PremReservation;
    }

    public static TextField getTxtSearch() {
        return txtSearch;
    }

    public static void setTxtSearch(TextField txtSearch) {
        PremCustomer.txtSearch = txtSearch;
    }

    public static TextField getTxtName() {
        return txtName;
    }

    public static void setTxtName(TextField txtName) {
        PremCustomer.txtName = txtName;
    }

    public static TextField getTxtPhone() {
        return txtPhone;
    }

    public static void setTxtPhone(TextField txtPhone) {
        PremCustomer.txtPhone = txtPhone;
    }

    public static TextField getTxtCard() {
        return txtCard;
    }

    public static void setTxtCard(TextField txtCard) {
        PremCustomer.txtCard = txtCard;
    }

    public static TextField getTxtReg() {
        return txtReg;
    }

    public static void setTxtReg(TextField txtReg) {
        PremCustomer.txtReg = txtReg;
    }

    public static TextField getTxtExp() {
        return txtExp;
    }

    public static void setTxtExp(TextField txtExp) {
        PremCustomer.txtExp = txtExp;
    }

    public static Customer getNewCustomer() {
        return newCustomer;
    }

    public static void setNewCustomer(Customer newCustomer) {
        PremCustomer.newCustomer = newCustomer;
    }

    public static Button getBtnNext() {
        return btnNext;
    }

    public static void setBtnNext(Button btnNext) {
        PremCustomer.btnNext = btnNext;
    }

    public static TextField getTxtResInfo() {
        return txtResInfo;
    }

    public static void setTxtResInfo(TextField txtResInfo) {
        PremCustomer.txtResInfo = txtResInfo;
    }

    public static TextField getTxtNumPer() {
        return txtNumPer;
    }

    public static void setTxtNumPer(TextField txtNumPer) {
        PremCustomer.txtNumPer = txtNumPer;
    }

    public static void setData() {
        PremReservation.setInfo(txtResInfo.getText());
        PremReservation.setNoPersons(Integer.parseInt(txtNumPer.getText()));
        
    }

    public static void goNext() {
        PremCusReservation.chgScene();
    }

    public static void btnBack_Click() {
        Main.getStgMain().setScene(Main.getScnMain());
    }

    public static Scene getScnPremCustomer() {
        return scnPremCustomer;
    }
}

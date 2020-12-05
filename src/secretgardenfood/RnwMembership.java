package secretgardenfood;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Renew Membership Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class RnwMembership {

    private static TextField txtName;
    private static TextField txtPhone;
    private static TextField txtCard;
    private static TextField txtReg;
    private static TextField txtExp;
    private static TextField txtSearch;
    private static Button btnRenew;

    private static PremiumMember PremCusRenew = new PremiumMember();
    private static ApplyMembership newApply = new ApplyMembership();
    private static Receipt newReceipt = new Receipt();

    public static void chgScene() {
        // init
        PremCusRenew = new PremiumMember();
        newApply = new ApplyMembership();
        newReceipt = new Receipt();

        // Heading
        Text txtHeading = new Text("Search for membership card");
        txtHeading.setFont(new Font(20));

        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

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
                alert.setTitle("Renew Membership");
                alert.setHeaderText("Error: Incomplete field");
                alert.setContentText("Please fill up the following");

                alert.showAndWait();
            } else {
                SQLSearchRenewMembership.toSQLSearch();

            }
        });

        HBox paneSearch = new HBox(10, txtSearch, btnSearch);

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
        Separator sepNew = new Separator();
        sepNew.setMaxWidth(600);
        sepNew.setHalignment(HPos.CENTER);

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

        // Setup Panes: Name, Field, Separator, etc.
        VBox paneCenter = new VBox(20, paneSearch, paneName, panePhone, sepNew,
                paneCard, paneReg, paneExp);
        paneCenter.setPadding(new Insets(0, 25, 0, 25));
        paneCenter.setAlignment(Pos.CENTER);

        // Buttons: Create, Back
        btnRenew = new Button("Renew");
        btnRenew.setPrefWidth(80);
        btnRenew.setDisable(true);
        btnRenew.setDefaultButton(true);
        btnRenew.setOnAction(e -> btnRenew_Click());

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(80);
        btnBack.setCancelButton(true);
        btnBack.setOnAction(e -> btnBack_Click());

        // Add Spacer
        Region spacer = new Region();

        // Setup Panes: Create, Back, Spacer
        HBox paneBottom = new HBox(10, spacer, btnRenew, btnBack);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Setup BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // Setup: Scene, Stage
        Scene scnNew = new Scene(paneMain, 580, 450);
        Main.getStgMain().setScene(scnNew);
        Main.getStgMain().setTitle("Renew Membership");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static PremiumMember getPremCusRenew() {
        return PremCusRenew;
    }

    public static void setPremCusRenew(PremiumMember PremCusRenew) {
        RnwMembership.PremCusRenew = PremCusRenew;
    }

    public static void btnRenew_Click() {
        ButtonType btnPrint = new ButtonType("Print Details",
                ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(AlertType.INFORMATION,
                "Press the button to print the receipt", btnPrint);
        alert.setTitle("Renew Membership");
        alert.setHeaderText("Membership Renewed Successfully");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnPrint) {
            printPDF();
            Main.getStgMain().setScene(Main.getScnMain());
        }
    }

    public static void btnBack_Click() {
        Main.getStgMain().setScene(Main.getScnMain());
    }

    public static Receipt getNewReceipt() {
        return newReceipt;
    }

    public static void setNewReceipt(Receipt newReceipt) {
        RnwMembership.newReceipt = newReceipt;
    }

    public static TextField getTxtSearch() {
        return txtSearch;
    }

    public static void setTxtSearch(TextField txtSearch) {
        RnwMembership.txtSearch = txtSearch;
    }

    public static TextField getTxtName() {
        return txtName;
    }

    public static void setTxtName(TextField txtName) {
        RnwMembership.txtName = txtName;
    }

    public static TextField getTxtPhone() {
        return txtPhone;
    }

    public static void setTxtPhone(TextField txtPhone) {
        RnwMembership.txtPhone = txtPhone;
    }

    public static TextField getTxtCard() {
        return txtCard;
    }

    public static void setTxtCard(TextField txtCard) {
        RnwMembership.txtCard = txtCard;
    }

    public static TextField getTxtReg() {
        return txtReg;
    }

    public static void setTxtReg(TextField txtReg) {
        RnwMembership.txtReg = txtReg;
    }

    public static TextField getTxtExp() {
        return txtExp;
    }

    public static void setTxtExp(TextField txtExp) {
        RnwMembership.txtExp = txtExp;
    }

    public static Button getBtnRenew() {
        return btnRenew;
    }

    public static void setBtnRenew(Button btnRenew) {
        RnwMembership.btnRenew = btnRenew;
    }

    public static ApplyMembership getNewApply() {
        return newApply;
    }

    public static void setNewApply(ApplyMembership newApply) {
        RnwMembership.newApply = newApply;
    }

    public static void printPDF() {
        try {
            newApply.setApplyMembershipID();
            newApply.setType("Renew Membership");
            newApply.setDate(java.time.LocalDate.now().toString());
            newApply.setCost(20);

            newReceipt.setReceiptDate(newApply.getDate());
            newReceipt.setReceiptType(newApply.getType());
            newReceipt.setReceiptTotalCost(newApply.getCost());

            String dateOld = PremCusRenew.getPremiumExpireDate();
            int intDateOld = Integer.parseInt(dateOld.substring(6)) + 1;
            String newDateOld = dateOld.substring(0, 6) + String.valueOf(intDateOld);

            PremCusRenew.setPremiumExpireDate(newDateOld);

            SQLRenewMembership.toSQL();
            PrintRenewMembership.print();
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

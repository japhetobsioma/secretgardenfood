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
 * New Membership Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class NwMembership {

    private static TextField txtName;
    private static TextField txtPhone;
    private static TextField txtCard;
    private static TextField txtReg;
    private static TextField txtExp;

    private static PremiumMember PremCus = new PremiumMember();
    private static Receipt newReceipt = new Receipt();
    private static ApplyMembership newApply = new ApplyMembership();

    public static void chgScene() {
        // init
        PremCus = new PremiumMember();
        newReceipt = new Receipt();
        newApply = new ApplyMembership();

        // Heading
        Text txtHeading = new Text("Enter membership details");
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

        // Card Number Field
        Label lblCard = new Label("Card Number:");
        lblCard.setMinWidth(110);

        Tooltip ttipCard = new Tooltip("Format: XXX XXX XXX");
        txtCard = new TextField();
        txtCard.setPrefColumnCount(70);
        txtCard.setMaxWidth(Double.MAX_VALUE);
        txtCard.setTooltip(ttipCard);

        HBox paneCard = new HBox(lblCard, txtCard);

        // Registration Date Field
        Label lblReg = new Label("Registration Date:");
        lblReg.setMinWidth(110);

        Tooltip ttipReg = new Tooltip("Format: MM/DD/YYYY");
        txtReg = new TextField();
        txtReg.setPrefColumnCount(70);
        txtReg.setMaxWidth(Double.MAX_VALUE);
        txtReg.setTooltip(ttipReg);

        HBox paneReg = new HBox(lblReg, txtReg);

        // Expiration Date
        Label lblExp = new Label("Expiration Date:");
        lblExp.setMinWidth(110);

        Tooltip ttipExp = new Tooltip("Format: MM/DD/YYYY");
        txtExp = new TextField();
        txtExp.setPrefColumnCount(70);
        txtExp.setMaxWidth(Double.MAX_VALUE);
        txtExp.setTooltip(ttipExp);

        HBox paneExp = new HBox(lblExp, txtExp);

        // Setup Panes: Name, Field, Separator, etc.
        VBox paneCenter = new VBox(20, paneName, panePhone, sepNew,
                paneCard, paneReg, paneExp);
        paneCenter.setPadding(new Insets(0, 25, 0, 25));
        paneCenter.setAlignment(Pos.CENTER);

        // Buttons: Create, Back
        Button btnCreate = new Button("Create");
        btnCreate.setPrefWidth(80);
        btnCreate.setDefaultButton(true);
        btnCreate.setOnAction(e -> btnCreate_Click());

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(80);
        btnBack.setCancelButton(true);
        btnBack.setOnAction(e -> btnBack_Click());

        // Add Spacer
        Region spacer = new Region();

        // Setup Panes: Create, Back, Spacer
        HBox paneBottom = new HBox(10, spacer, btnCreate, btnBack);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Setup BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        paneMain.setTop(paneTop);

        // Setup: Scene, Stage
        Scene scnNew = new Scene(paneMain, 580, 450);
        Main.getStgMain().setScene(scnNew);
        Main.getStgMain().setTitle("New Membership");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static PremiumMember getPremCus() {
        return PremCus;
    }

    public static void setPremCus(PremiumMember PremCus) {
        NwMembership.PremCus = PremCus;
    }

    public static ApplyMembership getNewApply() {
        return newApply;
    }

    public static void setNewApply(ApplyMembership newApply) {
        NwMembership.newApply = newApply;
    }

    public static void btnCreate_Click() {
        boolean error = true;

        if (checkData(error) == false) {
            setData();
            SQLSearchNewMembership.toSQLSearch();

        }
    }

    public static boolean checkData(boolean gotError) {
        if (txtName.getText().length() == 0 || txtPhone.getText().length() == 0
                || txtCard.getText().length() == 0
                || txtReg.getText().length() == 0
                || txtExp.getText().length() == 0) {
            gotError = true;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("New Membership");
            alert.setHeaderText("Error: Complete all the field");
            alert.setContentText("Please fill up the following");

            alert.showAndWait();
        } else {
            gotError = false;
        }

        return gotError;
    }

    public static void toPrint() {
        ButtonType btnPrint = new ButtonType("Print Details",
                ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(AlertType.INFORMATION,
                "Press the button to print the receipt", btnPrint);
        alert.setTitle("New Membership");
        alert.setHeaderText("Membership Created Successfully");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnPrint) {

            printPDF();
            Main.getStgMain().setScene(Main.getScnMain());
        }
    }

    public static void setData() {
        PremCus.setCustomerName(txtName.getText());
        PremCus.setCustomerPhone(txtPhone.getText());
        PremCus.setCustomerType("Premium");

        PremCus.setPremiumCardNumber(txtCard.getText());
        PremCus.setPremiumRegDate(txtReg.getText());
        PremCus.setPremiumExpireDate(txtExp.getText());

        newApply.setApplyMembershipID();
        newApply.setType("New Membership");
        newApply.setDate(java.time.LocalDate.now().toString());
        newApply.setCost(20);
    }

    public static void btnBack_Click() {
        Main.getStgMain().setScene(Main.getScnMain());
    }

    public static Receipt getNewReceipt() {
        return newReceipt;
    }

    public static void setNewReceipt(Receipt newReceipt) {
        NwMembership.newReceipt = newReceipt;
    }

    public static void printPDF() {
        try {
            newReceipt.setReceiptDate(java.time.LocalDate.now().toString());
            newReceipt.setReceiptType(newApply.getType());
            newReceipt.setReceiptTotalCost(newApply.getCost());
            SQLNewMembership.toSQL();
            PrintNewMembership.print();
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

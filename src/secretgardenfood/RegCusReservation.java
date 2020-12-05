package secretgardenfood;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.cell.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Regular Customer Reservation Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class RegCusReservation {

    private static TableView<ResTime> table;
    private static ObservableList<ResTime> data;

    private static LocalDate currentDate;
    private static ChoiceBox chcTable;
    private static String selectedDate;
    private static String selectedTable;

    private static RoomTable newRoomTable = new RoomTable();
    private static Receipt newReceipt = new Receipt();

    private static Button btnFinish;

    public static void chgScene() {
        // init
        newRoomTable = new RoomTable();
        newReceipt = new Receipt();

        // initialize date
        currentDate = LocalDate.now();
        selectedDate = currentDate.getMonth().toString()
                + " " + currentDate.getDayOfMonth()
                + ", " + currentDate.getYear();

        // Heading Field
        Text txtHeading = new Text("Enter reservation details");
        txtHeading.setFont(new Font(20));

        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        // Date Picker Field
        Text txtDate = new Text();
        txtDate.setFont(new Font(15));

        currentDate = LocalDate.now();
        txtDate.setText(currentDate.getMonth().toString()
                + " " + currentDate.getDayOfMonth()
                + ", " + currentDate.getYear());

        // Add Spacer
        Region spr = new Region();

        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        dpDate.setOnAction(e -> {
            LocalDate date = dpDate.getValue();
            selectedDate = date.getMonth().toString()
                    + " " + date.getDayOfMonth()
                    + ", " + date.getYear();
            txtDate.setText(selectedDate);

            table.setItems(loadData());
        });

        // Setup Panes: lblDatePick, Region, Date
        HBox paneDate = new HBox(10, txtDate, spr, dpDate);
        paneDate.setHgrow(spr, Priority.ALWAYS);

        // Table
        table = new TableView<ResTime>();
        table.setEditable(true);
        table.setItems(loadData());

        TableColumn<ResTime, String> colTime = new TableColumn("Time");
        colTime.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("Time"));
        colTime.setStyle("-fx-alignment: CENTER;");
        colTime.setResizable(true);
        colTime.setMinWidth(264);
        colTime.setMaxWidth(264);

        TableColumn<ResTime, String> colTblLeft = new TableColumn("Tables Left");
        colTblLeft.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("tblLeft"));
        colTblLeft.setStyle("-fx-alignment: CENTER;");
        colTblLeft.setResizable(true);
        colTblLeft.setMinWidth(264);
        colTblLeft.setMaxWidth(264);

        table.getColumns().addAll(colTime, colTblLeft);
        table.setPlaceholder(new Label("No rows to display"));
        table.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<ResTime> sel;
            sel = table.getSelectionModel().getSelectedItems();
            sel.forEach((m) -> {
                chcTable.getItems().clear();

                RegCustomer.getNewReservation().setDate(selectedDate);
                RegCustomer.getNewReservation().setTime(m.getTime());

                // chcTable.getItems().add("A1");
                Connection connection = null;
                Statement selectStmt = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

                    selectStmt = connection.createStatement();
                    ResultSet rs = selectStmt.executeQuery(
                            "SELECT TableName "
                            + "FROM roomtable "
                            + "WHERE Date = '" + selectedDate + "' "
                            + "AND Time = '" + m.getTime() + "' "
                            + "AND Status = 'Available' "
                            + "AND RoomName = 'Main Dining Hall' ");

                    while (rs.next()) {

                        chcTable.getItems().add(rs.getString(1));
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
            });
            btnFinish.setDisable(true);
            chcTable.setDisable(false);
        });

        // TabPane
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Main Dining Hall", table);

        tabPane.getTabs().add(tab1);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getStyleClass().add("floating");
        tabPane.setTabMinWidth(100);
        tabPane.setFocusTraversable(false);

        // Select Table
        Text txtTable = new Text("Select Table: ");
        txtTable.setFont(new Font(15));

        chcTable = new ChoiceBox();
        chcTable.setDisable(true);
        chcTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            btnFinish.setDisable(false);
        });

        HBox paneTable = new HBox(10, txtTable, chcTable);

        // Setup Panes: DatePick, TabPane, Table, etc.
        VBox paneCenter = new VBox(20, paneDate, tabPane, paneTable);
        paneCenter.setPadding(new Insets(0, 25, 0, 25));
        paneCenter.setAlignment(Pos.CENTER);

        // Buttons: Create, Back
        btnFinish = new Button("Finish");
        btnFinish.setPrefWidth(80);
        btnFinish.setDefaultButton(true);
        btnFinish.setOnAction(e -> {
            setData();
            Alert alrtOrderFood = new Alert(AlertType.CONFIRMATION);
            alrtOrderFood.setTitle("Reservation - Regular Customer");
            alrtOrderFood.setHeaderText("Order food in advance?");
            alrtOrderFood.setContentText("Press OK to continue");

            Optional<ButtonType> result = alrtOrderFood.showAndWait();
            if (result.get() == ButtonType.OK) {
                OrdFoodMenu.chgScene();
            } else {
                ButtonType btnPrint = new ButtonType("Print Details",
                        ButtonBar.ButtonData.OK_DONE);
                Alert alrtPrintDetails = new Alert(AlertType.INFORMATION,
                        "Press the button to print the receipt", btnPrint);
                alrtPrintDetails.setTitle("Reservation - Regular Customer");
                alrtPrintDetails.setHeaderText("Reservation Created Successfully");

                Optional<ButtonType> resultReg = alrtPrintDetails.showAndWait();
                if (resultReg.get() == btnPrint) {
                    printPDF();
                    Main.getStgMain().setScene(Main.getScnMain());
                }
            }
        });

        btnFinish.setDisable(true);

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(80);
        btnBack.setCancelButton(true);
        btnBack.setOnAction(e -> btnBack_Click());

        // Add Spacer
        Region spacer = new Region();

        // Setup Panes: Create, Back, Spacer
        HBox paneBottom = new HBox(10, spacer, btnFinish, btnBack);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Setup BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // Setup: Scene, Stage
        Scene scnRegCusReservation = new Scene(paneMain, 580, 500);
        Main.getStgMain().setScene(scnRegCusReservation);
        Main.getStgMain().setTitle("Reservation - Regular Customer");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();

    }

    public static RoomTable getNewRoomTable() {
        return newRoomTable;
    }

    public static void setNewRoomTable(RoomTable newRoomTable) {
        RegCusReservation.newRoomTable = newRoomTable;
    }

    public static Receipt getNewReceipt() {
        return newReceipt;
    }

    public static void setNewReceipt(Receipt newReceipt) {
        RegCusReservation.newReceipt = newReceipt;
    }

    public static void setData() {
        selectedTable = (String) chcTable.getValue();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT RoomTableID, Date, Time, RoomName, TableName "
                    + "FROM roomtable "
                    + "WHERE Date = '" + RegCustomer.getNewReservation().getDate() + "' "
                    + "AND Time = '" + RegCustomer.getNewReservation().getTime() + "' "
                    + "AND RoomName =  'Main Dining Hall' "
                    + "AND TableName = '" + selectedTable + "' ");

            while (rs.next()) {
                newRoomTable.setRoomTableID(rs.getString(1));
                newRoomTable.setDate(rs.getString(2));
                newRoomTable.setTime(rs.getString(3));
                newRoomTable.setRoomName(rs.getString(4));
                newRoomTable.setTableName(rs.getString(5));
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

        RegCustomer.getNewReservation().setDate(newRoomTable.getDate());
        RegCustomer.getNewReservation().setTime(newRoomTable.getTime());

        newReceipt.setReceiptDate(java.time.LocalDate.now().toString());
        newReceipt.setReceiptType("Main Dining Hall Reservation Fee");
        newReceipt.setReceiptTotalCost(RegCustomer.getNewReservation().getCost());
    }

    public static ObservableList<ResTime> loadData() {
        data = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '09:00 AM - 11:00 AM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'Main Dining Hall' ");

            while (rs.next()) {
                data.add(new ResTime("09:00 AM - 11:00 AM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '11:00 AM - 01:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'Main Dining Hall' ");

            while (rs.next()) {
                data.add(new ResTime("11:00 AM - 01:00 PM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '01:00 PM - 03:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'Main Dining Hall' ");

            while (rs.next()) {
                data.add(new ResTime("01:00 PM - 03:00 PM", Integer.parseInt(rs.getString(1))));
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

        return data;
    }

    public static void btnBack_Click() {
        Main.getStgMain().setScene(RegCustomer.getScnRegCustomer());
    }

    public static void printPDF() {
        try {
            SQLRegCusReservation.toSQL();
            PrintRegCusReservation.print();
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

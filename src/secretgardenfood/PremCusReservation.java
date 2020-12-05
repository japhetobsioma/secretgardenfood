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
 * Premium Customer Reservation Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class PremCusReservation {

    private static TableView<ResTime> tblMain;
    private static TableView<ResTime> tblVVIP;
    private static ObservableList<ResTime> dataMain;
    private static ObservableList<ResTime> dataVVIP;

    private static LocalDate currentDate;
    private static ChoiceBox chcTable;
    private static String selectedDate;
    private static String selectedTable;
    private static String selectedRoom;

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

        // Date Picker Field
        Text txtDate = new Text("Sunday, October 18, 2020");
        txtDate.setFont(new Font(15));

        currentDate = LocalDate.now();
        txtDate.setText(currentDate.getMonth().toString()
                + " " + currentDate.getDayOfMonth()
                + ", " + currentDate.getYear());

        Region rgnDate = new Region();

        DatePicker dpDate = new DatePicker();
        dpDate.setValue(LocalDate.now());
        dpDate.setOnAction(e -> {
            LocalDate date = dpDate.getValue();
            selectedDate = date.getMonth().toString()
                    + " " + date.getDayOfMonth()
                    + ", " + date.getYear();
            txtDate.setText(selectedDate);

            tblMain.setItems(loadDataMain());
            tblVVIP.setItems(loadDataVVIP());
        });

        // Table Field - Main Dining Hall
        tblMain = new TableView<ResTime>();
        tblMain.setEditable(true);
        tblMain.setItems(loadDataMain());

        TableColumn<ResTime, String> colTimeMain = new TableColumn("Time");
        colTimeMain.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("Time"));
        colTimeMain.setStyle("-fx-alignment: CENTER;");
        colTimeMain.setResizable(true);
        colTimeMain.setMinWidth(264);
        colTimeMain.setMaxWidth(264);
        colTimeMain.setSortable(false);

        TableColumn<ResTime, String> colTblLeftMain = new TableColumn("Tables Left");
        colTblLeftMain.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("tblLeft"));
        colTblLeftMain.setStyle("-fx-alignment: CENTER;");
        colTblLeftMain.setResizable(true);
        colTblLeftMain.setMinWidth(264);
        colTblLeftMain.setMaxWidth(264);
        colTblLeftMain.setSortable(false);

        tblMain.getColumns().addAll(colTimeMain, colTblLeftMain);
        tblMain.setPlaceholder(new Label("No rows to display"));
        tblMain.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<ResTime> selMain;
            selMain = tblMain.getSelectionModel().getSelectedItems();
            selMain.forEach((n) -> {
                newReceipt.setReceiptType("Main Dining Hall Reservation Fee");
                PremCustomer.getPremReservation().setCost(10);
                chcTable.getItems().clear();
                selectedRoom = "Main Dining Hall";
                chcTable.getItems().clear();

                PremCustomer.getPremReservation().setDate(selectedDate);
                PremCustomer.getPremReservation().setTime(n.getTime());

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
                            + "AND Time = '" + n.getTime() + "' "
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

        // Table Field - VVIP Room
        tblVVIP = new TableView<ResTime>();
        tblVVIP.setEditable(true);
        tblVVIP.setItems(loadDataVVIP());

        TableColumn<ResTime, String> colTimeVVIP = new TableColumn("Time");
        colTimeVVIP.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("Time"));
        colTimeVVIP.setStyle("-fx-alignment: CENTER;");
        colTimeVVIP.setResizable(true);
        colTimeVVIP.setMinWidth(264);
        colTimeVVIP.setMaxWidth(264);
        colTimeVVIP.setSortable(false);

        TableColumn<ResTime, String> colTblLeftVVIP = new TableColumn("Tables Left");
        colTblLeftVVIP.setCellValueFactory(
                new PropertyValueFactory<ResTime, String>("tblLeft"));
        colTblLeftVVIP.setStyle("-fx-alignment: CENTER;");
        colTblLeftVVIP.setResizable(true);
        colTblLeftVVIP.setMinWidth(264);
        colTblLeftVVIP.setMaxWidth(264);
        colTblLeftVVIP.setSortable(false);

        tblVVIP.getColumns().addAll(colTimeVVIP, colTblLeftVVIP);
        tblVVIP.setPlaceholder(new Label("No rows to display"));
        tblVVIP.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<ResTime> selVVIP;
            selVVIP = tblVVIP.getSelectionModel().getSelectedItems();
            selVVIP.forEach((n) -> {
                newReceipt.setReceiptType("VVIP Room Reservation Fee");
                PremCustomer.getPremReservation().setCost(30);
                selectedRoom = "VVIP Room";
                chcTable.getItems().clear();

                PremCustomer.getPremReservation().setDate(selectedDate);
                PremCustomer.getPremReservation().setTime(n.getTime());

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
                            + "AND Time = '" + n.getTime() + "' "
                            + "AND Status = 'Available' "
                            + "AND RoomName = 'VVIP Room' ");

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

        // Table Field - TabPane
        TabPane tabPane = new TabPane();
        Tab tabMain = new Tab("Main Dining Hall", tblMain);
        Tab tabVVIP = new Tab("VVIP Room", tblVVIP);

        tabPane.getTabs().add(tabMain);
        tabPane.getTabs().add(tabVVIP);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getStyleClass().add("floating");
        tabPane.setTabMinWidth(100);
        tabPane.setFocusTraversable(false);

        // Select Table Field
        Text txtTable = new Text("Select Table: ");
        txtTable.setFont(new Font(15));

        chcTable = new ChoiceBox();
        chcTable.setDisable(true);
        chcTable.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            btnFinish.setDisable(false);
        });

        // Bottom Field
        Region rgnBot = new Region();

        btnFinish = new Button("Finish");
        btnFinish.setPrefWidth(80);
        btnFinish.setDefaultButton(true);
        btnFinish.setOnAction(e -> {
            setData();
            Alert alrtOrderFood = new Alert(AlertType.CONFIRMATION);
            alrtOrderFood.setTitle("Reservation - Premium Customer");
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
                alrtPrintDetails.setTitle("Reservation - Premium Customer");
                alrtPrintDetails.setHeaderText("Reservation Created Successfully");

                Optional<ButtonType> resultPrem = alrtPrintDetails.showAndWait();
                if (resultPrem.get() == btnPrint) {
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

        // Setup Panes
        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        HBox paneDate = new HBox(10, txtDate, rgnDate, dpDate);
        paneDate.setHgrow(rgnDate, Priority.ALWAYS);

        HBox paneTable = new HBox(10, txtTable, chcTable);

        VBox paneCenter = new VBox(20, paneDate, tabPane, paneTable);
        paneCenter.setPadding(new Insets(0, 25, 0, 25));
        paneCenter.setAlignment(Pos.CENTER);

        HBox paneBottom = new HBox(10, rgnBot, btnFinish, btnBack);
        paneBottom.setHgrow(rgnBot, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Setup BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // Setup: Scene, Stage
        Scene scnPremCusReservation = new Scene(paneMain, 580, 500);
        Main.getStgMain().setScene(scnPremCusReservation);
        Main.getStgMain().setTitle("Reservation - Premium Customer");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static RoomTable getNewRoomTable() {
        return newRoomTable;
    }

    public static void setNewRoomTable(RoomTable newRoomTable) {
        PremCusReservation.newRoomTable = newRoomTable;
    }

    public static Receipt getNewReceipt() {
        return newReceipt;
    }

    public static void setNewReceipt(Receipt newReceipt) {
        PremCusReservation.newReceipt = newReceipt;
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
                    + "WHERE Date = '" + PremCustomer.getPremReservation().getDate() + "' "
                    + "AND Time = '" + PremCustomer.getPremReservation().getTime() + "' "
                    + "AND RoomName =  '" + selectedRoom + "' "
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

        PremCustomer.getPremReservation().setDate(newRoomTable.getDate());
        PremCustomer.getPremReservation().setTime(newRoomTable.getTime());

        // Change this later
        newReceipt.setReceiptDate(java.time.LocalDate.now().toString());
        
        newReceipt.setReceiptTotalCost(PremCustomer.getPremReservation().getCost());
    }

    public static ObservableList<ResTime> loadDataMain() {
        dataMain = FXCollections.observableArrayList();

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
                dataMain.add(new ResTime("09:00 AM - 11:00 AM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '11:00 AM - 01:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'Main Dining Hall' ");

            while (rs.next()) {
                dataMain.add(new ResTime("11:00 AM - 01:00 PM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '01:00 PM - 03:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'Main Dining Hall' ");

            while (rs.next()) {
                dataMain.add(new ResTime("01:00 PM - 03:00 PM", Integer.parseInt(rs.getString(1))));
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

        return dataMain;
    }

    public static ObservableList<ResTime> loadDataVVIP() {
        dataVVIP = FXCollections.observableArrayList();
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
                    + "AND RoomName = 'VVIP Room' ");

            while (rs.next()) {
                dataVVIP.add(new ResTime("09:00 AM - 11:00 AM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '11:00 AM - 01:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'VVIP Room' ");

            while (rs.next()) {
                dataVVIP.add(new ResTime("11:00 AM - 01:00 PM", Integer.parseInt(rs.getString(1))));
            }

            rs = selectStmt.executeQuery(
                    "SELECT COUNT(TableName) "
                    + "FROM roomtable "
                    + "WHERE Date = '" + selectedDate + "' "
                    + "AND Time = '01:00 PM - 03:00 PM' "
                    + "AND Status = 'Available' "
                    + "AND RoomName = 'VVIP Room' ");

            while (rs.next()) {
                dataVVIP.add(new ResTime("01:00 PM - 03:00 PM", Integer.parseInt(rs.getString(1))));
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

        return dataVVIP;
    }

    public static void btnBack_Click() {
        Main.getStgMain().setScene(PremCustomer.getScnPremCustomer());
    }

    public static void printPDF() {
        try {
            SQLPremCusReservation.toSQL();
            PrintPremCusReservation.print();
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

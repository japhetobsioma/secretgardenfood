package secretgardenfood;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.*;

/**
 * Food Ordering Menu Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class OrdFoodMenu {

    private static TableView<FdMenu> tblSushi;
    private static ObservableList<FdMenu> dataSushi;
    private static TableView<FdMenu> tblNoodles;
    private static ObservableList<FdMenu> dataNoodles;
    private static TableView<FdMenu> tblSoup;
    private static ObservableList<FdMenu> dataSoup;
    private static TableView<FdMenu> tblDessert;
    private static ObservableList<FdMenu> dataDessert;
    private static TableView<FdMenu> tblDrinks;
    private static ObservableList<FdMenu> dataDrinks;

    private static List<String> foodOrderingFoodName = new LinkedList<>();
    private static List<Integer> foodOrderingFoodQTY = new LinkedList<>();
    private static List<Double> foodOrderingFoodSubTotal = new LinkedList<>();

    private static FoodOrdering newFoodOrder = new FoodOrdering();
    private static TextField txtInputTotalMenu;

    private static Button btnFinish;

    public static void chgScene() {
        // init 
        foodOrderingFoodName = new LinkedList<>();
        foodOrderingFoodQTY = new LinkedList<>();
        foodOrderingFoodSubTotal = new LinkedList<>();
        newFoodOrder = new FoodOrdering();

        // Heading
        Text txtHeading = new Text("Enter food ordering details");
        txtHeading.setFont(new Font(20));

        // Table Field - Sushi
        tblSushi = new TableView<FdMenu>();
        tblSushi.setEditable(true);
        tblSushi.setItems(loadDataSushi());

        TableColumn<FdMenu, String> colFdNameSushi = new TableColumn("Food Name");
        colFdNameSushi.setCellValueFactory(
                new PropertyValueFactory<FdMenu, String>("name"));
        colFdNameSushi.setStyle("-fx-alignment: CENTER;");
        colFdNameSushi.setResizable(true);
        colFdNameSushi.setMinWidth(237);
        colFdNameSushi.setMaxWidth(237);
        colFdNameSushi.setSortable(false);

        TableColumn<FdMenu, Double> colPriceSushi = new TableColumn("Price");
        colPriceSushi.setCellValueFactory(
                new PropertyValueFactory<FdMenu, Double>("price"));
        colPriceSushi.setStyle("-fx-alignment: CENTER;");
        colPriceSushi.setResizable(true);
        colPriceSushi.setMinWidth(237);
        colPriceSushi.setMaxWidth(237);
        colPriceSushi.setSortable(false);

        tblSushi.getColumns().addAll(colFdNameSushi, colPriceSushi);
        tblSushi.setPlaceholder(new Label("No rows to display"));
        tblSushi.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdMenu> selSushi;
            selSushi = tblSushi.getSelectionModel().getSelectedItems();

            selSushi.forEach((n) -> {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Food Ordering - Menu");
                dialog.setHeaderText("You choose: " + n.getName());
                dialog.setContentText("How many you want to order: ");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    foodOrderingFoodName.add(n.getName());
                    foodOrderingFoodQTY.add(Integer.parseInt(result.get()));
                    double newSubTotal = Integer.parseInt(result.get()) * n.getPrice();
                    foodOrderingFoodSubTotal.add(newSubTotal);

                    newFoodOrder.setFoodOrderTotal(newSubTotal);
                    txtInputTotalMenu.setText(String.valueOf(newFoodOrder.getFoodOrderTotal()));
                }

            });
        });

        VBox paneSushi = new VBox(20, tblSushi);
        paneSushi.setPadding(new Insets(20, 25, 20, 25));
        paneSushi.setAlignment(Pos.CENTER);

        // Table Field - Noodles
        tblNoodles = new TableView<FdMenu>();
        tblNoodles.setEditable(true);
        tblNoodles.setItems(loadDataNoodles());

        TableColumn<FdMenu, String> colFdNameNoodles = new TableColumn("Food Name");
        colFdNameNoodles.setCellValueFactory(
                new PropertyValueFactory<FdMenu, String>("name"));
        colFdNameNoodles.setStyle("-fx-alignment: CENTER;");
        colFdNameNoodles.setResizable(true);
        colFdNameNoodles.setMinWidth(237);
        colFdNameNoodles.setMaxWidth(237);
        colFdNameNoodles.setSortable(false);

        TableColumn<FdMenu, Double> colPriceNoodles = new TableColumn("Price");
        colPriceNoodles.setCellValueFactory(
                new PropertyValueFactory<FdMenu, Double>("price"));
        colPriceNoodles.setStyle("-fx-alignment: CENTER;");
        colPriceNoodles.setResizable(true);
        colPriceNoodles.setMinWidth(237);
        colPriceNoodles.setMaxWidth(237);
        colPriceNoodles.setSortable(false);

        tblNoodles.getColumns().addAll(colFdNameNoodles, colPriceNoodles);
        tblNoodles.setPlaceholder(new Label("No rows to display"));
        tblNoodles.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdMenu> selNoodles;
            selNoodles = tblNoodles.getSelectionModel().getSelectedItems();
            selNoodles.forEach((n) -> {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Food Ordering - Menu");
                dialog.setHeaderText("You choose: " + n.getName());
                dialog.setContentText("How many you want to order: ");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    foodOrderingFoodName.add(n.getName());
                    foodOrderingFoodQTY.add(Integer.parseInt(result.get()));
                    double newSubTotal = Integer.parseInt(result.get()) * n.getPrice();
                    foodOrderingFoodSubTotal.add(newSubTotal);

                    newFoodOrder.setFoodOrderTotal(newSubTotal);
                    txtInputTotalMenu.setText(String.valueOf(newFoodOrder.getFoodOrderTotal()));
                }
            });
        });

        VBox paneNoodles = new VBox(20, tblNoodles);
        paneNoodles.setPadding(new Insets(20, 25, 20, 25));
        paneNoodles.setAlignment(Pos.CENTER);

        // Table Field - Soup
        tblSoup = new TableView<FdMenu>();
        tblSoup.setEditable(true);
        tblSoup.setItems(loadDataSoup());

        TableColumn<FdMenu, String> colFdNameSoup = new TableColumn("Food Name");
        colFdNameSoup.setCellValueFactory(
                new PropertyValueFactory<FdMenu, String>("name"));
        colFdNameSoup.setStyle("-fx-alignment: CENTER;");
        colFdNameSoup.setResizable(true);
        colFdNameSoup.setMinWidth(237);
        colFdNameSoup.setMaxWidth(237);
        colFdNameSoup.setSortable(false);

        TableColumn<FdMenu, Double> colPriceSoup = new TableColumn("Price");
        colPriceSoup.setCellValueFactory(
                new PropertyValueFactory<FdMenu, Double>("price"));
        colPriceSoup.setStyle("-fx-alignment: CENTER;");
        colPriceSoup.setResizable(true);
        colPriceSoup.setMinWidth(237);
        colPriceSoup.setMaxWidth(237);
        colPriceSoup.setSortable(false);

        tblSoup.getColumns().addAll(colFdNameSoup, colPriceSoup);
        tblSoup.setPlaceholder(new Label("No rows to display"));
        tblSoup.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdMenu> selSoup;
            selSoup = tblSoup.getSelectionModel().getSelectedItems();
            selSoup.forEach((n) -> {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Food Ordering - Menu");
                dialog.setHeaderText("You choose: " + n.getName());
                dialog.setContentText("How many you want to order: ");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    foodOrderingFoodName.add(n.getName());
                    foodOrderingFoodQTY.add(Integer.parseInt(result.get()));
                    double newSubTotal = Integer.parseInt(result.get()) * n.getPrice();
                    foodOrderingFoodSubTotal.add(newSubTotal);

                    newFoodOrder.setFoodOrderTotal(newSubTotal);
                    txtInputTotalMenu.setText(String.valueOf(newFoodOrder.getFoodOrderTotal()));
                }
            });
        });

        VBox paneSoup = new VBox(20, tblSoup);
        paneSoup.setPadding(new Insets(20, 25, 20, 25));
        paneSoup.setAlignment(Pos.CENTER);

        // Table Field - Dessert
        tblDessert = new TableView<FdMenu>();
        tblDessert.setEditable(true);
        tblDessert.setItems(loadDataDessert());

        TableColumn<FdMenu, String> colFdNameDessert = new TableColumn("Food Name");
        colFdNameDessert.setCellValueFactory(
                new PropertyValueFactory<FdMenu, String>("name"));
        colFdNameDessert.setStyle("-fx-alignment: CENTER;");
        colFdNameDessert.setResizable(true);
        colFdNameDessert.setMinWidth(237);
        colFdNameDessert.setMaxWidth(237);
        colFdNameDessert.setSortable(false);

        TableColumn<FdMenu, Double> colPriceDessert = new TableColumn("Price");
        colPriceDessert.setCellValueFactory(
                new PropertyValueFactory<FdMenu, Double>("price"));
        colPriceDessert.setStyle("-fx-alignment: CENTER;");
        colPriceDessert.setResizable(true);
        colPriceDessert.setMinWidth(237);
        colPriceDessert.setMaxWidth(237);
        colPriceDessert.setSortable(false);

        tblDessert.getColumns().addAll(colFdNameDessert, colPriceDessert);
        tblDessert.setPlaceholder(new Label("No rows to display"));
        tblDessert.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdMenu> selDessert;
            selDessert = tblDessert.getSelectionModel().getSelectedItems();
            selDessert.forEach((n) -> {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Food Ordering - Menu");
                dialog.setHeaderText("You choose: " + n.getName());
                dialog.setContentText("How many you want to order: ");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    foodOrderingFoodName.add(n.getName());
                    foodOrderingFoodQTY.add(Integer.parseInt(result.get()));
                    double newSubTotal = Integer.parseInt(result.get()) * n.getPrice();
                    foodOrderingFoodSubTotal.add(newSubTotal);

                    newFoodOrder.setFoodOrderTotal(newSubTotal);
                    txtInputTotalMenu.setText(String.valueOf(newFoodOrder.getFoodOrderTotal()));
                }
            });
        });

        VBox paneDessert = new VBox(20, tblDessert);
        paneDessert.setPadding(new Insets(20, 25, 20, 25));
        paneDessert.setAlignment(Pos.CENTER);

        // Table Field - Drinks
        tblDrinks = new TableView<FdMenu>();
        tblDrinks.setEditable(true);
        tblDrinks.setItems(loadDataDrinks());

        TableColumn<FdMenu, String> colFdNameDrinks = new TableColumn("Food Name");
        colFdNameDrinks.setCellValueFactory(
                new PropertyValueFactory<FdMenu, String>("name"));
        colFdNameDrinks.setStyle("-fx-alignment: CENTER;");
        colFdNameDrinks.setResizable(true);
        colFdNameDrinks.setMinWidth(237);
        colFdNameDrinks.setMaxWidth(237);
        colFdNameDrinks.setSortable(false);

        TableColumn<FdMenu, Double> colPriceDrinks = new TableColumn("Price");
        colPriceDrinks.setCellValueFactory(
                new PropertyValueFactory<FdMenu, Double>("price"));
        colPriceDrinks.setStyle("-fx-alignment: CENTER;");
        colPriceDrinks.setResizable(true);
        colPriceDrinks.setMinWidth(237);
        colPriceDrinks.setMaxWidth(237);
        colPriceDrinks.setSortable(false);

        tblDrinks.getColumns().addAll(colFdNameDrinks, colPriceDrinks);
        tblDrinks.setPlaceholder(new Label("No rows to display"));
        tblDrinks.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdMenu> selDrinks;
            selDrinks = tblDrinks.getSelectionModel().getSelectedItems();
            selDrinks.forEach((n) -> {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Food Ordering - Menu");
                dialog.setHeaderText("You choose: " + n.getName());
                dialog.setContentText("How many you want to order: ");

                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    foodOrderingFoodName.add(n.getName());
                    foodOrderingFoodQTY.add(Integer.parseInt(result.get()));
                    double newSubTotal = Integer.parseInt(result.get()) * n.getPrice();
                    foodOrderingFoodSubTotal.add(newSubTotal);

                    newFoodOrder.setFoodOrderTotal(newSubTotal);
                    txtInputTotalMenu.setText(String.valueOf(newFoodOrder.getFoodOrderTotal()));
                }
            });
        });

        VBox paneDrinks = new VBox(20, tblDrinks);
        paneDrinks.setPadding(new Insets(20, 25, 20, 25));
        paneDrinks.setAlignment(Pos.CENTER);

        // Table Field - TabPane
        TabPane tabPane = new TabPane();
        Tab tabSushi = new Tab("Sushi", paneSushi);
        Tab tabNoodles = new Tab("Noodles", paneNoodles);
        Tab tabSoup = new Tab("Soup", paneSoup);
        Tab tabDessert = new Tab("Dessert", paneDessert);
        Tab tabDrinks = new Tab("Drinks", paneDrinks);

        tabPane.getTabs().add(tabSushi);
        tabPane.getTabs().add(tabNoodles);
        tabPane.getTabs().add(tabSoup);
        tabPane.getTabs().add(tabDessert);
        tabPane.getTabs().add(tabDrinks);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getStyleClass().add("floating");
        tabPane.setTabMinWidth(90);
        tabPane.setTabMaxWidth(90);
        tabPane.setFocusTraversable(false);
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
        });

        // Total Amount Field
        Text txtTotal = new Text("Total Amount: ");
        txtTotal.setFont(new Font(15));

        txtInputTotalMenu = new TextField("0");
        txtInputTotalMenu.setPrefColumnCount(70);
        txtInputTotalMenu.setMaxWidth(Double.MAX_VALUE);

        txtInputTotalMenu.setEditable(false);

        txtInputTotalMenu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (txtInputTotalMenu.getText().trim().equals("0") || txtInputTotalMenu.getText().trim().equals("0.0")) {
                btnFinish.setDisable(true);
            } else {
                btnFinish.setDisable(false);
            }
        });

        HBox paneTotal = new HBox(10, txtTotal, txtInputTotalMenu);

        // Bottom Field
        Button btnCart = new Button("Show Cart");
        btnCart.setPrefWidth(80);
        btnCart.setOnAction(e -> {
            tblSushi.getSelectionModel().clearSelection();
            OrdFoodCart.showStage();
        });

        Button btnClear = new Button("Clear Selection");
        btnClear.setPrefWidth(100);
        btnClear.setOnAction(e -> {
            tblSushi.getSelectionModel().clearSelection();
            tblNoodles.getSelectionModel().clearSelection();
            tblSoup.getSelectionModel().clearSelection();
            tblDessert.getSelectionModel().clearSelection();
            tblDrinks.getSelectionModel().clearSelection();
        });

        Region spcrBot = new Region();

        btnFinish = new Button("Finish");
        btnFinish.setPrefWidth(80);
        btnFinish.setDefaultButton(true);
        btnFinish.setOnAction(e -> {
            ButtonType btnPrintFood = new ButtonType("Print Details",
                    ButtonBar.ButtonData.OK_DONE);
            Alert alrtPrintDetailsFood = new Alert(AlertType.INFORMATION,
                    "Press the button to print the receipt", btnPrintFood);
            alrtPrintDetailsFood.setTitle("Food Ordering - Menu");
            alrtPrintDetailsFood.setHeaderText("Food Ordering Created Successfully");

            Optional<ButtonType> result = alrtPrintDetailsFood.showAndWait();
            if (result.get() == btnPrintFood) {
                printPDF();
                Main.getStgMain().setScene(Main.getScnMain());
            }

        });

        btnFinish.setDisable(true);

        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setCancelButton(true);
        btnCancel.setOnAction(e -> {
            ButtonType btnPrintReservation = new ButtonType("Print Details",
                    ButtonBar.ButtonData.OK_DONE);
            Alert alrtPrintDetailsReservation = new Alert(AlertType.INFORMATION,
                    "Press the button to print the receipt", btnPrintReservation);
            alrtPrintDetailsReservation.setTitle("Reservation - Customer");
            alrtPrintDetailsReservation.setHeaderText("Reservation Created Successfully");

            Optional<ButtonType> result = alrtPrintDetailsReservation.showAndWait();
            if (result.get() == btnPrintReservation) {
                printPreviousPDF();
                Main.getStgMain().setScene(Main.getScnMain());
            }
        });

        // Setup Panes
        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        VBox paneCenter = new VBox(20, tabPane, paneTotal);
        paneCenter.setPadding(new Insets(0, 25, 20, 25));
        paneCenter.setAlignment(Pos.CENTER);

        HBox paneBottom = new HBox(10, btnCart, btnClear, spcrBot, btnFinish, btnCancel);
        paneBottom.setHgrow(spcrBot, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Panes to BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        // Setup: Scene, Stage
        Scene primaryScene = new Scene(paneMain, 580, 550);
        Main.getStgMain().setScene(primaryScene);
        Main.getStgMain().setTitle("Food Ordering - Menu");
        Main.getStgMain().setResizable(false);
        Main.getStgMain().show();
    }

    public static List<String> getFoodOrderingFoodName() {
        return foodOrderingFoodName;
    }

    public static void setFoodOrderingFoodName(List<String> foodOrderingFoodName) {
        OrdFoodMenu.foodOrderingFoodName = foodOrderingFoodName;
    }

    public static List<Integer> getFoodOrderingFoodQTY() {
        return foodOrderingFoodQTY;
    }

    public static void setFoodOrderingFoodQTY(List<Integer> foodOrderingFoodQTY) {
        OrdFoodMenu.foodOrderingFoodQTY = foodOrderingFoodQTY;
    }

    public static List<Double> getFoodOrderingFoodSubTotal() {
        return foodOrderingFoodSubTotal;
    }

    public static void setFoodOrderingFoodSubTotal(List<Double> foodOrderingFoodSubTotal) {
        OrdFoodMenu.foodOrderingFoodSubTotal = foodOrderingFoodSubTotal;
    }

    public static FoodOrdering getNewFoodOrder() {
        return newFoodOrder;
    }

    public static void setNewFoodOrder(FoodOrdering newFoodOrder) {
        OrdFoodMenu.newFoodOrder = newFoodOrder;
    }

    public static TextField getTxtInputTotalMenu() {
        return txtInputTotalMenu;
    }

    public static void setTxtInputTotalMenu(TextField txtInputTotalMenu) {
        OrdFoodMenu.txtInputTotalMenu = txtInputTotalMenu;
    }

    public static ObservableList<FdMenu> loadDataSushi() {
        dataSushi = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT FoodName, UnitPrice "
                    + "FROM food "
                    + "WHERE Category = 'Sushi' ");

            while (rs.next()) {
                dataSushi.add(new FdMenu(rs.getString(1), Double.parseDouble(rs.getString(2))));
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

        return dataSushi;
    }

    public static ObservableList<FdMenu> loadDataNoodles() {
        dataNoodles = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT FoodName, UnitPrice "
                    + "FROM food "
                    + "WHERE Category = 'Noodles' ");

            while (rs.next()) {
                dataNoodles.add(new FdMenu(rs.getString(1), Double.parseDouble(rs.getString(2))));
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

        return dataNoodles;
    }

    public static ObservableList<FdMenu> loadDataSoup() {
        dataSoup = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT FoodName, UnitPrice "
                    + "FROM food "
                    + "WHERE Category = 'Soup' ");

            while (rs.next()) {
                dataSoup.add(new FdMenu(rs.getString(1), Double.parseDouble(rs.getString(2))));
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

        return dataSoup;
    }

    public static ObservableList<FdMenu> loadDataDessert() {
        dataDessert = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT FoodName, UnitPrice "
                    + "FROM food "
                    + "WHERE Category = 'Dessert' ");

            while (rs.next()) {
                dataDessert.add(new FdMenu(rs.getString(1), Double.parseDouble(rs.getString(2))));
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

        return dataDessert;
    }

    public static ObservableList<FdMenu> loadDataDrinks() {
        dataDrinks = FXCollections.observableArrayList();

        Connection connection = null;
        Statement selectStmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");

            selectStmt = connection.createStatement();
            ResultSet rs = selectStmt.executeQuery(
                    "SELECT FoodName, UnitPrice "
                    + "FROM food "
                    + "WHERE Category = 'Drinks' ");

            while (rs.next()) {
                dataDrinks.add(new FdMenu(rs.getString(1), Double.parseDouble(rs.getString(2))));
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

        return dataDrinks;
    }

    public static void printPDF() {

        try {
            if ("Premium".equals(PremCustomer.getNewCustomer().getCustomerType())) {
                SQLOrdFoodMenu.toSQL();
                PremCusReservation.getNewReceipt().setAddReceiptTotalCost(OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal());
                PrintPremOrdFoodMenu.print();
                PremCusReservation.getNewReceipt().setNewReceiptType(" + Food Ordering Fee");
                        
                SQLPremCusReservation.toSQL();

                Connection connection = null;
                Statement stmt = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");
                    stmt = connection.createStatement();

                    stmt.execute("UPDATE reservation SET OrderFoodID = "
                            + "'" + OrdFoodMenu.getNewFoodOrder().getFoodOrderingID() + "' "
                            + "WHERE ReservationID = "
                            + "'" + PremCustomer.getPremReservation().getReservationID() + "' ");
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

                
            } else {
                SQLOrdFoodMenu.toSQL();

                RegCusReservation.getNewReceipt().setAddReceiptTotalCost(OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal());
                RegCusReservation.getNewReceipt().setReceiptType("Main Dining Hall Reservation Fee + Food Ordering");
                SQLRegCusReservation.toSQL();

                Connection connection = null;
                Statement stmt = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/sgf_database", "root", "");
                    stmt = connection.createStatement();

                    stmt.execute("UPDATE reservation SET OrderFoodID = "
                            + "'" + OrdFoodMenu.getNewFoodOrder().getFoodOrderingID() + "' "
                            + "WHERE ReservationID = "
                            + "'" + RegCustomer.getNewReservation().getReservationID() + "' ");;
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

                PrintRegOrdFoodMenu.print();
            }
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printPreviousPDF() {
        try {
            if ("Premium".equals(PremCustomer.getNewCustomer().getCustomerType())) {
                SQLPremCusReservation.toSQL();
                PrintPremCusReservation.print();
            } else {
                SQLRegCusReservation.toSQL();
                PrintRegCusReservation.print();
            }
        } catch (IOException ex) {
            Logger.getLogger(NwMembership.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package secretgardenfood;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.*;

/**
 * Food Ordering - Cart Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class OrdFoodCart {

    private static TableView<FdCart> tblCart;
    private static ObservableList<FdCart> dataCart;
    
    private static TextField txtInputTotal;

    public static void showStage() {
        // Different Stage
        Stage primaryStage = new Stage();

        // Heading
        Text txtHeading = new Text("Manage customers food order cart");
        txtHeading.setFont(new Font(20));

        // Table Field - Cart
        tblCart = new TableView<FdCart>();
        tblCart.setEditable(true);
        tblCart.setItems(loadDataCart());

        TableColumn<FdCart, String> colFdNameCart = new TableColumn("Food Name");
        colFdNameCart.setCellValueFactory(
                new PropertyValueFactory<FdCart, String>("name"));
        colFdNameCart.setStyle("-fx-alignment: CENTER;");
  
        colFdNameCart.setResizable(true);
        colFdNameCart.setMinWidth(151);
        colFdNameCart.setMaxWidth(151);
        colFdNameCart.setSortable(false);
  
        
        TableColumn<FdCart, Integer> colQTYCart = new TableColumn("QTY");
        colQTYCart.setCellValueFactory(
                new PropertyValueFactory<FdCart, Integer>("QTY"));
        colQTYCart.setStyle("-fx-alignment: CENTER;");
    
        colQTYCart.setResizable(true);
        colQTYCart.setMinWidth(151);
        colQTYCart.setMaxWidth(151);
        colQTYCart.setSortable(false);

        
        TableColumn<FdCart, Double> colPriceCart = new TableColumn("Price");
        colPriceCart.setCellValueFactory(
                new PropertyValueFactory<FdCart, Double>("price"));
        colPriceCart.setStyle("-fx-alignment: CENTER;");

        colPriceCart.setResizable(true);
        colPriceCart.setMinWidth(151);
        colPriceCart.setMaxWidth(151);
        colPriceCart.setSortable(false);


        tblCart.getColumns().addAll(colFdNameCart, colQTYCart, colPriceCart);
        tblCart.setPlaceholder(new Label("No rows to display"));
        tblCart.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            ObservableList<FdCart> selCart;
            selCart = tblCart.getSelectionModel().getSelectedItems();
            selCart.forEach((n) -> {

                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Food Ordering Cart");
                alert.setHeaderText("You choose: " + n.getName());
                alert.setContentText("Delete this item in the cart?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    int indexDelete = OrdFoodMenu.getFoodOrderingFoodName().indexOf(n.getName());
                    
                    OrdFoodMenu.getFoodOrderingFoodName().remove(indexDelete);
                    OrdFoodMenu.getFoodOrderingFoodQTY().remove(indexDelete);
                    OrdFoodMenu.getNewFoodOrder().setMinFoodOrderTotal(n.getPrice());
                    txtInputTotal.setText(String.valueOf(OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal()));
                    OrdFoodMenu.getTxtInputTotalMenu().setText(String.valueOf(OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal()));
                    OrdFoodMenu.getFoodOrderingFoodSubTotal().remove(indexDelete);
                           
                    primaryStage.close();
                } 
            });
        });

        VBox paneCart = new VBox(20, tblCart);
        paneCart.setPadding(new Insets(20, 25, 20, 25));
        paneCart.setAlignment(Pos.CENTER);

        // Total Amount Field
        Text txtTotal = new Text("Total Amount: ");
        txtTotal.setFont(new Font(15));

        txtInputTotal = new TextField();
        txtInputTotal.setPrefColumnCount(70);
        txtInputTotal.setMaxWidth(Double.MAX_VALUE);
        txtInputTotal.setEditable(false);
        txtInputTotal.setText(String.valueOf(OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal()));

        HBox paneTotal = new HBox(10, txtTotal, txtInputTotal);
        paneTotal.setPadding(new Insets(0, 25, 0, 25));

        // Bottom Field
        Region spcrBot = new Region();

        Button btnClear = new Button("Clear Selection");
        btnClear.setPrefWidth(100);
        btnClear.setOnAction(e -> {
            tblCart.getSelectionModel().clearSelection();
        });

        Button btnBack = new Button("Back");
        btnBack.setPrefWidth(80);
        btnBack.setCancelButton(true);
        btnBack.setOnAction(e -> {
            primaryStage.close();
        });

        // Setup Panes
        HBox paneTop = new HBox(txtHeading);
        paneTop.setPadding(new Insets(20, 25, 20, 25));

        VBox paneCenter = new VBox(10, paneCart, paneTotal);
        paneCenter.setPadding(new Insets(20, 0, 20, 0));
        paneCenter.setAlignment(Pos.CENTER);

        HBox paneBottom = new HBox(10, spcrBot, btnClear, btnBack);
        paneBottom.setHgrow(spcrBot, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 25, 20, 25));

        // Panes to BorderPane
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);

        Scene primaryScene = new Scene(paneMain, 500, 450);
        primaryStage.setScene(primaryScene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Food Ordering Cart");
        primaryStage.showAndWait();
    }

    public static ObservableList<FdCart> loadDataCart() {
        dataCart = FXCollections.observableArrayList();
       
        for (int x = 0; x < OrdFoodMenu.getFoodOrderingFoodName().size(); x++) {
            dataCart.add(new FdCart(OrdFoodMenu.getFoodOrderingFoodName().get(x), 
                    OrdFoodMenu.getFoodOrderingFoodQTY().get(x), 
                    OrdFoodMenu.getFoodOrderingFoodSubTotal().get(x)));
        }
        
        return dataCart;
    }
}

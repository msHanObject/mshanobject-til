package javafx.tutorial.lessen17;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxTutorial17 extends Application {
	//
	Stage window;
	TableView<Product> table;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		window = primaryStage;
		window.setTitle("TableView");
		
		//Name column
		TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//Price column
		TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(100);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//Quantity column
		TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setMinWidth(100);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		table = new TableView<>();
		table.setItems(getProduct());
		table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	//Get all of the products
	public ObservableList<Product> getProduct() {
		//
		ObservableList<Product> products = FXCollections.observableArrayList();
		products.add(new Product("Laptop", 859.00, 20));
		products.add(new Product("Bouncy Ball", 2.49, 198));
		products.add(new Product("Toilet", 99.00, 74));
		products.add(new Product("The Notebook DVD", 19.99, 12));
		products.add(new Product("Corn", 1.49, 856));
		
		return products;
	}
}
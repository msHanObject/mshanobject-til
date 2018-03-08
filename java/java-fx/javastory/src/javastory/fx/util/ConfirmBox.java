package javastory.fx.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	//
	private static boolean answer;
	
	public static boolean display(String title, String message) {
		//
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		//layout
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setAlignment(Pos.CENTER);
		
		//label
		Label label = new Label();
		label.setText(message);
		grid.add(label, 0, 0, 2, 1);
		
		//Create yes, no button
		Button yesButton = new Button("Yes");
		grid.add(yesButton, 0, 1);
		
		Button noButton = new Button("No");
		grid.add(noButton, 1, 1);
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		

		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
}

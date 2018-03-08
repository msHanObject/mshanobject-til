package javafx.tutorial.lessen3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxTutorial3_2 extends Application {

	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		primaryStage.setTitle("Title of the Window");
		button = new Button();
		button.setText("Hey baby");	
		button.setOnAction(e -> {
			System.out.println("hey now brown cow");
			System.out.println("I am meatball");
		});
			
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		
		Scene scene = new Scene(layout, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
package javafx.tutorial.lessen10;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxTutorial10 extends Application{
	//
	Stage window;
	Button button;
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		window = primaryStage;
		window.setTitle("mshanObject");
		
		//Form
		TextField nameInput = new TextField();
		
		button = new Button("Click me");
		button.setOnAction(e -> {
//			System.out.println(nameInput.getText());
			isInt(nameInput, nameInput.getText());
		});
		
		//Layout
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(nameInput, button);
		
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);		
		window.show();
	}
	
	private boolean isInt(TextField input, String message) {
		try {
			int age = Integer.parseInt(input.getText());
			System.out.println("User is: " + age);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Error: " + message + " is not a number.");
			return false;
		}
	}
}

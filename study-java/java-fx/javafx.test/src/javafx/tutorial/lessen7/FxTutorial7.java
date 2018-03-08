package javafx.tutorial.lessen7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.tutorial.lessen6.ConfirmBox;

public class FxTutorial7 extends Application{
	//
	Stage window;
	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		window = primaryStage;
		window.setTitle("JavaFx - thenewboston");
		
		window.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
		
		button = new Button("Close Program");
		button.setOnAction(e -> {
			closeProgram();
		});
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
		
	}
	
	private void closeProgram() {
//		System.out.println("File is saved!");
//		window.close();
		Boolean answer = ConfirmBox.display("Title", "Sure you want to exit?");
		if (answer) {
			window.close();
		}
	}
}
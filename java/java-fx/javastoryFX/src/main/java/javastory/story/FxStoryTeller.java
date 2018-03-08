package javastory.story;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javastory.ui.window.WindowLycler;
import javastory.util.SizeHandler;

public class FxStoryTeller extends Application{
	//
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//
		primaryStage.setTitle("FxStory");
		
		Parent root = WindowLycler.shareInstance().requestMainView().getPane();
		Scene scene = new Scene(root, SizeHandler.SCREEN_FULL_WIDTH, SizeHandler.SCREEN_FULL_HEIGHT);
		primaryStage.setTitle("Javastory-Fx");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> programExit());
		primaryStage.show();
	}
	
	private void programExit() {
		// 
        Platform.exit();
        System.exit(0);
    }
}

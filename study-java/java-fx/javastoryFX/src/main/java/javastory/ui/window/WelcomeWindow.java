package javastory.ui.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.util.SizeHandler;

public class WelcomeWindow {

	private Stage window;
	private ImageView welcomeImgView;
	private Button startButton;
	
	public void display() {
		//
		this.setWindow();
	}

	private void setWindow() {
		// 
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		String title = new String("Welcome to FxStory World");
		window.setTitle(title);
		
		//Make stackPane
		StackPane stackPane = new StackPane();
		stackPane.setStyle("-fx-background-color:#FFFFFF;");
		
		setImages();
		setButton();
		//Wrap children with VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(welcomeImgView, startButton);
		vBox.setAlignment(Pos.CENTER);
		
		stackPane.getChildren().add(vBox);
		Scene scene = new Scene(stackPane, SizeHandler.POPUP_SCREEN_WIDTH, SizeHandler.POPUP_SCREEN_HEIGHT);
		window.setScene(scene);
		window.showAndWait();
	}
	


	private void setButton() {
		// 
		startButton = new Button("Start");
		startButton.setGraphic(welcomeImgView);
		startButton.setContentDisplay(ContentDisplay.TOP);
		startButton.setStyle("-fx-background-color:#FFFFFF;");
		startButton.setOnMouseClicked( e -> {
			WindowLycler.shareInstance().requestLoginWindow().display(); 
			window.close();
		});
	}


	private void setImages() {
		//Get image resource
		Image welcomeImage = new Image(getClass().getClassLoader().getResourceAsStream("images/rocket.png"));
		welcomeImgView = new ImageView(welcomeImage);
		//Resize imageView
		welcomeImgView.setFitWidth(SizeHandler.POPUP_HALF_WIDTH);
		welcomeImgView.setFitHeight(SizeHandler.POPUP_HALF_HEIGHT);
		welcomeImgView.setPreserveRatio(true);
	}
}

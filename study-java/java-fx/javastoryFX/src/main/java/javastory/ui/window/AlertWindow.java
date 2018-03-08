package javastory.ui.window;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.util.SizeHandler;

public class AlertWindow {
	//
	private Stage window;
	private ImageView warningImgView;
	private Label warningLabel;
	private String warning;

	public AlertWindow() {
		//
	}

	public void display(String warning) {
		//
		this.warning = warning;
		this.setWindow();
	}

	private void setWindow() {
		//
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		String title = new String(this.getClass().getSimpleName());
		window.setTitle(title);
		
		creatingNodes();
		
		resizeImageView();
		
		//Wrap nodes with VBox
		VBox vBox = new VBox();
		vBox.getChildren().addAll(warningImgView, warningLabel);
		
		vBox.setAlignment(Pos.CENTER);
		vBox.setStyle("-fx-background-color:#FFFFFF");
		
		Scene scene = new Scene(vBox, SizeHandler.POPUP_QUARTER_WIDTH, SizeHandler.POPUP_QUARTER_HEIGHT);
		window.setScene(scene);
		window.showAndWait();
	}

	private void resizeImageView() {
		// Resizing ImageView
		warningImgView.setFitWidth(SizeHandler.WARNING_ICON_WIDTH);
		warningImgView.setFitHeight(SizeHandler.WARNING_ICON_HEIGHT);
	}

	private void creatingNodes() {
		//Creating Nodes
		Image warningImage = new Image(getClass().getClassLoader().getResourceAsStream("images/rocket.png"));
		warningImgView = new ImageView(warningImage);
		
		warningLabel = new Label(warning);
	}
}

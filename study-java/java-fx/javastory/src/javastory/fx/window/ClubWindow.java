package javastory.fx.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.console.ClubConsole;
import javastory.fx.util.ConfirmBox;

public class ClubWindow {
	//
	private String title;
	private TextField q1Input, q2Input;
	private Button register, modify, delete, cancel;
	private ClubConsole clubConsole;
	private TravelClubDto currentClub;
	
	public ClubWindow(String title, ClubConsole clubConsole) {
		//
		this.title = title;
		this.clubConsole = clubConsole;
		q1Input = new TextField();
		q2Input = new TextField();
		register = new Button("register");
		modify = new Button("modify");
		delete = new Button("delete");
		cancel = new Button("cancel");
	}
	
	private void init() {
		//
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(20,0,0,40));
		grid.setAlignment(Pos.CENTER);
		
		//HeadLine
		Text headLine = new Text("TravelClub");
		headLine.setFont(Font.font("Arial", 20));
	    grid.add(headLine, 1, 0, 2, 2); 
		
		//Question 1 label
		Label q1 = new Label();
		q1.setText("• name: ");
		q1.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q1, 0, 3);
		
		//Question 1 input
		q1Input.setMaxWidth(140);
		grid.add(q1Input, 1, 3, 2, 1);
		
		//Question 2 label
		Label q2 = new Label();
		q2.setText("• intro: ");
		q2.setAlignment(Pos.CENTER_RIGHT);
		grid.add(q2, 0, 5);
		
		//Question 2 input
		q2Input.setMaxWidth(140);
		grid.add(q2Input, 1, 5, 2, 1);
	
		//register button
		grid.add(register, 0, 7);
		register.setOnAction(e -> {
			String clubName = q1Input.getText();
			String intro = q2Input.getText();
			clubConsole.register(clubName, intro);
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		//modify button
		grid.add(modify, 1, 7);
		modify.setOnAction(e -> {
			currentClub.setName(q1Input.getText());
			currentClub.setIntro(q2Input.getText());
			clubConsole.modify(currentClub);
			register.setDisable(false);
			window.close();
		});
		
		//delete button
		grid.add(delete, 2, 7);
		delete.setOnAction(e -> {
			boolean result = ConfirmBox.display("Remove Club", "Are you sure you want to remove this club?");
			if (result) {
				clubConsole.remove(currentClub);
				register.setDisable(false);
				window.close();
			}
		});
		
		//cancel button
		grid.add(cancel, 3, 7);
		cancel.setOnAction(e -> {
			register.setDisable(false);
			modify.setDisable(false);
			delete.setDisable(false);
			window.close();
		});
		
		Scene scene = new Scene(grid, 400, 200);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void registerMode() {
		//
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}
	
	public void modifyMode(TravelClubDto targetClub) {
		//
		this.fieldClear();
		register.setDisable(true);
		String clubName = targetClub.getName();
		String clubIntro = targetClub.getIntro();
		q1Input.setText(clubName);
		q2Input.setText(clubIntro);
		currentClub = clubConsole.searchByName(clubName);
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		q1Input.setText(null);
		q2Input.setText(null);
	}
}

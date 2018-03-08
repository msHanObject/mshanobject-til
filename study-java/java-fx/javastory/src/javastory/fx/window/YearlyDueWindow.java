package javastory.fx.window;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javastory.budget.service.dto.YearlyDueDto;
import javastory.budget.share.IdName;
import javastory.fx.controller.YearlyDueController;
import javastory.fx.detail.MonthlyDueTable;
import javastory.fx.util.AlertBox;
import javastory.fx.util.ConfirmBox;

public class YearlyDueWindow {
	//
	private Scene scene;
	private String title;
	private TextField memberIdInput, memberNameInput, yearInput;
	private YearlyDueController yearlyDueConsole;
	private YearlyDueDto currentYearlyDue;
	private Button register, modify, delete, cancel;
	private MonthlyDueTable monthlyDueTable;

	public YearlyDueWindow(String title, YearlyDueController yearlyDueConsole) {
		// 
		this.title = title;
		this.yearlyDueConsole = yearlyDueConsole;
		memberIdInput = new TextField();
		memberNameInput = new TextField();
		yearInput = new TextField();
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
		
		//Border create
		BorderPane border = new BorderPane();
		
		//Grid create
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #FFFFFF;");
		grid.setVgap(10);
		grid.setHgap(20);
		grid.setPadding(new Insets(0,0,0,40));
		
		//HeadLine
		Text headLine = new Text("AccountYearlyDue");
		headLine.setFont(Font.font("Arial", 20));
		grid.add(headLine, 2, 1, 4, 2);
		
		//member id label
		Label id = new Label();
		id.setText("• id: ");
		grid.add(id, 1, 5);
		//member id input
		memberIdInput.setMaxWidth(140);
		grid.add(memberIdInput, 2, 5, 2, 1);
		
		//member name label
		Label name = new Label();
		name.setText("• namd: ");
		grid.add(name, 5, 5);
		//mmeber name input
		memberNameInput.setMaxWidth(140);
		grid.add(memberNameInput, 6, 5);
		
		//year label
		Label year = new Label();
		year.setText("• year: ");
		grid.add(year, 1, 6);
		//year input
		yearInput.setMaxWidth(140);
		grid.add(yearInput, 2, 6);
		
		//Border set top
		border.setTop(grid);
		
		//create monthlyDue table
		monthlyDueTable = new MonthlyDueTable(currentYearlyDue);
		//Border set center
		VBox tableBox = monthlyDueTable.addTable();
		tableBox.setPadding(new Insets(10,10,10,10));
		border.setCenter(tableBox);
		
		//RMDC box
		HBox rmdcBox = new HBox();
		rmdcBox.setPadding(new Insets(10,10,10,10));
		rmdcBox.setSpacing(10);
		rmdcBox.setAlignment(Pos.CENTER);
		rmdcBox.setStyle("-fx-background-color: #FFFFFF;");
		
		//register button
		rmdcBox.getChildren().add(register);
		register.setOnAction( e -> {
			if (currentYearlyDue!= null) {
				currentYearlyDue = yearlyDueConsole.requestcurrentYearlyDuelyDue();
				this.setCurrentYearlyDue();
				yearlyDueConsole.register(currentYearlyDue);
			}
			window.close();
		});
		
		//modify button
		rmdcBox.getChildren().add(modify);
		modify.setOnAction( e -> {
			//Check if it has changed.
			boolean isModified = this.checkModified();
			if (isModified) {
				this.setCurrentYearlyDue();
				yearlyDueConsole.modify();
			} else {
				AlertBox.display("Modify Error", "Nothing has been modified");
				return;
			}
			window.close();
		});
		
		//delete button
		rmdcBox.getChildren().add(delete);
		delete.setOnAction( e -> {
			boolean result = ConfirmBox.display("Remove AccountYearlyDue", "Are you sure you want to remove this yearlyDue?");
			if (result) {
				yearlyDueConsole.delete(currentYearlyDue);
				window.close();
			}
		});
		
		//cancel button
		rmdcBox.getChildren().add(cancel);
		cancel.setOnAction(e -> {
			window.close();
		});
		
		
		//Border bottom set
		border.setBottom(rmdcBox);
		
		scene = new Scene(border, 600, 600);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private boolean checkModified() {
		//
		if (currentYearlyDue.getMemberId().equals(memberIdInput.getText())
			&& currentYearlyDue.getMemberName().equals(memberNameInput.getText())
			&& !monthlyDueTable.checkModified()) {
			return false;
		}
		return true;
	}
	
	private void setCurrentYearlyDue() {
		//
		IdName member = new IdName(memberIdInput.getText(), memberNameInput.getText());
		
		currentYearlyDue.setMember(member);
		currentYearlyDue.setYear(yearInput.getText());
		currentYearlyDue.setMonthlyDues(monthlyDueTable.getMonthlyDueTable().getItems());
		yearlyDueConsole.setCurrentYearlyDue(currentYearlyDue);
	}
	
	public void registerMode() {
		// 
		this.fieldClear();
		modify.setDisable(true);
		delete.setDisable(true);
		init();
	}
	
	public void modifyMode(YearlyDueDto targetYearlyDue) {
		// 
		this.fieldClear();
		register.setDisable(true);
		currentYearlyDue = targetYearlyDue;
		yearlyDueConsole.setCurrentYearlyDue(targetYearlyDue);
		memberIdInput.setText(targetYearlyDue.getMemberId());
		memberIdInput.setDisable(true);
		memberNameInput.setText(targetYearlyDue.getMemberName());
		memberNameInput.setDisable(true);
		yearInput.setText(targetYearlyDue.getYear());
		init();
	}
	
	private void fieldClear() {
		//
		register.setDisable(false);
		modify.setDisable(false);
		delete.setDisable(false);
		memberIdInput.setDisable(false);
		memberNameInput.setDisable(false);
		memberIdInput.setText(null);
		memberNameInput.setText(null);
		yearInput.setText(null);
		currentYearlyDue = new YearlyDueDto();
	}
}
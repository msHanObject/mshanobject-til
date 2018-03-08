package javafx.tutorial.lessen16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxTutorial16 extends Application{
	//
	Stage window;
	TreeView<String> tree;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// 
		window = primaryStage;
		window.setTitle("Tree View");

		TreeItem<String> root, bucky, megan;
		
		//Root
		root = new TreeItem<>();
		root.setExpanded(true);
		
		//Bucky
		bucky = makeBranch("Bucky", root);
		makeBranch("thenewboston", bucky);
		makeBranch("Youtube", bucky);
		makeBranch("Chicken", bucky);
		
		//Megan
		megan = makeBranch("Megan", root);
		makeBranch("Glitter", megan);
		makeBranch("Makeup", megan);
		
		//Create tree
		tree = new TreeView<>(root);
		tree.setShowRoot(false);
		tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue != null) {
				System.out.println(newValue.getValue());
			}
		});
		
		//Layout
		StackPane layout = new StackPane();
		layout.getChildren().add(tree);
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
	}
	
	public TreeItem<String> makeBranch(String title, TreeItem<String> parent){
		TreeItem<String> item = new  TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		
		return item;
	}
}
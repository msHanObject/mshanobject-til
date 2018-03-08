package javafx.test;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
/**
 *
 * Created on: 24.03.2012
 * @author Sebastian Damm
 */
public class FlowPaneAndTilePaneExample extends Application
{
    private Random random;
    private VBox root;        
     
    private FlowPane flowPane;
    private TilePane tilePane;
     
    @Override
    public void start(Stage primaryStage) throws Exception
    {                
        random = new Random();
        root = new VBox(30);
         
        VBox upperVBox = createUpperVBox();
        VBox lowerVBox = createLowerVBox();
 
        fillPanesWithImages();
                 
        root.getChildren().addAll(upperVBox, lowerVBox);
         
        Scene scene = new Scene(root, 800, 600, Color.ANTIQUEWHITE);
         
        primaryStage.setTitle("FlowPane and TilePane Example");
        primaryStage.setScene(scene);
        primaryStage.show();        
    }
     
    private VBox createUpperVBox()
    {
        VBox vbox = new VBox(20);
         
        Text textFlowPane = new Text("I am a FlowPane");
        textFlowPane.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        textFlowPane.setUnderline(true);
        textFlowPane.setEffect(new DropShadow());        
        VBox.setMargin(textFlowPane, new Insets(10, 0, 0, 10));
         
        flowPane = new FlowPane();
        flowPane.setHgap(5);
        flowPane.setVgap(5);
         
        vbox.getChildren().addAll(textFlowPane, flowPane);
        VBox.setMargin(vbox, new Insets(10));
         
        return vbox;
    }
     
    private VBox createLowerVBox()
    {
        VBox vbox = new VBox(20);
         
        Text textTilePane = new Text("I am a TilePane");
        textTilePane.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        textTilePane.setUnderline(true);        
        textTilePane.setEffect(new DropShadow());
        VBox.setMargin(textTilePane, new Insets(10, 0, 0, 10));
         
        tilePane = new TilePane();
        tilePane.setHgap(5);
        tilePane.setVgap(5);
                 
        vbox.getChildren().addAll(textTilePane, tilePane);
        VBox.setMargin(vbox, new Insets(10));
         
        return vbox;
    }
     
    private void fillPanesWithImages()
    {
        for (int i = 1; i <= 6; i++)
        {
            int imgSize = random.nextInt(128) + 1;
             
            Button bt = new Button();                        
            Image img = new Image(FlowPaneAndTilePaneExample.class
   .getResourceAsStream("images/person" + i + ".png"), 
   imgSize > 50 ? imgSize : 50, 0, true, false);
            ImageView view = new ImageView(img);
            bt.setGraphic(view);
             
            flowPane.getChildren().add(bt);            
             
            Button bt2 = new Button();                        
            Image img2 = new Image(FlowPaneAndTilePaneExample.class
   .getResourceAsStream("images/person" + i + ".png")
   , imgSize > 50 ? imgSize : 50, 0, true, false);
            ImageView view2 = new ImageView(img2);
            bt2.setGraphic(view2);
             
            tilePane.getChildren().add(bt2);              
        }        
    }
 
    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
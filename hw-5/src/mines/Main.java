package mines;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(); //create loading object
			loader.setLocation(getClass().getResource("Myapp.fxml"));//setting location of loading file
			HBox root =loader.load();//loading file by arrangement
			Scene scene = new Scene(root); //creating scene with the arrangement
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//design file by css
			primaryStage.sizeToScene();
			primaryStage.setMinHeight(270);
			primaryStage.setMinWidth(200);
			primaryStage.setScene(scene);//put scene in the stage
			primaryStage.setTitle("Minesweeper");
			 
			primaryStage.show();//show stage
			

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


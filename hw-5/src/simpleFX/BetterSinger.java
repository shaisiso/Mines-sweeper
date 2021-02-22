package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BetterSinger extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene (createRoot());
		stage.setTitle("Voting Machine");
		stage.setScene(scene);
		stage.show();
	}
		 
	private int i = 0;
	
	private GridPane createRoot() {
		       GridPane gridPane = new GridPane();
		       gridPane.setPrefWidth(208);
		       gridPane.setPadding(new Insets(20));
		       gridPane.setHgap(10);
		       gridPane.setVgap(10);
		       
		       Button b1 = new Button("Ofra Haza");
		       Button b2 = new Button("Yardena Arazi");
		       Label score = new Label(i + ""); 
		       
		       score.setStyle("-fx-background-color: blue");
		       score.setPrefWidth(Double.MAX_VALUE);
		       score.setAlignment(Pos.CENTER);

		       gridPane.add(b1, 0, 0, 1, 1);
		       gridPane.add(b2, 1, 0, 1, 1);
		       gridPane.add(score, 0, 2, 2, 1);
		       
		       class LabelIncreaser implements EventHandler<ActionEvent> {
		    	   @Override
		    	   public void handle(ActionEvent event) {
		    		   i++;
		    		   score.setText(i + "");
		    	   }
		       }
		       class LabelDecrease implements EventHandler<ActionEvent> {
		    	   @Override
		    	   public void handle(ActionEvent event) {
		    		   i--;
		    		   score.setText(i + "");
		    	   }
		       }
		       b1.setOnAction(new LabelIncreaser());
		       b2.setOnAction(new LabelDecrease());
		   	   return gridPane;
	}
}
		      

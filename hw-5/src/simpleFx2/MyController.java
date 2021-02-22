package simpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class MyController {

    @FXML
    private Label lblCount;
    
    private static int count = 0;
    
    @FXML
    void add(ActionEvent event) {
    	count++;
    	lblCount.setText(count + "");
    }

    @FXML
    void dec(ActionEvent event) {
    	count--;
    	lblCount.setText(count + "");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lblCount != null : "fx:id=\"lblCount\" was not injected: check your FXML file 'OvsY.fxml'.";
        lblCount.setText(count +"");
        lblCount.setAlignment(Pos.CENTER);
    }
}

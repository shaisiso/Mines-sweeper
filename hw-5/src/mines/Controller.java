package mines;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller {

	GridPane grid;
	@FXML
	private HBox hbroot;
	@FXML
	private HBox hb;

	@FXML
	private StackPane sp;
	@FXML
	private TextField t_height;

	@FXML
	private TextField t_width;

	@FXML
	private TextField t_mines;

	@FXML
	private Button set_button;
	@FXML
	private Label l_height;

	@FXML
	private Label l_width;

	@FXML
	private Label l_mines;


	@FXML
	void SetGame(ActionEvent event) {
		set_button.setText("Reset");
		Mines m;
		sp.getChildren().clear();
		int height,width,mines;
		Button [][]b;
		String []color=new String[9];
		//getting details from text
		height=Integer.parseInt(t_height.getText());
		width=Integer.parseInt(t_width.getText());
		mines=Integer.parseInt(t_mines.getText());
		b=new Button[height][width];
		GridPane grid=new GridPane();
		grid.setPrefSize(width, height);
		m=new Mines(height,width,mines); //create new game
		grid.setVgap(2);
		for (int i=0; i<height; i++) {
			grid.getRowConstraints().add(new RowConstraints(30));
		}
		for(int j=0;j<width;j++) {
			grid.getColumnConstraints().add(new ColumnConstraints(31));
		}
		//loading images
		Image imageFlag = new Image("file:flag.png");
		Image imageMine = new Image("file:mine.png");
		GridPane.setVgrow(grid, Priority.ALWAYS);
		GridPane.setHgrow(grid, Priority.ALWAYS);
		//creating buttons to game
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++) {
				b[i][j]=new Button("");
				b[i][j].setContentDisplay(ContentDisplay.LEFT);
				b[i][j].setStyle("    -fx-background-color:\r\n" + 
						"            linear-gradient(#a9ff00,#f0ff35),\r\n" + 
						"            radial-gradient(center 50% -40%, radius 200%, #80c800 45%, #b8ee36 50%);\r\n" + 
						"    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75),4,0,0,1);\r\n" + 
						"    -fx-font-weight: bold;\r\n" + 
						"    -fx-font-size: 1.1em;\r\n" + 
						"    -fx-text-fill: black;");
				b[i][j].setMaxSize(30,30);
				b[i][j].setMinSize(30,30);
				grid.add(b[i][j], j, i);
			}

		}
		//colors for numbers of mine
		color[0]="black";
		color[1]="blue";
		color[2]="green";
		color[3]="red";
		color[4]="yellow";
		for (int i=5; i<9; i++)
			color[i]="brown";
		//create on action for each button
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++) {
				class BtnXY extends Button{ //to know the place 
					int x,y;
					Button b;
					ImageView im;
					BtnXY(Button b,int x,int y){
						this.x=x;
						this.y=y;
						this.b=b;
					}
				}
				BtnXY bn=new BtnXY(b[i][j],i,j);
				b[i][j].setOnMouseClicked(e -> {
					if(e.getButton()==MouseButton.SECONDARY){ //flag or cancel flag
						if (!m.isOpen(bn.x,bn.y)) { //only if not already open
							m.toggleFlag(bn.x, bn.y);
							if(m.get(bn.x, bn.y).equals("F")) { //flag
								bn.im=new ImageView(imageFlag);
								bn.im.setFitHeight(25);	
								bn.im.setFitWidth(18);
							}
							else {
								bn.setStyle("    -fx-background-color:\r\n" + 
										"            linear-gradient(#a9ff00,#f0ff35),\r\n" + 
										"            radial-gradient(center 50% -40%, radius 200%, #80c800 45%, #b8ee36 50%);\r\n" + 
										"    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75),4,0,0,1);\r\n" + 
										"    -fx-font-weight: bold;\r\n" + 
										"    -fx-font-size: 1.1em;\r\n" + 
										"    -fx-text-fill: black;");
								bn.im=null;
							}

							bn.b.setGraphic(bn.im);
						}
					}
					else { //left click - open a square
						if (!m.open(bn.x, bn.y)) { //a mine is here
							m.setShowAll(true);
							FXMLLoader loader = new FXMLLoader(); //create loading object
							loader.setLocation(getClass().getResource("lost.fxml"));//setting location of loading file
							AnchorPane root;
							try { //loading loser file
								root = loader.load();
								Scene scene = new Scene(root); //creating scene with the arrangement
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//design file by css
								Stage stage = new Stage();
								stage.setScene(scene);//put scene in the stage
								stage.setTitle("Minesweeper");
								stage.show();//show stage
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//loading file by arrangement
						}
						int cnt_em=0;
						for (int i2=0; i2<height; i2++) //open all needed
							for (int j2=0; j2<width; j2++) {
								ImageView im2=null;
								String s=m.get(i2,j2);
								if (s.equals("F"))
									im2=new ImageView(imageFlag);
								if (s.equals(".")) {
									b[i2][j2].setStyle("    -fx-background-color:\r\n" + 
											"            linear-gradient(#a9ff00,#f0ff35),\r\n" + 
											"            radial-gradient(center 50% -40%, radius 200%, #80c800 45%, #b8ee36 50%);\r\n" + 
											"    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75),4,0,0,1);\r\n" + 
											"    -fx-font-weight: bold;\r\n" + 
											"    -fx-font-size: 1.1em;\r\n" + 
											"    -fx-text-fill: black;");
								}
								if (s.equals("X"))
									im2=new ImageView(imageMine);
								if(im2 != null) {
									im2.setFitHeight(25);	
									im2.setFitWidth(18);
								}
								b[i2][j2].setGraphic(im2);
								if (im2==null && !s.equals(".")) {
									int c=0;
									cnt_em++;
									if (!m.get(i2, j2).equals(" "))
										c=Integer.parseInt(m.get(i2, j2));
									b[i2][j2].setStyle("    -fx-background-color:\r\n" + 
											"        -fx-shadow-highlight-color,\r\n" + 
											"        linear-gradient(to bottom, derive(-fx-color,-90%) 0%, derive(-fx-color,-60%) 100%),\r\n" + 
											"        linear-gradient(to bottom, derive(-fx-color,-60%) 0%, derive(-fx-color,-35%) 50%, derive(-fx-color,-30%) 98%, derive(-fx-color,-50%) 100%),\r\n" + 
											"        linear-gradient(to right, rgba(0,0,0,0.3) 0%, rgba(0,0,0,0) 10%, rgba(0,0,0,0) 90%, rgba(0,0,0,0.3) 100%);\r\n" + 
											"    -fx-background-insets: 0 0 -1 0, 0, 1, 1;\r\n" +  
											"    -fx-font-weight: bold;\r\n" + 
											"    -fx-font-size: 1.1em;\r\n" + 
											"    -fx-text-fill: "+color[c]+";");
									b[i2][j2].setText(m.get(i2,j2));
								}
							}
						if (cnt_em==height*width-mines && !m.isLost()) {
							FXMLLoader loader = new FXMLLoader(); //create loading object
							loader.setLocation(getClass().getResource("Winner.fxml"));//setting location of loading file
							AnchorPane root;
							try { //loading loser file
								root = loader.load();
								Scene scene = new Scene(root); //creating scene with the arrangement
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//design file by css
								Stage stage = new Stage();
								stage.setScene(scene);//put scene in the stage
								stage.setTitle("Minesweeper");
								stage.show();//show stage
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//loading file by arrangement
						}
					}
				});
			}
		}
		hb.setPrefWidth(width);
		sp.getChildren().add(grid);
		sp.getScene().getWindow().sizeToScene();
	}
}



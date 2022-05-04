/**
 * 
 */
package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Azan S
 *
 */
public class gui extends GridPane {
	
	//UI Components
	private TextArea txtUsers;
	private Button btnDisplay;
	
	public gui(Stage stage) {
		
		setUI();
		
		btnDisplay.setOnAction(e->{
			System.exit(0);
		});

	}

	private void setUI() {
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		
		txtUsers = new TextArea("IDK\r\n");
		btnDisplay = new Button("Something idk");
		
		//Now place the components onto Pane
		add(txtUsers, 1, 2);
		add(btnDisplay, 2, 2);
	}

}

package pfa.chatbothealthcare.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class UserMessage extends HBox {
	private String text;
	public UserMessage(String text) {
		this.text = text;
		this.setPadding(new Insets(10,10,10,100));
		
		Label lb = new Label(text);
		lb.setStyle("-fx-font-weight: bold");
		lb.setWrapText(true);
		lb.setMinWidth(200);
		lb.setPrefWidth(200);
		lb.setMaxWidth(200);
		lb.setPadding(new Insets(10));
		
		CornerRadii corn = new CornerRadii(10);
		lb.setBackground(new Background(new  BackgroundFill(Color.web("#00b8d4"),corn,null)));
				
		this.getChildren().add(lb);
	}
}

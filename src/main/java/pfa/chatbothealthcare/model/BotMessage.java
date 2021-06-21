package pfa.chatbothealthcare.model;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class BotMessage extends HBox{

	private ImageView  img = new ImageView(getClass().getResource("/images/chatbot.png").toString());
	private Label message;
	
	
	public BotMessage(String str) {
		super(10);
		this.setAlignment(Pos.TOP_LEFT);
		message = new Label(str);
		message.setWrapText(true);
		message.setPadding(new Insets(10));
		CornerRadii corn = new CornerRadii(10);
		message.setBackground(new Background(new  BackgroundFill(Color.web("#fce4ec"),corn,null)));
		message.setMaxWidth(200);
		message.setStyle("-fx-font-weight: bold;");
		
		this.setMaxWidth(300);
		
		this.getChildren().addAll(img,message);		
	}
	
}

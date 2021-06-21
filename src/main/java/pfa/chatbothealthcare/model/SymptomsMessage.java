package pfa.chatbothealthcare.model;

import java.io.PrintWriter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SymptomsMessage extends HBox{
	//private ImageView  img = new ImageView(getClass().getResource("/images/chatbot.png").toString());
	private Label name;
	private Button yes;
	private Button non;
	private boolean result;
	private PrintWriter writer;
	
	public SymptomsMessage(PrintWriter writer ,String name) {
		super(5);
		this.writer = writer;
		this.setPadding(new Insets(0,0,0,60));
		this.setAlignment(Pos.CENTER_LEFT);
		this.name = new Label(name);
		this.name.setWrapText(true);
		this.name.setPadding(new Insets(5,0,5,10));
		this.name.setPrefWidth(200);
		this.name.setMaxWidth(200);
		this.name.setMinWidth(200);
		CornerRadii corn = new CornerRadii(10);
		this.name.setBackground(new Background(new  BackgroundFill(Color.web("#fce4ec"),corn,null)));
		this.name.setStyle("-fx-font-weight: bold;-fx-border-radius : 5");
		//this.name.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 14	));
		yes = new Button("",new ImageView(getClass().getResource("/images/buttons/yes.png").toString()));
		yes.setBackground(new Background(new BackgroundFill(Color.web("#ffffff",0), null, null)));
		non = new Button("",new ImageView(getClass().getResource("/images/buttons/no.png").toString()));
		non.setBackground(new Background(new BackgroundFill(Color.web("#ffffff",0), null, null)));
		
		this.getChildren().addAll(this.name,yes,non);
		
		this.setMaxWidth(200);
		
		
		initListener();
		
	}
	
	private void initListener() {
		yes.setOnMouseClicked(e ->{
			this.getChildren().removeAll(yes,non);
			CornerRadii corn = new CornerRadii(10);
			this.name.setBackground(new Background(new  BackgroundFill(Color.web("#76ff03"),corn,null)));
			writer.println("yes");
			
		});
		non.setOnMouseClicked(e ->{
			this.getChildren().removeAll(yes,non);
			CornerRadii corn = new CornerRadii(10);
			this.name.setBackground(new Background(new  BackgroundFill(Color.web("#ff1744"),corn,null)));
			writer.println("no");
		});
		
		yes.setOnMouseEntered(e ->{
			yes.setGraphic(new ImageView(getClass().getResource("/images/buttons/yeshover.png").toString()));
		});
		non.setOnMouseEntered(e ->{
			non.setGraphic(new ImageView(getClass().getResource("/images/buttons/nohover.png").toString()));
		});
		yes.setOnMouseExited(e ->{
			yes.setGraphic(new ImageView(getClass().getResource("/images/buttons/yes.png").toString()));
		});
		non.setOnMouseExited(e ->{
			non.setGraphic(new ImageView(getClass().getResource("/images/buttons/no.png").toString()));
		});
	}
	
	public boolean getResult() {
		return result;
	}
	
}

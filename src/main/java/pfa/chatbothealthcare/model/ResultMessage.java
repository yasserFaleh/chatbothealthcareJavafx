package pfa.chatbothealthcare.model;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ResultMessage extends HBox {
	private ImageView  img = new ImageView(getClass().getResource("/images/chatbot.png").toString());
	private VBox 	   conteneur;
	private List<Disease> diseases;
	private List<String> precautions;
	
	public ResultMessage(List<Disease> diseases ,List<String> precautions) {
		if ( diseases != null && diseases.size() > 0) {
			this.diseases 		= diseases;
			this.precautions 	= precautions;
			conteneur 			= new VBox();
			conteneur.setPadding(new Insets(10));
			
			conteneur.setPrefWidth(300);
			conteneur.setMinWidth(300);
			CornerRadii corn = new CornerRadii(10);
			conteneur.setBackground(new Background(new  BackgroundFill(Color.web("#fce4ec"),corn,null)));
				
			Label lab = new Label("You may have :");
			lab.setStyle("-fx-font-weight : bold;");
			
			conteneur.getChildren().add(lab);
			for ( Disease ds : diseases ) {
				Label lb = new Label(ds.getName());
				lb.setTextFill(Color.web("#ff1744"));
				conteneur.getChildren().add(lb);
				
				Label desc = new Label(ds.getDescription());
				desc.setMaxWidth(300);
				desc.setWrapText(true);
				conteneur.getChildren().add(desc);
			}
			
			
		
			Label lab1 = new Label("Take following measures :");
			lab1.setStyle("-fx-font-weight : bold;");
			
			conteneur.getChildren().add(lab1);
			
			for ( String st : precautions) {
				Label l = new Label("- "+st);
				l.setTextFill(Color.GREEN);
				l.setStyle("-fx-font-weight : bold;");
				conteneur.getChildren().add(l);
			}
			this.getChildren().addAll(img,conteneur);
			
		}else {
			this.setSpacing(10);
			Label message = new Label("Sorry i couldn't recognise this symptom please try again and try an other one");
			message.setWrapText(true);
			message.setPadding(new Insets(10));
			CornerRadii corn = new CornerRadii(10);
			message.setBackground(new Background(new  BackgroundFill(Color.web("#fce4ec"),corn,null)));
			message.setMaxWidth(200);
			message.setStyle("-fx-font-weight: bold;");
			this.getChildren().addAll(img,message);
		}
		}
	}

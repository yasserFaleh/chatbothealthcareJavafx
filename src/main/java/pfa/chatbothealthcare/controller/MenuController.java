package pfa.chatbothealthcare.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pfa.chatbothealthcare.model.BotMessage;
import pfa.chatbothealthcare.model.Disease;
import pfa.chatbothealthcare.model.ResultMessage;
import pfa.chatbothealthcare.model.SymptomsMessage;
import pfa.chatbothealthcare.model.UserMessage;

public class MenuController implements Initializable{

	
	@FXML 
	private Circle 				circleImg;
	@FXML 
	private ListView<HBox> 		conteneurMessages;
	@FXML 
	private TextField 			messageUser;
	@FXML 
	private Button 				sendButton;
	@FXML 
	private Button 				reload;
	
	private ObservableList<HBox>items;
	
	private boolean 			isSymptomGift;
	public PrintWriter 			writer = null;
	public BufferedInputStream 	reader = null;
	
	private boolean 			askingMode=true;
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			initCommunicationWithServer();
			initDesign();
			initButtonListeners();
			startListening();
			
		} catch (Exception e) {
		
		}
		
		
		
		
		
		
	}


	
	
	
	
	
	
	private void startListening() {
		Thread listener = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while ( true ) {
						String	recu = read();
						Platform.runLater(new Runnable() {
							  @Override 
						      public void run() {
						    	 if ( recu.charAt(0) == 's') {
						    		 addSymptomsMessage(recu.substring(1));
						    	   
						    	 }else if ( 'r' == recu.charAt(0) ) {
						    	   try {
						    		 end(recu.substring(1));
						    	 
						    	   }catch(Exception e) {
						    		   e.printStackTrace();
						    	   }
						    	 }	
						      }

							
						});
					}
				
				
				
				} catch (IOException e) {
					
				}
				
			}
		});
		listener.start();
		
	}








	private void initCommunicationWithServer() throws Exception {
		etablirConnection("localhost", 2021);
	}





	private void end(String recu) throws ParserConfigurationException, SAXException, IOException {
		//read the xml 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(recu)));
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		List<String> precautions = new ArrayList<>();
		List<Disease> diseases = new ArrayList<>();
		NodeList nList = document.getElementsByTagName("disease");
		for (int temp = 0; temp < nList.getLength(); temp++){
			 Node node = nList.item(temp);
			 if (node.getNodeType() == Node.ELEMENT_NODE){
				 Element eElement = (Element) node;
				 diseases.add(new Disease(eElement.getElementsByTagName("name").item(0).getTextContent() ,eElement.getElementsByTagName("description").item(0).getTextContent() ));
			 }
		}
		NodeList precaustion = document.getElementsByTagName("precaution");

		for (int temp = 0; temp < precaustion.getLength(); temp++){
			 Node node = precaustion.item(temp);
			 if (node.getNodeType() == Node.ELEMENT_NODE){
				 Element eElement = (Element) node;
				 precautions.add( eElement.getTextContent() );
			 }
		}
		
		// show result
		items.add(new ResultMessage(diseases, precautions));	
		
		
		
	}


	private void initButtonListeners() {
		sendButton.setOnMouseClicked( e->{
			if ( !messageUser.getText().equals("") ) 
				
				if( askingMode) {
					writer.println(messageUser.getText());
					addUserMessage(messageUser.getText());
	 				messageUser.setText("");
	    			askingMode = false;
	    		 }else {
	    			addUserMessage(messageUser.getText());
	    		 }
				
				
		});
		reload.setOnMouseClicked( e->{
			items.clear();
			addBotMessage("Hello, I am a healthcare bot. Please enter the symptom you are experiencing : ");
		});
		
		
	}


	private void addUserMessage(String text) {
		items.add(new UserMessage(text));
		
	}








	private void addBotMessage(String str) {
		items.add(new BotMessage(str));
		conteneurMessages.scrollTo(items.size() -1);
	}

	private void addSymptomsMessage(String str) {
		items.add(new SymptomsMessage(writer,str));
		conteneurMessages.scrollTo(items.size() -1);
	}

		




	/**
	 * Initialiser le design du menu
	 */
	private void initDesign() {
		// ajouter l'image de la conversation
		circleImg.setFill(new ImagePattern(new Image(getClass().getResource("/images/chatbot.png").toString(), false)	));
		items	= FXCollections.observableArrayList();
		conteneurMessages.setItems(items);
		conteneurMessages.setMaxHeight(Control.USE_PREF_SIZE);
		
		addBotMessage("Hello, I am a healthcare bot. I can give some precautions in the symptoms given.");
		
	}
	
	private Socket etablirConnection(String ip , int port) throws Exception {
		Socket socket= new Socket(ip, port);
		

		reader = new BufferedInputStream(socket.getInputStream());
		writer = new PrintWriter(socket.getOutputStream(), true);
		
		return socket;
	}

	

	private String read() throws IOException {
		String reponse = "";
		int stream;
		byte[] b = new byte[8192];
		stream = this.reader.read(b);
		reponse = new String(b, 0, stream);
		return reponse;

	}
	

}

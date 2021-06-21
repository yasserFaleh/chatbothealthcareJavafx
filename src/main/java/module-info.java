module pfa.chatbothealthcare {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	requires java.xml;

    opens pfa.chatbothealthcare to javafx.fxml;
    exports pfa.chatbothealthcare;
    exports pfa.chatbothealthcare.controller;
    opens pfa.chatbothealthcare.controller;
}

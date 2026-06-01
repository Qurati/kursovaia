module  ru.qurati.chatgptapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires javafx.swing;
    opens  ru.qurati.chatgptapp to javafx.fxml;
    opens  ru.qurati.chatgptapp.model to org.hibernate.orm.core;
    exports  ru.qurati.chatgptapp;
    exports  ru.qurati.chatgptapp.controller.client;
    opens  ru.qurati.chatgptapp.controller.client to javafx.fxml;
    exports  ru.qurati.chatgptapp.controller.querycategories;
    opens  ru.qurati.chatgptapp.controller.querycategories to javafx.fxml;
    exports  ru.qurati.chatgptapp.controller.request;
    opens  ru.qurati.chatgptapp.controller.request to javafx.fxml;
}

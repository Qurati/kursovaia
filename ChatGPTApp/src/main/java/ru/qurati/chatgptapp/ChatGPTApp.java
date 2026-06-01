package ru.qurati.chatgptapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatGPTApp extends Application {
    public static Stage primaryStage;
    public static Scene clients;
    public static Scene quearyCategories;
    public static Scene requests;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        clients = createScene("client-view.fxml");
        requests = createScene("request-view.fxml");
        quearyCategories = createScene("query-categories-view.fxml");
        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(675);
        primaryStage.setTitle("Пользователи");
        clients.getStylesheets().add("base-styles.css");
        quearyCategories.getStylesheets().add("base-styles.css");
        requests.getStylesheets().add("base-styles.css");
        primaryStage.setScene(clients);
        primaryStage.show();
    }

    private Scene createScene(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatGPTApp.class.getResource(name));
        return new Scene(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}
package ru.qurati.chatgptapp.controller.request;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.chatgptapp.model.Client;
import ru.qurati.chatgptapp.model.QueryCategories;
import ru.qurati.chatgptapp.model.Request;
import ru.qurati.chatgptapp.service.ClientService;
import ru.qurati.chatgptapp.service.QueryCategoriesService;
import ru.qurati.chatgptapp.service.RequestService;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AddEditRequestDialog implements Initializable {
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private TextField lengthField;
    @FXML
    private Button okButton;
    @FXML
    private Label errorLabel;

    private Stage dialogStage;
    private Request request;
    private ObservableList<String> categories;
    private ObservableList<String> users;
    private java.util.Map<String, QueryCategories> categoryMap;
    private java.util.Map<String, Client> userMap;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories = FXCollections.observableArrayList();
        users = FXCollections.observableArrayList();
        categoryMap = new java.util.HashMap<>();
        userMap = new java.util.HashMap<>();

        for (QueryCategories credit : new QueryCategoriesService().findAll()) {
            categories.add(credit.getName());
            categoryMap.put(credit.getName(), credit);
        }

        for (Client client : new ClientService().findAll()) {
            users.add(client.getLogin());
            userMap.put(client.getLogin(), client);
        }

        categoryComboBox.setItems(categories);
        userComboBox.setItems(users);
    }

    private void add() {
        try {
            String selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            String selectedUser = userComboBox.getSelectionModel().getSelectedItem();
            String lengthText = lengthField.getText();

            if (selectedCategory == null) {
                throw new IllegalArgumentException("Выберите категорию!");
            }
            if (selectedUser == null) {
                throw new IllegalArgumentException("Выберите пользователя!");
            }
            if (lengthText == null || lengthText.trim().isEmpty()) {
                throw new IllegalArgumentException("Введите длину запроса!");
            }

            int length;
            try {
                length = Integer.parseInt(lengthText);
                if (length <= 0) {
                    throw new IllegalArgumentException("Длина должна быть положительным числом!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Длина должна быть целым числом!");
            }

            Request newRequest = new Request();
            newRequest.setQueryCategory(categoryMap.get(selectedCategory));
            newRequest.setClient(userMap.get(selectedUser));
            newRequest.setLength(String.valueOf(length));
            newRequest.setTime(LocalDateTime.now());

            new RequestService().save(newRequest);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    private void edit() {
        try {
            String selectedCategory = categoryComboBox.getSelectionModel().getSelectedItem();
            String selectedUser = userComboBox.getSelectionModel().getSelectedItem();
            String lengthText = lengthField.getText();

            if (selectedCategory == null) {
                throw new IllegalArgumentException("Выберите категорию!");
            }
            if (selectedUser == null) {
                throw new IllegalArgumentException("Выберите пользователя!");
            }
            if (lengthText == null || lengthText.trim().isEmpty()) {
                throw new IllegalArgumentException("Введите длину запроса!");
            }

            int length;
            try {
                length = Integer.parseInt(lengthText);
                if (length <= 0) {
                    throw new IllegalArgumentException("Длина должна быть положительным числом!");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Длина должна быть целым числом!");
            }

            request.setQueryCategory(categoryMap.get(selectedCategory));
            request.setClient(userMap.get(selectedUser));
            request.setLength(String.valueOf(length));

            new RequestService().update(request);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction((www) -> add());
    }

    public void setEditDialogStage(Stage dialogStage, Request request) {
        this.request = request;
        this.dialogStage = dialogStage;

        if (request.getQueryCategory() != null) {
            categoryComboBox.getSelectionModel().select(request.getQueryCategory().getName());
        }

        if (request.getClient() != null) {
            userComboBox.getSelectionModel().select(request.getClient().getLogin());
        }

        lengthField.setText(String.valueOf(request.getLength()));
        okButton.setOnAction((www) -> edit());
    }
}
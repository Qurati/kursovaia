package ru.qurati.chatgptapp.controller.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.chatgptapp.model.Client;
import ru.qurati.chatgptapp.service.ClientService;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddEditClientDialog implements Initializable {
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField kindPropertyField;
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField dateRegistration;

    private Stage dialogStage;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void add() {
        try {
            Client client = new Client();
            client.setLogin(nameField.getText());
            client.setTarif(kindPropertyField.getText());
            client.setEmail(emailField.getText());
            client.setDateRegistration(LocalDateTime.now());
            new ClientService().save(client);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    void edit() {
        try {
            client.setLogin(nameField.getText());
            client.setTarif(kindPropertyField.getText());
            client.setEmail(emailField.getText());
            new ClientService().update(client);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dateRegistration.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        dateRegistration.setEditable(false);
        okButton.setOnAction((www) -> add());
    }

    public void setEditDialogStage(Stage dialogStage, Client client) {
        this.client = client;
        this.dialogStage = dialogStage;
        nameField.setText(client.getLogin());
        kindPropertyField.setText(client.getTarif());
        emailField.setText(client.getEmail());
        if (client.getDateRegistration() != null) {
            dateRegistration.setText(client.getDateRegistration().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        dateRegistration.setEditable(false);
        okButton.setOnAction((www) -> edit());
    }
}
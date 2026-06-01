package ru.qurati.chatgptapp.controller.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.qurati.chatgptapp.ChatGPTApp;
import ru.qurati.chatgptapp.model.Client;
import ru.qurati.chatgptapp.repository.ClientDao;
import ru.qurati.chatgptapp.service.ClientService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClientController {
    private List<Client> clients;
<<<<<<< HEAD
    private ObservableList<ClientTableItem> clientsObservable;

    @FXML
=======
    private ObservableList<ClientTableItem> creditsObservable;

    @FXML
    private Label welcomeText;
    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> kindPropertyColumn;
    @FXML
<<<<<<< HEAD
=======
    private TableColumn<?, ?> phoneColumn;
    @FXML
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
    private TableView<ClientTableItem> clientsTable;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> dateRegColumn;

    private final ClientDao clientDao = new ClientDao();

    @FXML
    void btnAddClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-client-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ChatGPTApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить пользователя");
            AddEditClientDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    private void updateList() {
        clients = new ClientService().findAll();
<<<<<<< HEAD
        clientsObservable = FXCollections.observableArrayList();
        for (Client client : clients) {
            clientsObservable.add(new ClientTableItem(client));
        }
        clientsTable.setItems(clientsObservable);
=======
        creditsObservable = FXCollections.observableArrayList();
        for (Client client : clients) {
            creditsObservable.add(new ClientTableItem(client));
        }
        clientsTable.setItems(creditsObservable);
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
    }

    @FXML
    void btnDeleteClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
<<<<<<< HEAD
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getLogin() + "\"?\n\n"+"Это приведет к удалению записей в таблице \"Запросы\"");
=======
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getLogin() + "\"?");
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new ClientService().delete(currentItem.getClient());
                clientsTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditClient(ActionEvent event) {
        ClientTableItem currentItem = clientsTable.getSelectionModel().getSelectedItem();
        int currentItemId = clientsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-client-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(ChatGPTApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать пользователя");
                AddEditClientDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getClient());
                dialogStage.showAndWait();
                updateList();
            } catch (IOException e) {
                System.out.println("Ошибка открытия окна: " + e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для редактирования");
            alert.showAndWait();
        }
    }

    @FXML
    void btnOffOnAction(ActionEvent event) {
        ChatGPTApp.primaryStage.close();
    }

    @FXML
    void btnUpdateClients(ActionEvent event) {
        updateList();
    }

    public void initialize() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        kindPropertyColumn.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        dateRegColumn.setCellValueFactory(new PropertyValueFactory<>("dateRegistration"));
        updateList();
    }

<<<<<<< HEAD
    public void btnRequestsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.requests);
    }

    public void btnQuearyCategoriesOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.quearyCategories);
    }

    public void btnClientsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }
=======
    public void btnCreditsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }

    public void btnKindCreditOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.kindCredits);
    }
    public void btnrRquestsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.requests);
    }
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
}
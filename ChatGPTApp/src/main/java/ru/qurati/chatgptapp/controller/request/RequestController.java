package ru.qurati.chatgptapp.controller.request;

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
import ru.qurati.chatgptapp.model.QueryCategories;
import ru.qurati.chatgptapp.model.Request;
import ru.qurati.chatgptapp.service.ClientService;
import ru.qurati.chatgptapp.service.QueryCategoriesService;
import ru.qurati.chatgptapp.service.RequestService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestController {
    private List<Request> requests;
    private ObservableList<RequestTableItem> requestsObservable;
    private Map<Integer, String> categoryMap;
    private Map<Integer, String> userMap;

    @FXML
    private TableView<RequestTableItem> requestsTable;
    @FXML
    private TableColumn<RequestTableItem, Integer> idColumn;
    @FXML
    private TableColumn<RequestTableItem, String> categoryColumn;
    @FXML
    private TableColumn<RequestTableItem, String> userColumn;
    @FXML
    private TableColumn<RequestTableItem, Integer> lengthColumn;
    @FXML
    private TableColumn<RequestTableItem, String> timeColumn;

    @FXML
    void btnAddRequest(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-request-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ChatGPTApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить запрос");
            AddEditRequestDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnEditRequest(ActionEvent event) {
        RequestTableItem currentItem = requestsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            try {
                FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-request-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(ChatGPTApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать запрос");
                AddEditRequestDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getRequest());
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
    void btnDeleteRequest(ActionEvent event) {
        RequestTableItem currentItem = requestsTable.getSelectionModel().getSelectedItem();
        if (currentItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить запрос \"" + currentItem.getQueryCategoriesName() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new RequestService().delete(currentItem.getRequest());
                requestsTable.getItems().remove(currentItem);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateRequests(ActionEvent event) {
        updateList();
    }

    @FXML
<<<<<<< HEAD
    void btnClientsOnAction(ActionEvent event) {
=======
    void btnCredits(ActionEvent event) {
        // Исправлено: было ChatGPTApp.clients, но, судя по контексту, должно быть requests или clients
        // Если нужно переключиться на clients:
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }

    @FXML
<<<<<<< HEAD
    void btnQuearyCategoriesOnAction(ActionEvent event) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.quearyCategories);
=======
    void btnClients(ActionEvent event) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }

    @FXML
    void btnKindCredit(ActionEvent event) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.kindCredits);
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
    }

    @FXML
    void btnOff(ActionEvent event) {
        ChatGPTApp.primaryStage.close();
    }

    private void updateList() {
        requests = new RequestService().findAll();
        categoryMap = new HashMap<>();
        userMap = new HashMap<>();

<<<<<<< HEAD
=======
        // Загрузка карты категорий
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
        for (QueryCategories credit : new QueryCategoriesService().findAll()) {
            categoryMap.put(credit.getQueryCategoriesId(), credit.getName());
        }

<<<<<<< HEAD
=======
        // Загрузка карты пользователей
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
        for (Client client : new ClientService().findAll()) {
            userMap.put(client.getUserId(), client.getLogin());
        }

        requestsObservable = FXCollections.observableArrayList();
        for (Request request : requests) {
<<<<<<< HEAD
            String categoryName = categoryMap.getOrDefault(request.getQueryCategory().getQueryCategoriesId(), "Неизвестно");
            String userLogin = userMap.getOrDefault(request.getClient().getUserId(), "Неизвестно");
=======
            String categoryName = categoryMap.getOrDefault(request.getQueryCategoriesId(), "Неизвестно");
            String userLogin = userMap.getOrDefault(request.getUserId(), "Неизвестно");
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
            requestsObservable.add(new RequestTableItem(request, categoryName, userLogin));
        }
        requestsTable.setItems(requestsObservable);
    }

    @FXML
    public void initialize() {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("queryCategoriesName"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("userLogin"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        updateList();
    }
}
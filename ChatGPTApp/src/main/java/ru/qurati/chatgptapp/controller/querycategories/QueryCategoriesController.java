package ru.qurati.chatgptapp.controller.querycategories;

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
import ru.qurati.chatgptapp.model.QueryCategories;
import ru.qurati.chatgptapp.service.QueryCategoriesService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class QueryCategoriesController {
    private List<QueryCategories> queryCategories;

    private ObservableList<QueryCategoriesTableItem> creditsObservable;
    @FXML
    private TableView<QueryCategoriesTableItem> QueryCategoriesTable;

    @FXML
    private Button btnAddKindCredit;

    @FXML
    private Button btnClients;

    @FXML
    private Button btnDeleteQueryCategories;

    @FXML
    private Button btnEditQueryCategories;

    @FXML
    private Button btnOff;

    @FXML
    private Button btnQuearyCategories;

    @FXML
    private Button btnRequests;

    @FXML
    private Button btnUpdateQueryCategories;

    @FXML
    private TableColumn<?, ?> conditionColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> rateColumn;
    @FXML
    void btnAddQueryCategories(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-query-categories-dialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(ChatGPTApp.primaryStage);
            dialogStage.setMinWidth(400);
            dialogStage.setScene(new Scene(loader.load()));
            dialogStage.setTitle("Добавить категорию запроса");
            AddEditQueryCategoriesDialog controller = loader.getController();
            controller.setAddDialogStage(dialogStage);
            dialogStage.showAndWait();
            updateList();
        } catch (IOException e) {
            System.out.println("Ошибка открытия окна: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteQueryCategories(ActionEvent event) {
        QueryCategoriesTableItem currentItem = QueryCategoriesTable.getSelectionModel().getSelectedItem();
        int currentItemId = QueryCategoriesTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?\n\n"+"Это приведет к удалению записей в таблице \"Запросы\"");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new QueryCategoriesService().delete(currentItem.getKindCredit());
                QueryCategoriesTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditQueryCategories(ActionEvent event) {
        QueryCategoriesTableItem currentItem = QueryCategoriesTable.getSelectionModel().getSelectedItem();
        int currentItemId = QueryCategoriesTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            try {
                FXMLLoader loader = new FXMLLoader(ChatGPTApp.class.getResource("add-edit-query-categories-dialog.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(ChatGPTApp.primaryStage);
                dialogStage.setMinWidth(400);
                dialogStage.setScene(new Scene(loader.load()));
                dialogStage.setTitle("Редактировать категорию запроса");
                AddEditQueryCategoriesDialog controller = loader.getController();
                controller.setEditDialogStage(dialogStage, currentItem.getKindCredit());
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
    void btnOff(ActionEvent event) {
        ChatGPTApp.primaryStage.close();
    }

    @FXML
    void btnUpdateQueryCategories(ActionEvent event) {
        updateList();
    }

    public void updateList() {
        queryCategories = new QueryCategoriesService().findAll();
        creditsObservable = FXCollections.observableArrayList();
        for (QueryCategories queryCategories : this.queryCategories) {
            creditsObservable.add(new QueryCategoriesTableItem(queryCategories));
        }
        QueryCategoriesTable.setItems(creditsObservable);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("limitValue"));
        updateList();
    }

    public void btnClientsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }
    public void btnRequestsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.requests);
    }
}
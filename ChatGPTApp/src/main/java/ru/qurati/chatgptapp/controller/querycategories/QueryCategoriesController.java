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

    @FXML
    private Button btnClients;
    @FXML
    private TableColumn<?, ?> conditionColumn;
    @FXML
    private Button btnCredits;
    @FXML
    private TableView<QueryCategoriesTableItem> kindCreditsTable;
    @FXML
    private Button btnKindCredit;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> rateColumn;
    @FXML
    private TableColumn<?, ?> termColumn;

    private ObservableList<QueryCategoriesTableItem> creditsObservable;

    @FXML
    void btnAddKindCredit(ActionEvent event) {
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
    void btnDeleteKindCredit(ActionEvent event) {
        QueryCategoriesTableItem currentItem = kindCreditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = kindCreditsTable.getSelectionModel().getSelectedIndex();
        if (currentItemId != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Удаление записи");
            alert.setContentText("Вы действительно хотите удалить \"" + currentItem.getName() + "\"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new QueryCategoriesService().delete(currentItem.getKindCredit());
                kindCreditsTable.getItems().remove(currentItemId);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Предупреждение");
            alert.setContentText("Выберите запись в таблице для удаления");
            alert.showAndWait();
        }
    }

    @FXML
    void btnEditKindCredit(ActionEvent event) {
        QueryCategoriesTableItem currentItem = kindCreditsTable.getSelectionModel().getSelectedItem();
        int currentItemId = kindCreditsTable.getSelectionModel().getSelectedIndex();
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
    void btnUpdateKindCredits(ActionEvent event) {
        updateList();
    }

    public void updateList() {
        queryCategories = new QueryCategoriesService().findAll();
        creditsObservable = FXCollections.observableArrayList();
        for (QueryCategories queryCategories : this.queryCategories) {
            creditsObservable.add(new QueryCategoriesTableItem(queryCategories));
        }
        kindCreditsTable.setItems(creditsObservable);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("limitValue"));
        updateList();
    }

    public void btnClients(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.clients);
    }
    public void btnRquestsOnAction(ActionEvent actionEvent) {
        ChatGPTApp.primaryStage.setScene(ChatGPTApp.requests);
    }
}
package ru.qurati.chatgptapp.controller.querycategories;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.qurati.chatgptapp.model.QueryCategories;
import ru.qurati.chatgptapp.service.QueryCategoriesService;

public class AddEditQueryCategoriesDialog {
    @FXML
    private TextField conditionField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Button okButton;
    @FXML
    private TextField rateField;
    @FXML
    private TextField termField;

    private Stage dialogStage;
    private QueryCategories queryCategories;

    void add() {
        try {
            QueryCategories queryCategories = new QueryCategories();
            queryCategories.setName(nameField.getText());
            queryCategories.setLevel(conditionField.getText());
            queryCategories.setLimitValue(rateField.getText());
            new QueryCategoriesService().save(queryCategories);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setAddDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        okButton.setOnAction((www) -> add());
    }

    void edit() {
        try {
            queryCategories.setName(nameField.getText());
            queryCategories.setLevel(conditionField.getText());
            queryCategories.setLimitValue(rateField.getText());
            new QueryCategoriesService().update(queryCategories);
            dialogStage.close();
        } catch (IllegalArgumentException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void setEditDialogStage(Stage dialogStage, QueryCategories queryCategories) {
        this.queryCategories = queryCategories;
        this.dialogStage = dialogStage;
        nameField.setText(queryCategories.getName());
        conditionField.setText(queryCategories.getLevel() != null ? queryCategories.getLevel().toString() : "");
        rateField.setText(queryCategories.getLimitValue() != null ? queryCategories.getLimitValue().toString() : "");
        okButton.setOnAction((www) -> edit());
    }
}
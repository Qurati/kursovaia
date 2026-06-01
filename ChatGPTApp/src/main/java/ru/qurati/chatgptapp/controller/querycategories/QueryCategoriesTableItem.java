package ru.qurati.chatgptapp.controller.querycategories;

import javafx.beans.property.SimpleStringProperty;
import ru.qurati.chatgptapp.model.QueryCategories;

public class QueryCategoriesTableItem {
    private SimpleStringProperty name;
    private SimpleStringProperty level;
    private SimpleStringProperty limitValue;
    private QueryCategories queryCategories;

    public QueryCategoriesTableItem(QueryCategories queryCategories) {
        this.name = new SimpleStringProperty(queryCategories.getName());
        this.level = new SimpleStringProperty(
                queryCategories.getLevel() != null ? queryCategories.getLevel().toString() : ""
        );
        this.limitValue = new SimpleStringProperty(
                queryCategories.getLimitValue() != null ? queryCategories.getLimitValue().toString() : ""
        );
        this.queryCategories = queryCategories;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLevel() {
        return level.get();
    }

    public SimpleStringProperty levelProperty() {
        return level;
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public String getLimitValue() {
        return limitValue.get();
    }

    public SimpleStringProperty limitValueProperty() {
        return limitValue;
    }

    public void setLimitValue(String limitValue) {
        this.limitValue.set(limitValue);
    }

    public QueryCategories getKindCredit() {
        return queryCategories;
    }

    public void setKindCredit(QueryCategories queryCategories) {
        this.queryCategories = queryCategories;
    }
}
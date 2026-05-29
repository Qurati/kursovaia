package ru.qurati.chatgptapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "query_categories")
public class QueryCategories {
    @Id
    @Column(name = "query_categories_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer queryCategoriesId;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "limit_value")
    private Integer limitValue;

    public Integer getQueryCategoriesId() {
        return queryCategoriesId;
    }

    public void setQueryCategoriesId(Integer queryCategoriesId) {
        this.queryCategoriesId = queryCategoriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Имя не должно быть пустым");
        }
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(String levelText) {
        if (!levelText.isEmpty() && levelText.matches("\\d+")) {
            int level = Integer.parseInt(levelText);
            if (level > 0) {
                this.level = level;
            } else {
                throw new IllegalArgumentException("Уровень должен быть положительным числом!");
            }
        } else {
            throw new IllegalArgumentException("Уровень должен быть целым положительным числом!");
        }
    }

    public Integer getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(String limitText) {
        if (!limitText.isEmpty()) {
            if (limitText.matches("\\d+")) {
                this.limitValue = Integer.parseInt(limitText);
            } else {
                throw new IllegalArgumentException("Лимит должен быть целым числом!");
            }
        } else {
            this.limitValue = null;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
package ru.qurati.chatgptapp.repository;

import ru.qurati.chatgptapp.model.QueryCategories;

public class QueryCategoriesDao extends BaseDao<QueryCategories> {
    public QueryCategoriesDao() {
        super(QueryCategories.class);
    }
}
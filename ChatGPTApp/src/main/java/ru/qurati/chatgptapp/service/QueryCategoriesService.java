package ru.qurati.chatgptapp.service;

import ru.qurati.chatgptapp.model.QueryCategories;
import ru.qurati.chatgptapp.repository.QueryCategoriesDao;

import java.util.List;

public class QueryCategoriesService {
    private QueryCategoriesDao queryCategoriesDAO = new QueryCategoriesDao();

    public QueryCategoriesService() {
    }

    public List<QueryCategories> findAll() {
        return queryCategoriesDAO.findAll();
    }

    public QueryCategories findOne(final long id) {
        return queryCategoriesDAO.findOne(id);
    }

    public void save(final QueryCategories entity) {
        if (entity == null)
            return;
        queryCategoriesDAO.save(entity);
    }

    public void update(final QueryCategories entity) {
        if (entity == null)
            return;
        queryCategoriesDAO.update(entity);
    }

    public void delete(final QueryCategories entity) {
        if (entity == null)
            return;
        queryCategoriesDAO.delete(entity);
    }

    public void deleteById(final Long id) {
        if (id == null)
            return;
        queryCategoriesDAO.deleteById(id);
    }
}
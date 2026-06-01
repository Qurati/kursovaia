package ru.qurati.chatgptapp.service;

import ru.qurati.chatgptapp.model.Request;
import ru.qurati.chatgptapp.repository.RequestDao;

import java.util.List;

public class RequestService {
    private RequestDao requestDao = new RequestDao();

    public RequestService() {
    }

    public List<Request> findAll() {
        return requestDao.findAll();
    }

    public Request findOne(final long id) {
        return requestDao.findOne(id);
    }

    public void save(final Request entity) {
        if (entity == null)
            return;
        requestDao.save(entity);
    }

    public void update(final Request entity) {
        if (entity == null)
            return;
        requestDao.update(entity);
    }

    public void delete(final Request entity) {
        if (entity == null)
            return;
        requestDao.delete(entity);
    }

    public void deleteById(final Long id) {
        if (id == null)
            return;
        requestDao.deleteById(id);
    }
}
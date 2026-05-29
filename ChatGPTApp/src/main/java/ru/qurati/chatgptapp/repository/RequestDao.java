package ru.qurati.chatgptapp.repository;

import ru.qurati.chatgptapp.model.Request;

public class RequestDao extends BaseDao<Request> {
    public RequestDao() {
        super(Request.class);
    }
}
package ru.qurati.chatgptapp.repository;

import ru.qurati.chatgptapp.model.Client;

public class ClientDao extends BaseDao<Client> {
    public ClientDao() {
        super(Client.class);
    }
}
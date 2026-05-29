package ru.qurati.chatgptapp.controller.client;

import javafx.beans.property.SimpleStringProperty;
import ru.qurati.chatgptapp.model.Client;
import java.time.format.DateTimeFormatter;

public class ClientTableItem {
    private SimpleStringProperty login;
    private SimpleStringProperty tarif;
    private SimpleStringProperty email;
    private SimpleStringProperty dateRegistration;
    private Client client;

    public ClientTableItem(Client client) {
        this.login = new SimpleStringProperty(client.getLogin());
        this.tarif = new SimpleStringProperty(client.getTarif());
        this.email = new SimpleStringProperty(client.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateRegistration = new SimpleStringProperty(
                client.getDateRegistration() != null ? client.getDateRegistration().format(formatter) : ""
        );
        this.client = client;
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getTarif() {
        return tarif.get();
    }

    public SimpleStringProperty tarifProperty() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif.set(tarif);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDateRegistration() {
        return dateRegistration.get();
    }

    public SimpleStringProperty dateRegistrationProperty() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration.set(dateRegistration);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
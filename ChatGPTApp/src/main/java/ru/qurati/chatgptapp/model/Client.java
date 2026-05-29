package ru.qurati.chatgptapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class Client {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "login")
    private String login;

    @Column(name = "tarif")
    private String tarif;

    @Column(name = "email")
    private String email;

    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (!login.isEmpty()) this.login = login;
        else throw new IllegalArgumentException("Пользователь не должен быть пустым!");
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        if (!tarif.isEmpty()) this.tarif = tarif;
        else throw new IllegalArgumentException("Тариф не должен быть пустым!");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.isEmpty()) this.email = email;
        else throw new IllegalArgumentException("Email не может быть пустым!");
    }

    public LocalDateTime getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(LocalDateTime dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    @Override
    public String toString() {
        return login;
    }
}
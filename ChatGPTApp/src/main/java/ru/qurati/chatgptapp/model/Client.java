package ru.qurati.chatgptapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b

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

<<<<<<< HEAD
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();

=======
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
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

<<<<<<< HEAD
    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

=======
>>>>>>> 7ddce83372b90a65811b77c110b413a2eb81751b
    @Override
    public String toString() {
        return login;
    }
}
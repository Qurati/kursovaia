package ru.qurati.chatgptapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @Column(name = "requests_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestsId;

    @ManyToOne
    @JoinColumn(name = "query_categories_id", nullable = true)
    private QueryCategories queryCategory;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @Column(name = "length")
    private Integer length;

    @Column(name = "time")
    private LocalDateTime time;

    public Integer getRequestsId() {
        return requestsId;
    }

    public void setRequestsId(Integer requestsId) {
        this.requestsId = requestsId;
    }

    public QueryCategories getQueryCategory() {
        return queryCategory;
    }

    public void setQueryCategory(QueryCategories queryCategory) {
        this.queryCategory = queryCategory;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(String lengthText) {
        if (!lengthText.isEmpty() && lengthText.matches("\\d+")) {
            this.length = Integer.parseInt(lengthText);
        } else {
            throw new IllegalArgumentException("Длина должна быть целым числом!");
        }
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Request #" + requestsId;
    }
}
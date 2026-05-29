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

    @Column(name = "query_categories_id")
    private Integer queryCategoriesId;

    @Column(name = "user_id")
    private Integer userId;

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

    public Integer getQueryCategoriesId() {
        return queryCategoriesId;
    }

    public void setQueryCategoriesId(Integer queryCategoriesId) {
        this.queryCategoriesId = queryCategoriesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
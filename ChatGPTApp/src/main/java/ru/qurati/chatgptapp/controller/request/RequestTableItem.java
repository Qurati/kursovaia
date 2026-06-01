package ru.qurati.chatgptapp.controller.request;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import ru.qurati.chatgptapp.model.Request;
import java.time.format.DateTimeFormatter;

public class RequestTableItem {
    private final SimpleIntegerProperty requestsId;
    private final SimpleStringProperty queryCategoriesName;
    private final SimpleStringProperty userLogin;
    private final SimpleIntegerProperty length;
    private final SimpleStringProperty time;
    private final Request request;

    public RequestTableItem(Request request, String queryCategoriesName, String userLogin) {
        this.requestsId = new SimpleIntegerProperty(request.getRequestsId());
        this.queryCategoriesName = new SimpleStringProperty(queryCategoriesName);
        this.userLogin = new SimpleStringProperty(userLogin);
        this.length = new SimpleIntegerProperty(request.getLength());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.time = new SimpleStringProperty(request.getTime() != null ? request.getTime().format(formatter) : "");
        this.request = request;
    }

    public int getRequestsId() {
        return requestsId.get();
    }

    public SimpleIntegerProperty requestsIdProperty() {
        return requestsId;
    }

    public String getQueryCategoriesName() {
        return queryCategoriesName.get();
    }

    public SimpleStringProperty queryCategoriesNameProperty() {
        return queryCategoriesName;
    }

    public String getUserLogin() {
        return userLogin.get();
    }

    public SimpleStringProperty userLoginProperty() {
        return userLogin;
    }

    public int getLength() {
        return length.get();
    }

    public SimpleIntegerProperty lengthProperty() {
        return length;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public Request getRequest() {
        return request;
    }
}
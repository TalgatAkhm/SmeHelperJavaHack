package com.mipt.smehelper;

import com.mipt.smehelper.models.Notification;
import com.mipt.smehelper.models.User;

import java.util.List;

public class Data {

    private static final Data INSTANCE = new Data();

    // Logged in user
    private User user;
    // Workers for current user, may be null
    private List<User> workers;

    private List<Notification> notifications;

    private Data(){}

    public static Data getInstance(){
        return INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}

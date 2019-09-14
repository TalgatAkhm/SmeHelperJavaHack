package com.mipt.smehelper.EBMessages;

import com.mipt.smehelper.models.User;

import java.util.List;

public class UsersFetchedMessage {
    private List<User> users;

    public UsersFetchedMessage(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}

package com.example.CompuCom2.repository.procedures;

import com.example.CompuCom2.entity.User;

import java.util.List;

public interface Procedures {
    List<String> userEmails();
    List<User> getUsers();
    User getUser(Integer i);
}

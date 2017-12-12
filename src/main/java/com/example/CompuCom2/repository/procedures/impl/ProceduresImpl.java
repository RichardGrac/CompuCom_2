package com.example.CompuCom2.repository.procedures.impl;

import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.repository.procedures.Procedures;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProceduresImpl implements Procedures {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> userEmails() {
        List<String> result = new ArrayList<>();
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("getUserEmails");
        storedProcedureQuery.getResultList().forEach(email -> result.add((String) email));
        return result;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("getUsers", User.class);
        storedProcedureQuery.getResultList().forEach(user -> users.add((User) user));
        return users;
    }

    @Override
    public User getUser(Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("getUser", User.class)
                .registerStoredProcedureParameter("p_id", Integer.class, ParameterMode.IN)
                .setParameter("p_id", id);
        return (User) storedProcedureQuery.getSingleResult();
    }
}

package com.example.CompuCom2.repository.procedures.impl;

import com.example.CompuCom2.entity.User;
import com.example.CompuCom2.model.UserAddressModel;
import com.example.CompuCom2.model.UserModel;
import com.example.CompuCom2.repository.procedures.Procedures;
import org.apache.catalina.Store;
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

    @Override
    public void addUser(UserModel userModel, UserAddressModel userAddressModel) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("addUser")
                .registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_city", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_colony", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_country", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_number", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_referece", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_state", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_street", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_zip", String.class, ParameterMode.IN)
                .setParameter("p_email", userModel.getEmail())
                .setParameter("p_password", userModel.getPassword())
                .setParameter("p_username", userModel.getUsername())
                .setParameter("p_city", userAddressModel.getCity())
                .setParameter("p_colony", userAddressModel.getColony())
                .setParameter("p_country", userAddressModel.getCountry())
                .setParameter("p_number", userAddressModel.getNumber())
                .setParameter("p_referece", userAddressModel.getReference())
                .setParameter("p_state", userAddressModel.getState())
                .setParameter("p_street", userAddressModel.getStreet())
                .setParameter("p_zip", userAddressModel.getZip());
    }
}

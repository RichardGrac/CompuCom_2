package com.example.CompuCom2.repository;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface Estadisticas {

    List<String> monthWithMorePurchases() throws DataAccessException;
}

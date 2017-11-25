package com.example.CompuCom2.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EstadisticasImpl implements Estadisticas{

    private EntityManager entityManager;

    private EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<String> monthWithMorePurchases() throws DataAccessException{
        Query query = getEntityManager().createQuery("SELECT MONTHNAME(b.date) FROM Bill AS b GROUP BY MONTH(b.date)");
        return (List<String>) query.getResultList();
    }
}

package com.enjoythecode.bitcoininvestmentsimulator.dao;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class InvestmentDao implements IInvestmentDao {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Override
    public Investment save(Investment investment) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(investment);
        tx.commit();
        entityManager.close();
        return investment;
    }

    @Override
    public Investment get(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        Investment investment = entityManager.find(Investment.class, id);
        entityManager.close();
        return investment;
    }

}

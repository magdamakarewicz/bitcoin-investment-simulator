package com.enjoythecode.bitcoininvestmentsimulator.dao;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

/**
 * Data Access Object for InvestmentDao entities.
 * <p>
 * Implements the {@link IInvestmentDao} interface.
 */
@Repository
public class InvestmentDao implements IInvestmentDao {

    /**
     * The {@link EntityManagerFactory} used to create {@link EntityManager} instances.
     * Injected via {@link PersistenceUnit} annotation.
     */
    @PersistenceUnit
    private EntityManagerFactory factory;

    /**
     * Saves the given {@link Investment} to the database.
     * Creates an {@link EntityManager}, begins a transaction, persists the entity, commits the transaction,
     * and closes the {@link EntityManager}.
     *
     * @param investment the {@link Investment} to save.
     * @return the saved {@link Investment} instance.
     */
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

    /**
     * Retrieves the {@link Investment} entity with the specified ID from the database.
     * Creates an {@link EntityManager}, finds the entity by its ID, and then closes the {@link EntityManager}.
     *
     * @param id The ID of the {@link Investment} entity to retrieve.
     * @return The retrieved {@link Investment} entity, or null if no entity with the given ID exists.
     */
    @Override
    public Investment get(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        Investment investment = entityManager.find(Investment.class, id);
        entityManager.close();
        return investment;
    }

}

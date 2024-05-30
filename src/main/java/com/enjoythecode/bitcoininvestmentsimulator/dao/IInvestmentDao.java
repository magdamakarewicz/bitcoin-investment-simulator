package com.enjoythecode.bitcoininvestmentsimulator.dao;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;

/**
 * Public interface IInvestmentDao that defines operations related to saving Bitcoin investments.
 */
public interface IInvestmentDao {

    /**
     * Saves a Bitcoin investment.
     *
     * @param investment The Investment object to be saved.
     * @return The saved Investment object.
     */
    Investment save(Investment investment);

    /**
     * Retrieves a Bitcoin investment by its ID.
     *
     * @param id The ID of the Investment to be retrieved.
     * @return The retrieved Investment object, or null if no Investment with the given ID exists.
     */
    Investment get(Long id);

}

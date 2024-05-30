package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;

/**
 * Provides method to save Investments.
 */
public interface IInvestmentService {

    /**
     * Saves an Investment to the database.
     *
     * @param investment The Investment event to save.
     * @return The saved Investment instance.
     */
    Investment saveInvestmentEvent(Investment investment);

}
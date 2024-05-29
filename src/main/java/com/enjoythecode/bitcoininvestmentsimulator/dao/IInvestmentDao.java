package com.enjoythecode.bitcoininvestmentsimulator.dao;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;

public interface IInvestmentDao {

    Investment save(Investment investment);

    Investment get(Long id);

}

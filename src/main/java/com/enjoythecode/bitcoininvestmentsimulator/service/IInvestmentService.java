package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;

public interface IInvestmentService {

    Investment saveInvestmentEvent(Investment investment);

}
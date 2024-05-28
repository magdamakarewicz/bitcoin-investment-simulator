package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

public interface IExchangeRateService {

    BigDecimal getRate(String fromCurrency, String toCurrency) throws InvalidInputDataException;

}

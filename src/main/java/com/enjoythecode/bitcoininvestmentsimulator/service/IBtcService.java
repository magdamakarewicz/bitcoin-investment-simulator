package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

public interface IBtcService {

    BigDecimal getBtcCalculationPrice(String date, String currency) throws InvalidInputDataException;

    BigDecimal btcValueToday(String calculationDate, String currency, BigDecimal amount) throws InvalidInputDataException;

}

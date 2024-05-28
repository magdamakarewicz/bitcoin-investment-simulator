package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

public interface IBtcPriceService {

    BigDecimal getPastPrice(String date, String currency) throws InvalidInputDataException;

    BigDecimal getCurrentPrice(String currency) throws InvalidInputDataException;

}
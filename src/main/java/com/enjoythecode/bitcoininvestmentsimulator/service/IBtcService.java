package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

/**
 * Interface for retrieving information about Bitcoin prices and sales.
 */
public interface IBtcService {

    /**
     * Retrieves the Bitcoin calculation (potential purchase) price for a specific date and currency.
     *
     * @param date     the date of the investment (Bitcoin purchase) in format "dd-MM-yyyy".
     * @param currency the currency in which Bitcoin was purchased ("USD" or "PLN").
     * @return the purchase price of Bitcoin on the specified date and in the specified currency.
     * @throws InvalidInputDataException if the provided input data is invalid or cannot be used for calculations.
     */
    BigDecimal getBtcCalculationPrice(String date, String currency) throws InvalidInputDataException;

    /**
     * Calculates the potential value of Bitcoin (bought in the past by given amount of money) if sold today.
     *
     * @param calculationDate the date of the investment (Bitcoin purchase) in format "dd-MM-yyyy".
     * @param currency     the currency in which Bitcoin was purchased ("USD" or "PLN").
     * @param amount       the amount of money invested.
     * @return the potential sale value of the given amount of Bitcoin in the specified currency.
     * @throws InvalidInputDataException if the provided input data is invalid or cannot be used for calculations.
     */
    BigDecimal btcSaleEvent(String calculationDate, String currency, BigDecimal amount) throws InvalidInputDataException;

}

package com.enjoythecode.bitcoininvestmentsimulator.service;

/**
 * Interface for building URLs to Bitcoin rates APIs.
 */
public interface IBtcUrlStringBuilder {

    /**
     * Builds a URL string for retrieving the Bitcoin purchase price for a given date and currency mark.
     *
     * @param investmentDate the date of the desired purchase rate in the format "dd-MM-yyyy".
     * @param currency the currency code for the currency of the desired purchase rate.
     * @return a URL string for retrieving the Bitcoin purchase rate.
     */
    String buildBtcUrl(String investmentDate, String currency);

}

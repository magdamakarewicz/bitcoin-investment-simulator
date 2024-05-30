package com.enjoythecode.bitcoininvestmentsimulator.config;

/**
 * The AppConfig interface contains constants that store URLs and API keys required for the application.
 * <p>
 * Two URLs that are required:
 * <p>
 * - Bitcoin exchange API (FastForex), which is required to get current Bitcoin rate
 * <p>
 * - currency exchange API (CoinDesk), which is required to get PLN/USD rate when user picks PLN currency for Bitcoin
 * purchase, as in Bitcoin exchange API there is no Bitcoin rate for PLN currency available
 */
public interface AppConfig {

    /**
     * The base URL for the currency exchange API.
     */
    String EXCHANGE_API_PAGE = "https://api.fastforex.io";
    /**
     * The endpoint for the currency exchange API.
     */
    String EXCHANGE_ENDPOINT = "/fetch-all?from=";
    /**
     * The private API key required for accessing the currency exchange API.
     */
    String EXCHANGE_PRIVATE_API_KEY = "&api_key=fb7a0538a7-a093871df4-se6wmx";

    /**
     * The base URL for the Bitcoin exchange API.
     */
    String BITCOIN_API_PAGE = "https://api.coindesk.com";
    /**
     * The endpoint for the Bitcoin API.
     */
    String BITCOIN_ENDPOINT = "/v1/bpi/historical/close.json";

}

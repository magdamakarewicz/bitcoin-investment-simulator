package com.enjoythecode.bitcoininvestmentsimulator.config;

public interface AppConfig {

    String EXCHANGE_API_PAGE = "https://api.fastforex.io";

    String EXCHANGE_ENDPOINT = "/fetch-all?from=";

    String EXCHANGE_PRIVATE_API_KEY = "&api_key=fb7a0538a7-a093871df4-se6wmx";


    String BITCOIN_API_PAGE = "https://api.coindesk.com";

    String BITCOIN_ENDPOINT = "/v1/bpi/historical/close.json";

}

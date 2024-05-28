package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import org.springframework.stereotype.Service;

import static com.enjoythecode.bitcoininvestmentsimulator.config.AppConfig.*;

@Service
public class FastForexUrlStringBuilder implements IExchangeUrlStringBuilder {

    @Override
    public String buildExchangeUrl(String fromCurrency) {
        return EXCHANGE_API_PAGE + EXCHANGE_ENDPOINT + fromCurrency + EXCHANGE_PRIVATE_API_KEY;
    }

}

package com.enjoythecode.bitcoininvestmentsimulator.service;

import org.springframework.stereotype.Service;

import static com.enjoythecode.bitcoininvestmentsimulator.config.AppConfig.*;

@Service
public class CoinDeskUrlStringBuilder implements IBtcUrlStringBuilder {

    @Override
    public String buildBtcUrl(String investmentDate, String currency) {
        return BITCOIN_API_PAGE + BITCOIN_ENDPOINT + "?start=" + investmentDate + "&end=" + investmentDate +
                "&currency=" + currency;
    }

}

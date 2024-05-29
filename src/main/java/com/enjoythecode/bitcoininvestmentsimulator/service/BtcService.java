package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Service
public class BtcService implements IBtcService {

    private final IBtcPriceService btcPriceService;

    @Override
    public BigDecimal getBtcCalculationPrice(String date, String currency) throws InvalidInputDataException {
        return btcPriceService.getPastPrice(date, currency);
    }

    @Override
    public BigDecimal btcValueToday(String calculationDate, String currency, BigDecimal amount) throws InvalidInputDataException {
        if (amount.doubleValue() <= 0)
            throw new InvalidInputDataException("The value should be greater than zero");

        BigDecimal btcCalculationPrice = getBtcCalculationPrice(calculationDate, currency);
        BigDecimal btcAmount = amount.divide(btcCalculationPrice, 4, RoundingMode.HALF_UP);
        BigDecimal btcSaleRate = btcPriceService.getCurrentPrice(currency);
        BigDecimal valueToday = btcSaleRate.multiply(btcAmount);

        return valueToday.setScale(2, RoundingMode.HALF_UP);
    }

}
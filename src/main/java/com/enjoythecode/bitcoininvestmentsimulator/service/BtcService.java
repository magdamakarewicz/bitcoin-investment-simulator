package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Service
public class BtcService implements IBtcService {

    private final IBtcPriceService btcPriceService;
    private final InvestmentService investmentService;

    @Override
    public BigDecimal getBtcCalculationPrice(String date, String currency) throws InvalidInputDataException {
        return btcPriceService.getPastPrice(date, currency);
    }

    @Override
    public BigDecimal btcSaleEvent(String calculationDate, String currency, BigDecimal investmentAmount)
            throws InvalidInputDataException {
        if (investmentAmount.doubleValue() <= 0)
            throw new InvalidInputDataException("The value should be greater than zero");

        BigDecimal btcCalculationPrice = getBtcCalculationPrice(calculationDate, currency);
        BigDecimal btcAmount = investmentAmount.divide(btcCalculationPrice, 4, RoundingMode.HALF_UP);
        BigDecimal btcPriceToday = btcPriceService.getCurrentPrice(currency);
        BigDecimal valueToday = btcPriceToday.multiply(btcAmount);

        BigDecimal profit = valueToday.subtract(investmentAmount);
        double profitPercentage = profit.divide(investmentAmount, 2, RoundingMode.HALF_UP).doubleValue() * 100;

        Investment investment = new Investment(
                LocalDate.now(),
                LocalDate.parse(calculationDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                investmentAmount,
                currency,
                btcAmount,
                btcCalculationPrice,
                btcPriceToday,
                profit,
                profitPercentage
        );

        investmentService.saveInvestmentEvent(investment);

        return valueToday.setScale(2, RoundingMode.HALF_UP);
    }

}
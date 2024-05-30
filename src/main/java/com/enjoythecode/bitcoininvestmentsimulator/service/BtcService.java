package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;

/**
 * The BtcService class implements the {@link IBtcService} interface and provides methods for retrieving Bitcoin
 * purchase rate and calculating the sale amount for a given Bitcoin purchase date, currency, and invested money amount.
 * It also saves the Bitcoin {@link Investment} event to the database asynchronously using the {@link Executor}.
 */
@AllArgsConstructor
@Service
public class BtcService implements IBtcService {

    private final IBtcPriceService btcPriceService;
    private final InvestmentService investmentService;
    private final Executor executor;

    /**
     * Retrieves the historical Bitcoin price for the specified date (investment day) and currency.
     *
     * @param date     The date of the Bitcoin purchase.
     * @param currency The currency in which the purchase was made.
     * @return The Bitcoin purchase price for the specified date and currency.
     * @throws InvalidInputDataException If the input data is invalid.
     */
    @Override
    public BigDecimal getBtcCalculationPrice(String date, String currency) throws InvalidInputDataException {
        return btcPriceService.getPastPrice(date, currency);
    }

    /**
     * Calculates the sale amount for the specified Bitcoin purchase date, currency, and amount, and saves the
     * sale event to the database asynchronously using the Executor.
     *
     * @param calculationDate       The date of the Bitcoin purchase.
     * @param currency   The currency in which the purchase was made.
     * @param investmentAmount The amount of money of the provided currency used to purchase Bitcoin.
     * @return The sale amount in the specified currency.
     * @throws InvalidInputDataException If the input data is invalid.
     */
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

        executor.execute(() -> investmentService.saveInvestmentEvent(investment));

        return valueToday.setScale(2, RoundingMode.HALF_UP);
    }

}
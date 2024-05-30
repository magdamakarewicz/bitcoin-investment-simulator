package com.enjoythecode.bitcoininvestmentsimulator.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * An entity class representing an potential event of selling Bitcoins bought in the past. It contains information
 * about the purchase date, purchase amount and price, calculation date, btc amount and price, profit, percentage profit.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "bitcoin_investments")
public class Investment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier of the Bitcoin investment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The date of the Bitcoin investment calculation (potential sale event).
     */
    private LocalDate calculationDate;
    /**
     * The date of the investment (Bitcoin purchase).
     */
    private LocalDate investmentDate;
    /**
     * The amount of invested money.
     */
    private BigDecimal investmentAmount;
    /**
     * The currency used to purchase Bitcoin.
     */
    private String currency;
    /**
     * The amount of Bitcoin purchased.
     */
    @Column(precision = 19, scale = 4)
    private BigDecimal btcAmount;
    /**
     * The price at which Bitcoin was purchased.
     */
    private BigDecimal btcPriceOnInvestmentDate;
    /**
     * The price at which Bitcoin was sold.
     */
    private BigDecimal btcPriceToday;
    /**
     * The profit made in the currency used to purchase Bitcoin.
     */
    private BigDecimal profitAmount;
    /**
     * The percentage profit of investment.
     */
    @Column(precision = 19, scale = 2)
    private double profitPercentage;

    /**
     * Constructor for Investment.
     *
     * @param calculationDate           The date of the Bitcoin investment calculation (potential sale event).
     * @param investmentDate            The date of the investment (Bitcoin purchase).
     * @param investmentAmount          The amount of invested money.
     * @param currency                  The currency used to purchase Bitcoin.
     * @param btcAmount                 The amount of Bitcoin purchased.
     * @param btcPriceOnInvestmentDate  The rate at which Bitcoin was purchased.
     * @param btcPriceToday             The price at which Bitcoin was sold.
     * @param profitAmount              The profit made in the currency used to purchase Bitcoin.
     * @param profitPercentage          The percentage profit of investment.
     */
    public Investment(LocalDate calculationDate, LocalDate investmentDate, BigDecimal investmentAmount, String currency,
                      BigDecimal btcAmount, BigDecimal btcPriceOnInvestmentDate, BigDecimal btcPriceToday,
                      BigDecimal profitAmount, double profitPercentage) {
        this.calculationDate = calculationDate;
        this.investmentDate = investmentDate;
        this.investmentAmount = investmentAmount;
        this.currency = currency;
        this.btcAmount = btcAmount;
        this.btcPriceOnInvestmentDate = btcPriceOnInvestmentDate;
        this.btcPriceToday = btcPriceToday;
        this.profitAmount = profitAmount;
        this.profitPercentage = profitPercentage;
    }

}



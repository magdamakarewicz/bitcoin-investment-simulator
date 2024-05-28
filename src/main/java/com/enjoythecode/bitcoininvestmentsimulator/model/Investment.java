package com.enjoythecode.bitcoininvestmentsimulator.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate calculationDate;

    private LocalDate investmentDate;

    private BigDecimal investmentAmount;

    private String currency;

    @Column(precision = 19, scale = 4)
    private BigDecimal btcAmount;

    private BigDecimal btcPriceOnInvestmentDate;

    private BigDecimal btcPriceToday;

    private BigDecimal profitAmount;

    @Column(precision = 19, scale = 2)
    private BigDecimal profitPercentage;

    public Investment(LocalDate calculationDate, LocalDate investmentDate, BigDecimal investmentAmount, String currency,
                      BigDecimal btcAmount, BigDecimal btcPriceOnInvestmentDate, BigDecimal btcPriceToday,
                      BigDecimal profitAmount, BigDecimal profitPercentage) {
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



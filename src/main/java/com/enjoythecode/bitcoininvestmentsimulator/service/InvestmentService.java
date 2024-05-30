package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.dao.IInvestmentDao;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the {@link IInvestmentService} interface responsible for managing Investments
 * using the provided {@link IInvestmentDao} data access object.
 */
@AllArgsConstructor
@Service
public class InvestmentService implements IInvestmentService {

    private IInvestmentDao investmentDao;

    /**
     * Saves the given {@link Investment} object to the data access object after checking that its ID is null.
     *
     * @param investment the Investment to save.
     * @return the saved Investment object.
     * @throws RuntimeException if the ID of the given Investment object is null.
     */
    @Override
    public Investment saveInvestmentEvent(Investment investment) {
        investment = Optional.ofNullable(investment)
                .filter(x -> Objects.isNull(x.getId()))
                .orElseThrow(() -> new RuntimeException("Bad entity"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return investmentDao.save(investment);
    }

}
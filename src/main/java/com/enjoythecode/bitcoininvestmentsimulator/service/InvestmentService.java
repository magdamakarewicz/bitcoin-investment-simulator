package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.dao.IInvestmentDao;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InvestmentService implements IInvestmentService {

    private IInvestmentDao investmentDao;

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
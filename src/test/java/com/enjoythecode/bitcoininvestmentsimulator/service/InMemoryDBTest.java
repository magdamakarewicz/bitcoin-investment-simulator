package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.config.JpaConfig;
import com.enjoythecode.bitcoininvestmentsimulator.dao.InvestmentDao;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JpaConfig.class, InvestmentDao.class})
@ActiveProfiles("test")
public class InMemoryDBTest {

    @Autowired
    private InvestmentDao investmentDao;

    @Test
    public void shouldReturnInvestmentWithId1FromDbSameAsSavedOne() {
        //check if a record with id 1 does not exist
        assertNull(investmentDao.get(1L));

        //given
        Investment investment = new Investment(
                LocalDate.of(2024, 5, 29),
                LocalDate.of(2015, 8, 31),
                BigDecimal.valueOf(1000),
                "USD",
                BigDecimal.valueOf(4.4018),
                BigDecimal.valueOf(227.1808),
                BigDecimal.valueOf(68098.9496),
                BigDecimal.valueOf(14470.7051),
                1447.07
        );

        //when
        investmentDao.save(investment);
        Investment investmentFromDB = investmentDao.get(1L);

        //then
        assertNotNull(investmentFromDB);
        assertEquals(LocalDate.of(2024, 5, 29), investmentFromDB.getCalculationDate());
        assertEquals(LocalDate.of(2015, 8, 31), investmentFromDB.getInvestmentDate());
        assertTrue(BigDecimal.valueOf(1000).compareTo(investmentFromDB.getInvestmentAmount()) == 0);
        assertEquals("USD", investmentFromDB.getCurrency());
        assertEquals(BigDecimal.valueOf(4.4018), investmentFromDB.getBtcAmount());
        assertEquals(BigDecimal.valueOf(227.18), investmentFromDB.getBtcPriceOnInvestmentDate());
        assertEquals(BigDecimal.valueOf(68098.95), investmentFromDB.getBtcPriceToday());
        assertEquals(BigDecimal.valueOf(14470.71), investmentFromDB.getProfitAmount());
        assertEquals(1447.07, investmentFromDB.getProfitPercentage());
    }

}

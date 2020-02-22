package com.antz.financial.application.impl;

import com.antz.financial.application.IFundCrawlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FundCrawlerServiceImplTest {

    @Autowired
    private IFundCrawlerService fundCrawlerService;

    @Test
    public void fundCrawlerTest() {
        fundCrawlerService.fundCrawler();
    }

}
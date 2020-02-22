package com.antz.financial.application.impl;

import com.antz.financial.application.IFundCrawlerService;
import com.antz.financial.crawler.TTFundCrawler;
import com.antz.financial.domain.Fund;
import com.antz.financial.infra.repository.FundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 20:48
 **/
@Service
public class FundCrawlerServiceImpl implements IFundCrawlerService {

    @Autowired
    private TTFundCrawler ttFundCrawler;

    @Autowired
    private FundRepository fundRepository;

    @Override
    public void fundCrawler() {
        List<Fund> funds = ttFundCrawler.crawlerFundAndParse();
        fundRepository.saveAll(funds);
    }
}

package com.antz.financial.application.impl;

import com.antz.financial.application.IFundCrawlerService;
import com.antz.financial.crawler.TTFundCrawler;
import com.antz.financial.domain.Fund;
import com.antz.financial.infra.repository.FundRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 20:48
 **/
@Slf4j
@Service
public class FundCrawlerServiceImpl implements IFundCrawlerService {

    public static final int ZERO = 0;
    @Autowired
    private TTFundCrawler ttFundCrawler;

    @Autowired
    private FundRepository fundRepository;

    @Override
    public void fundCrawler() {
        List<Fund> funds = ttFundCrawler.crawlerFundAndParse();
        fundRepository.saveAll(funds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long fundDetailCrawler(String fundCode, Integer page, Integer pageSize, long crawlerTimeStamp) {
        Page<Fund> fundPage = fundRepository.fundByPageOf(
                Fund.builder()
                        .code(fundCode)
                        .build(),
                PageRequest.of(page, pageSize));
        if (!fundPage.isEmpty()) {
            List<Fund> saveFunds = Lists.newArrayList();
            fundPage.getContent().forEach(fund -> {
                Optional<Fund> crawel_fund = wrapperCrawelFundDetail(fund, crawlerTimeStamp);
                if (crawel_fund.isPresent()) {
                    saveFunds.add(crawel_fund.get());
                }
            });
            return fundRepository.saveAll(saveFunds);
        }
        return ZERO;
    }

    private Optional<Fund> wrapperCrawelFundDetail(Fund fund, long crawlerTimeStamp) {
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return crawelFundDetail(fund, crawlerTimeStamp);
        } finally {
            stopWatch.stop();
            log.info("Fund Code :{}，execute crawler cost time :{}", fund.getCode(), stopWatch.getTotalTimeMillis());
        }
    }

    private Optional<Fund> crawelFundDetail(Fund fund, long crawlerTimeStamp) {
        Optional<Fund> crawel_fund = ttFundCrawler.crawlerFundDetailAndParse(fund.getCode(), crawlerTimeStamp);

        if (crawel_fund.isPresent()) {
            crawel_fund.get().setVersion(fund.getVersion());
            crawel_fund.get().setCreatedDate(fund.getCreatedDate());
            crawel_fund.get().setLastModifiedDate(fund.getLastModifiedDate());
            crawel_fund.get().setType(fund.getType());
        }
        return crawel_fund;
    }
}

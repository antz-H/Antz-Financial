package com.antz.financial.application;

/**
 * @author antz-H
 * @description
 * @date 2020/2/22 20:47
 **/
public interface IFundCrawlerService {

    void fundCrawler();

    long fundDetailCrawler(String fundCode, Integer page, Integer pageSize, long crawlerTimeStamp);

}

package com.antz.financial.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.antz.financial.domain.Fund;
import com.antz.financial.domain.FundManager;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 22:28
 **/
@Slf4j
@Service
public class TTFundCrawler {

    @Autowired
    private RestTemplate restTemplate;

    public static final String CRAWLER_TT_FUND_DETAIL_URL = "http://fund.eastmoney.com/pingzhongdata/{0}.js?v={1}";

    public static final String CRAWLER_TT_FUND_URL = "http://fund.eastmoney.com/js/fundcode_search.js";

    public Optional<Fund> crawlerFundDetailAndParse(String crawlerFundCode, long crawlerTimeStamp) {
        String crawler_fund_detail = "";
        String formatURL = null;
        try {
            formatURL = StringHelper.StringFormat(CRAWLER_TT_FUND_DETAIL_URL, crawlerFundCode, String.valueOf(crawlerTimeStamp));
            crawler_fund_detail = restTemplate.getForObject(formatURL, String.class);
            return Optional.ofNullable(parseFundDetail(crawler_fund_detail));
        } catch (Exception e) {
            log.error("Fund Code :{} crawel url：{}, failed : {}", formatURL, crawlerFundCode, e);
        }
        return Optional.ofNullable(null);
    }

    public List<Fund> crawlerFundAndParse() {
        String crawler_fund = restTemplate.getForObject(CRAWLER_TT_FUND_URL, String.class);
        return parseFund(crawler_fund);
    }

    public List<Fund> parseFund(String originalStr) {
        List<Fund> funds = Lists.newArrayList();
        Optional<String> targetChar = StringHelper.getTargetChar(TT_FUND_KEY_WORLD.R, TT_FUND_KEY_WORLD.FH, originalStr);
        if (targetChar.isPresent()) {
            String[] splits = targetChar.get().split("var r =");
            if (splits.length > 1) {
                JSONArray jsonArray = JSONArray.parseArray(splits[1]);
                jsonArray.stream().forEach(fundTemp -> {
                    JSONArray jsonArray_fund = (JSONArray) fundTemp;
                    funds.add(
                            Fund.builder()
                                    .code(jsonArray_fund.getString(0))
                                    .type(jsonArray_fund.getString(3))
                                    .build());
                });
            }
        }
        return funds;
    }


    public Fund parseFundDetail(String originalStr) {
        Fund fund = Fund.builder().build();
        parse_FS_NAME(fund, originalStr);
        parse_FS_CODE(fund, originalStr);
        parse_STOCK_CODES(fund, originalStr);
        parse_ZQ_CODES(fund, originalStr);
        parse_DATA_CURRENT_FUND_MANAGER(fund, originalStr);
        return fund;
    }

    private Optional<String> parse_given_key_words(String keyWords, String originalStr) {
        Optional<String> targetChar = StringHelper.getTargetChar(keyWords, TT_FUND_KEY_WORLD.FH, originalStr);
        return targetChar;
    }

    private void parse_DATA_CURRENT_FUND_MANAGER(Fund fund, String originalStr) {
        Optional<String> stringOptional = parse_given_key_words(TT_FUND_KEY_WORLD.DATA_CURRENT_FUND_MANAGER, originalStr);
        if (stringOptional.isPresent()) {
            String[] keyValues = stringOptional.get().split(TT_FUND_KEY_WORLD.DH);
            if (keyValues.length > 1) {
                JSONArray jsonArray = JSONArray.parseArray(keyValues[1]);
                List<FundManager> fundMangers = Lists.newArrayList();
                jsonArray.stream().forEach(fundManagerJSONobject -> {
                    FundManager fundManager = JSONObject.parseObject(JSONObject.toJSONString(fundManagerJSONobject), FundManager.class);
                    fundMangers.add(fundManager);
                });
                fund.setFundManagers(fundMangers);
            }
        }
    }

    private void parse_ZQ_CODES(Fund fund, String originalStr) {
        Optional<String> stringOptional = parse_given_key_words(TT_FUND_KEY_WORLD.ZQ_CODES, originalStr);
        if (stringOptional.isPresent()) {
            String[] keyValues = stringOptional.get().split(TT_FUND_KEY_WORLD.DH);
            if (keyValues.length > 1) {
                String[] codes = keyValues[1].split(TT_FUND_KEY_WORLD.XDH);
                fund.setBondPositions(Lists.newArrayList(codes));
            }
        }
    }

    private void parse_STOCK_CODES(Fund fund, String originalStr) {
        Optional<String> stringOptional = parse_given_key_words(TT_FUND_KEY_WORLD.STOCK_CODES, originalStr);
        if (stringOptional.isPresent()) {
            String[] keyValues = stringOptional.get().split(TT_FUND_KEY_WORLD.DH);
            if (keyValues.length > 1) {
                JSONArray jsonArray = JSONArray.parseArray(keyValues[1]);
                List<String> stockCodes = Lists.newArrayList();
                jsonArray.stream().forEach(stockCode -> stockCodes.add(stockCode.toString()));
                fund.setStockPositions(stockCodes);
            }
        }
    }

    private void parse_FS_CODE(Fund fund, String originalStr) {
        Optional<String> stringOptional = parse_given_key_words(TT_FUND_KEY_WORLD.FS_CODE, originalStr);
        if (stringOptional.isPresent()) {
            String[] keyValues = stringOptional.get().split(TT_FUND_KEY_WORLD.DH);
            if (keyValues.length > 1) {
                fund.setCode(keyValues[1]);
            }
        }
    }

    private void parse_FS_NAME(Fund fund, String originalStr) {
        Optional<String> stringOptional = parse_given_key_words(TT_FUND_KEY_WORLD.FS_NAME, originalStr);
        if (stringOptional.isPresent()) {
            String[] keyValues = stringOptional.get().split(TT_FUND_KEY_WORLD.DH);
            if (keyValues.length > 1) {
                fund.setName(keyValues[1]);
            }
        }
    }

}

package com.antz.financial.adapter.endpoint;

import com.antz.financial.application.IFundCrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huang.huang
 * @description
 * @date 2020/2/23 16:48
 **/
@Api(value = "Fund", tags = "Fund APIs", description = "Fund APIs.")
@RestController
public class FundEndpoint {

    public static final String DEFAULE_PAGE = "0";

    public static final String DEFAULE_PAGESIZE = "50";


    @Autowired
    private IFundCrawlerService fundCrawlerService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page.", paramType = ParamType.QUERY),
            @ApiImplicitParam(name = "pageSize", value = "pageSize.", paramType = ParamType.QUERY),
            @ApiImplicitParam(name = "fundCode", value = "fund code.", paramType = ParamType.QUERY),
            @ApiImplicitParam(name = "crawleDate", value = "fcrawleDate , example 20190908", paramType = ParamType.QUERY),
    })
    @PostMapping("/financial/fund/operation/crawle/funddetail")
    public String executeFundDetailCrawle(@RequestParam(value = "page", defaultValue = DEFAULE_PAGE) Integer page,
                                          @RequestParam(value = "pageSize", defaultValue = DEFAULE_PAGESIZE) Integer pageSize,
                                          @RequestParam(value = "fundCode", required = false) String fundCode,
                                          @RequestParam(value = "crawleDate", required = false) String crawleDate) {
        long crawleCount = fundCrawlerService.fundDetailCrawler(fundCode, page, pageSize, System.currentTimeMillis());
        return "Success :" + crawleCount;
    }

    @PostMapping("/financial/fund/operation/crawle/fund")
    public void executeFundCrawle() {
        fundCrawlerService.fundCrawler();
    }
}

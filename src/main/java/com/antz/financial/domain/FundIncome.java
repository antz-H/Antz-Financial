package com.antz.financial.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 22:48
 **/
@Data
public class FundIncome {

    private BigDecimal rateOfReturn_1y;
    private BigDecimal rateOfReturn_6m;
    private BigDecimal rateOfReturn_3m;
    private BigDecimal rateOfReturn_1m;
}

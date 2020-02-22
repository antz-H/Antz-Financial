package com.antz.financial.domain;

import com.antz.financial.domain.core.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 22:43
 **/
@Builder
@Data
@Document(collection = "col_fund")
public class Fund extends AbstractEntity {

    @Id
    private String code;

    private String name;

    private String type;

    private List<String> stockPositions;

    private List<String> bondPositions;

    private List<FundManager> fundManagers;

    private FundIncome fundIncome;

}

package com.antz.financial.domain;

import lombok.Data;

import java.util.List;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 23:08
 **/
@Data
public class IndexComparison {

    private List<Index> data;


    @Data
    public class Index {
        private String name;

        private String color;

        private Double y;
    }
}

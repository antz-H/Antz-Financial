package com.antz.financial.domain;

import lombok.Data;

import java.util.List;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 23:00
 **/
@Data
public class Power {

    private double avr;

    private List<String> categories;

    private List<String> dsc;

    private List<Double> data;

}

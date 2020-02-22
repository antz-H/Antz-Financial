package com.antz.financial.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.antz.financial.domain.Fund;
import com.antz.financial.domain.FundManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.System.out;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TTFundCrawlerTest {

    @Autowired
    private TTFundCrawler ttFundCrawler;

    String originalStr;

    @Before
    public void setup() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("DayFund_demo.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        int read;
        while ((read = bufferedInputStream.read(bytes)) != -1) {
            stringBuffer.append(new String(bytes, 0, read));
        }
        out.println(stringBuffer.toString());
        originalStr = stringBuffer.toString();
    }


    @Test
    public void testCrawlerAndParse() {
        TTFundCrawler fundCrawler = new TTFundCrawler();
        Fund fund = fundCrawler.crawlerFundDetailAndParse("519983", 20200219155842L);
        out.println(fund);
    }

    @Test
    public void testCrawlerFundAndParse() {
        List<Fund> maps = ttFundCrawler.crawlerFundAndParse();
        out.println(maps.size());
    }

    @Test
    public void testParseStr() {
        Fund fund = ttFundCrawler.parseFundDetail(originalStr);
        out.println(fund);
    }

    @Test
    public void testSplit() {
        String json = "[\"6031601\",\"6004661\",\"6001431\",\"0024752\",\"0026072\",\"6018081\",\"0021462\",\"0024632\",\"3001792\",\"0026012\"]";
        JSONArray jsonArray = JSONArray.parseArray(json);
    }

    @Test
    public void testJSON() {
        String json = "[{\"id\":\"30335060\",\"pic\":\"https://pdf.dfcfw.com/pdf/H8_JPG30335060_1.jpg\",\"name\":\"左金保\",\"star\":3,\"workTime\":\"4年又346天\",\"fundSize\":\"38.86亿(12只基金)\",\"power\":{\"avr\":\"47.19\",\"categories\":[\"经验值\",\"收益率\",\"抗风险\",\"稳定性\",\"择时能力\"],\"dsc\":[\"反映基金经理从业年限和管理基金的经验\",\"根据基金经理投资的阶段收益评分，反映\\u003cbr\\u003e基金经理投资的盈利能力\",\"反映基金经理投资的回撤控制能力\",\"反映基金经理投资收益的波动\",\"反映基金经理根据对市场的判断，通过\\u003cbr\\u003e调整仓位及配置而跑赢业绩的基准能力\"],\"data\":[72.50,61.70,12.50,16.0,54.90],\"jzrq\":\"2020-02-20\"},\"profit\":{\"categories\":[\"任期收益\",\"同类平均\",\"沪深300\"],\"series\":[{\"data\":[{\"name\":null,\"color\":\"#7cb5ec\",\"y\":66.0917},{\"name\":null,\"color\":\"#414c7b\",\"y\":54.16},{\"name\":null,\"color\":\"#f7a35c\",\"y\":14.57}]}],\"jzrq\":\"2020-02-20\"}}] ";
        JSONArray jsonArray = JSONArray.parseArray(json);
        FundManager fundManager = JSONObject.parseObject(jsonArray.getString(0), FundManager.class);

        out.println(fundManager);

    }

    @Test
    public void test() {
        String json = "[\n" +
                "    [\n" +
                "        \"000001\",\n" +
                "        \"HXCZHH\",\n" +
                "        \"华夏成长混合\",\n" +
                "        \"混合型\",\n" +
                "        \"HUAXIACHENGZHANGHUNHE\"\n" +
                "    ],\n" +
                "    [\n" +
                "        \"000002\",\n" +
                "        \"HXCZHH\",\n" +
                "        \"华夏成长混合(后端)\",\n" +
                "        \"混合型\",\n" +
                "        \"HUAXIACHENGZHANGHUNHE\"\n" +
                "    ]]";

        JSONArray jsonArray = JSONArray.parseArray(json);
        out.println(jsonArray);

    }

}
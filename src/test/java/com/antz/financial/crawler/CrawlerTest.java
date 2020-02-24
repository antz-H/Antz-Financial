package com.antz.financial.crawler;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 21:05
 **/
public class CrawlerTest {

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
    public void test() throws IOException {
        int indexOf_start = originalStr.indexOf(TT_FUND_KEY_WORLD.FS_CODE);
        if (indexOf_start > 0) {
            int indexOf_end = originalStr.indexOf(";", indexOf_start);
            String substring = originalStr.substring(indexOf_start, indexOf_end);
            out.println(substring);
        }
    }
}

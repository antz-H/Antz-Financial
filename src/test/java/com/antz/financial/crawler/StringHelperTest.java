package com.antz.financial.crawler;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.text.MessageFormat;

public class StringHelperTest {


    @Test
    public void testStringFormat() {
        String patternMessage = "Hello {0}";
        String args = "ANTZ";
        String stringFormat = StringHelper.StringFormat(patternMessage, args);
        String expertStringFormat = MessageFormat.format(patternMessage, args);
        Assertions.assertThat(stringFormat.equals(expertStringFormat)).isTrue();
    }
}
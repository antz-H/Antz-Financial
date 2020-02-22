package com.antz.financial.crawler;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * @author antz-H
 * @description
 * @date 2020/2/21 22:20
 **/
public class StringHelper {

    /**
     * @param startChar
     * @param endChar
     * @param originalChar
     * @return
     */
    public static Optional<String> getTargetChar(String startChar, String endChar, String originalChar) {
        int index_start = originalChar.indexOf(startChar);
        if (index_start > 0) {
            int index_end = originalChar.indexOf(endChar, index_start);
            if (index_end > 0) {
                return Optional.ofNullable(originalChar.substring(index_start, index_end));
            }
        }
        return Optional.ofNullable(null);
    }

    /**
     * 格式化
     *
     * @param patternMessage like  Hello {0} , {1} ..
     * @param args
     * @return
     */
    public static String StringFormat(String patternMessage, String... args) {
        return MessageFormat.format(patternMessage, args);
    }
}
